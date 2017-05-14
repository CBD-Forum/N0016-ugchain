package com.ugc.gameserver.service;

import com.ugc.gameserver.config.AppConfig;
import com.ugc.gameserver.domain.DermaOrder;
import com.ugc.gameserver.domain.OrderStatusEnum;
import com.ugc.gameserver.domain.UserToken;
import com.ugc.gameserver.domain.UserTokenStatusEnum;
import com.ugc.gameserver.domain.contract.DAS;
import com.ugc.gameserver.domain.contract.Recharge;
import com.ugc.gameserver.domain.contract.Trade;
import com.ugc.gameserver.util.Web3Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by fanjl on 2017/5/8.
 */
@Service
public class Web3jServiceImpl implements Web3jService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Web3jServiceImpl.class);

    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private DermaOrderService dermaOrderService;
    @Autowired
    private AppConfig appConfig;

    private Web3j web3;

    private Recharge recharge;

    private Trade trade;

    private DAS das;

    private int gameId = 0;

    @Override
    public void init() {
        web3 = Web3j.build(new HttpService(appConfig.getGethHttpURL()));
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(appConfig.getWalletPassword(), appConfig.getWalletPath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (CipherException e) {
            LOGGER.error(e.getMessage());
        }
        recharge = Recharge.load(
                appConfig.getRechargeAddress(), web3, credentials, GAS_PRICE, BigInteger.valueOf(4_700_000));
        trade = Trade.load(appConfig.getTradeAddress(),web3,credentials,GAS_PRICE, BigInteger.valueOf(4_700_000));
        das = DAS.load(appConfig.getDasAddress(),web3,credentials,GAS_PRICE,BigInteger.valueOf(4_700_000));

        if(credentials!=null){
            LOGGER.info("init web3j and load contract end, use address :"+credentials.getAddress());
        }else{
            LOGGER.warn("credentials is null ! contract can only do query operation");
        }
        String gameName = Web3Util.bytesToHexString("过河拆桥".getBytes());
        das.initGameId(Web3Util.hexStringToByte32(gameName));
        int i = 0;
        while (true){
            try {
                Address sellerAddress = new Address(appConfig.getSellerAddress());
                gameId = das.getGameId(sellerAddress).get().getValue().intValue();
                if(gameId !=0 ){
                    LOGGER.info("get gameId success gameId:"+gameId);
                    break;
                }
                LOGGER.info("wait for register gameId ,try get gameId times:"+i);
                i++;
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException!can't get game id");
            } catch (ExecutionException e) {
                LOGGER.error("ExecutionException!can't get game id,Please check network");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getGameId() {
        return this.gameId;
    }

    @Override
    public void listenRecharge() {
        LOGGER.info("start listen recharge contract--> address:"+appConfig.getRechargeAddress());
        Observable<Recharge.PayEventResponse> observable = recharge.payEventObservable();
        observable.subscribe(new Action1<Recharge.PayEventResponse>() {
            @Override
            @Transactional(rollbackFor=Exception.class)
            public void call(Recharge.PayEventResponse payEventResponse) {
                Uint256 value = payEventResponse._amount;
                Address fromAddress = payEventResponse._payer;
                Address toAddress = payEventResponse._seller;
                Uint64 gameId = payEventResponse._gameId;
                Uint64 tradeId = payEventResponse._tradeId;
                LOGGER.info("get recharge event,gameId: "+gameId.getValue()+", tradeId:"+tradeId.getValue()+", seller address:"+toAddress.toString() );
                DermaOrder order = dermaOrderService.getOrderById(tradeId.getValue().intValue());

                if(order.getGameId()!=gameId.getValue().intValue()){
                    LOGGER.error("gameId is not equal");
                    throw new RuntimeException("gameId is not equal");
                }
                dermaOrderService.updateOrder(tradeId.getValue().intValue(), OrderStatusEnum.SUCCESS);
                Optional<UserToken> userToken = userTokenService.getUserTokenByToken(order.getToken());
                if(!userToken.isPresent()){
                    LOGGER.error("the token which pay order is not exists,please check");
                    throw new RuntimeException("the token which pay order is not exists,please check");
                }
                if(!toAddress.toString().equals(appConfig.getSellerAddress())){
                    LOGGER.error("seller address is not equal: "+toAddress.toString());
                    throw new RuntimeException("seller address is not equal: "+toAddress.toString());
                }
                List<String> dermas = userToken.get().getDerma();
                List<String> newDermas = new LinkedList<String>();
                newDermas.addAll(dermas);
                newDermas.add(String.valueOf(order.getDerma().getId()));
                boolean result = userTokenService.updateDerma(order.getToken(),newDermas);
                LOGGER.info("update order and user derma end,order token:"+order.getToken()+"result is: "+ result);
            }

        });
    }

    @Override
    public void listenBuyEvent() {
        LOGGER.info("listen buy event contract--> address:"+appConfig.getTradeAddress());
        Observable<Trade.BuyEventResponse> observable = trade.buyEventObservable();
        observable.subscribe(new Action1<Trade.BuyEventResponse>() {
            @Override
            @Transactional(rollbackFor = Exception.class)
            public void call(Trade.BuyEventResponse buyEventResponse) {
                Uint64 assetIdU = buyEventResponse._assetIndex;
                int assetId = assetIdU.getValue().intValue();
                String token = queryTokenByAssetId(assetId);
                userTokenService.updateStatus(token, UserTokenStatusEnum.USEING.getId());
                LOGGER.info("buy event end ,update user token status using,token:"+token);
            }
        });
    }

    @Override
    public void listenSellEvent() {
        LOGGER.info("listen sell event contract--> address:"+appConfig.getTradeAddress());
        Observable<Trade.SellEventResponse> observable = trade.sellEventObservable();
        observable.subscribe(new Action1<Trade.SellEventResponse>() {
            @Override
            @Transactional(rollbackFor = Exception.class)
            public void call(Trade.SellEventResponse sellEventResponse) {
                Uint64 assetIdU = sellEventResponse._assetIndex;
                Uint64 gameIdU = sellEventResponse._gameId;
                int assetId = assetIdU.getValue().intValue();
                int gameId = gameIdU.getValue().intValue();
                String token = queryTokenByAssetId(assetId);
                userTokenService.updateStatus(token, UserTokenStatusEnum.DEALING.getId());
                LOGGER.info("sell event end ,gameId:"+gameId+", update user token status dealing,token:"+token);
            }
        });
    }

    @Override
    public int queryAssetIdByToken(String token) {
        Future<Uint64> assetIdFuture = das.getIndexByToken(Web3Util.hexStringToByte32(token));
        int assetId = 0;
        try {
            assetId = assetIdFuture.get().getValue().intValue();
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occured while query asset id",e);
        } catch (ExecutionException e) {
            LOGGER.error("ExecutionException occured while query asset id",e);
        }
        return assetId;
    }

    @Override
    public String queryTokenByAssetId(int assetId) {
        Future<Bytes32> tokenFuture = das.getTokenByAssetId(Web3Util.toUint64(assetId));
        String token = "0x";
        try {
            token += Web3Util.bytes32ToHexString(tokenFuture.get());
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occured while query token ",e);
        } catch (ExecutionException e) {
            LOGGER.error("ExecutionException occured while query token ",e);
        }
        return token.toLowerCase();
    }

    @Override
    public String queryGameNameById(int gameId) {
        Future<Bytes32> tokenFuture = das.getGameNameByGameId(Web3Util.toUint64(gameId));
        String name = "";
        try {
//            name = Web3Util.bytes32ToHexString(tokenFuture.get());
//            byte[] nameByte = Web3Util.hexStringToByte(name);

            byte[] str = tokenFuture.get().getValue();
            name = new String(Web3Util.fromUtf8(str));
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occured while query token ",e);
        } catch (ExecutionException e) {
            LOGGER.error("ExecutionException occured while query token ",e);
        }
        return name;
    }

    @Override
    public boolean isOnSell(int assetId) {
        boolean result = false;
        try {
             result = das.getSellingStatusByAssetId(Web3Util.toUint64(assetId)).get().getValue();
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException occured while query is on sell,assetId"+assetId);
        } catch (ExecutionException e) {
            LOGGER.error("ExecutionException occured while query is on sell,assetId"+assetId);
        }
        return result;
    }

    @Override
    public void sell(int gameId,String proveHash, BigDecimal prices, int assetId) {
//        Long unit = new Double(Math.pow(10,18)).longValue();
//        BigInteger price = BigInteger.valueOf(unit).multiply(BigInteger.valueOf(prices.intValue()));
        trade.sell(Web3Util.toUint64(gameId),Web3Util.toUint64(assetId),
                Web3Util.toUint256(prices.intValue()), Web3Util.hexStringToByte32(proveHash));
        LOGGER.info("send transcation end , prices:"+prices+", assetId:"+assetId);
    }

}

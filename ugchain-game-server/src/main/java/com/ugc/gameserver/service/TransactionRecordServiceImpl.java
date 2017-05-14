package com.ugc.gameserver.service;


import com.ugc.gameserver.domain.AmountChangeTypeEnum;
import com.ugc.gameserver.domain.BlockRecord;
import com.ugc.gameserver.domain.OrderStatusEnum;
import com.ugc.gameserver.mapper.BlockRecordMapper;
import com.ugc.gameserver.mapper.SequenceMapper;
import com.ugc.gameserver.util.HexUtil;
import com.ugc.gameserver.util.Keccak;
import com.ugc.gameserver.util.Parameters;
import com.ugc.gameserver.config.AppConfig;
import com.ugc.gameserver.domain.Account;
import com.ugc.gameserver.domain.OrderTypeEnum;
import com.ugc.gameserver.domain.contract.Cashier;
import com.ugc.gameserver.mapper.InnerRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.parity.Parity;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by fanjl on 2017/4/6.
 */
@Component
public class TransactionRecordServiceImpl implements TransactionRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRecordServiceImpl.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private InnerRecordMapper innerRecordMapper;
    @Autowired
    private BlockRecordMapper blockRecordMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private AppConfig appConfig;

    private Web3j web3;

    private Parity parity;

    private Keccak keccak = new Keccak();


     Cashier contract;

    @Override
    public void initWeb3J() {
        web3 = Web3j.build(new UnixIpcService(appConfig.getGethLocation()));
        parity = Parity.build(new UnixIpcService(appConfig.getGethLocation()));
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(appConfig.getWalletPassword(), appConfig.getWalletPath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (CipherException e) {
            LOGGER.error(e.getMessage());
        }
        contract = Cashier.load(
        appConfig.getSellerAddress(), web3, credentials, GAS_PRICE, GAS_LIMIT);
    }


    @Override
    public int
    nextTransactionId() {
        return sequenceMapper.nextId(sequenceMapper.BLOCK_RECORD);
    }

    @Override
    public String keccakHash(String data) {
        return   keccak.getHash(HexUtil.getHex(data.toString().getBytes()), Parameters.KECCAK_256);
    }

    @Override
    public void recharge() {
        initWeb3J();
        Observable<Cashier.CollectEventResponse> observable = contract.collectEventObservable();

        observable.subscribe(new Action1<Cashier.CollectEventResponse>() {
            @Override
            @Transactional(rollbackFor=Exception.class)
            public void call(Cashier.CollectEventResponse collectEventResponse) {
                Address fromAddress = collectEventResponse.addr;
                Uint256 value = collectEventResponse.amount;
                if(!accountService.isExistsAddress(fromAddress.toString())){
                    accountService.createAccount(fromAddress.toString(),value.getValue());
                }
                BlockRecord blockRecord = new BlockRecord();
                blockRecord.setAmount(value.getValue().divide(BigInteger.valueOf(1000000000)) );
                blockRecord.setBlockRecordId(nextTransactionId());
                blockRecord.setFee(appConfig.getFee());
                blockRecord.setTargetAddress(appConfig.getSellerAddress());
                blockRecord.setType(OrderTypeEnum.RECHARGE.getId());
                blockRecord.setStatus(OrderStatusEnum.SUCCESS.getId());
                System.out.println(value.getValue());
                blockRecordMapper.insertBlockRecord(blockRecord);

                Optional<Account> account = accountService.getAccountByAddress(fromAddress.toString());
                if(account.isPresent()){
                     accountService.updateAmount(fromAddress.toString(),value.getValue(), AmountChangeTypeEnum.ADD.getId());
                }else {
                    LOGGER.error("Contract Listener  error while insert account , fromAddress :"+fromAddress.toString()+",value :"+value.getValue());
                    throw new RuntimeException("Contract Listener  error while insert account , fromAddress :"+fromAddress.toString()+",value :"+value.getValue());
                }

            }

        });

    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void transfer(String fromAddress, String toAddress, BigInteger amount, int nonce, String signedMsg, String msg) {
        Optional<Account> fromAccount = accountService.getAccountByAddress(fromAddress);
        verifySigned(fromAddress,signedMsg,msg,nonce);
        Optional<Account> toAccount = accountService.getAccountByAddress(toAddress);
        if(!fromAccount.isPresent()){
            LOGGER.info(String.format("Account is not present,fromAddress:%s",fromAddress));
            return;
        }
        if(!toAccount.isPresent()){
            LOGGER.info(String.format("Account is not present,toAddress:%s",toAddress));
            return;
        }
        if(accountService.isAmountEnough(fromAddress,amount)){
            accountService.updateAmount(fromAddress,amount, AmountChangeTypeEnum.SUBTRACTION.getId());
            accountService.updateAmount(toAddress,amount,AmountChangeTypeEnum.ADD.getId());
        }else {
            LOGGER.info(String.format("Account amount is not enough , fromAddress:%s",fromAddress));
        }
    }

    @Override
    public void withDraw(String address, String signedMsg, BigInteger amount, int nonce, String msg) {
        // TODO: 2017/4/7 转账交易sdk，提现即从公司账户向目标账户提现
    }

    @Override
    public boolean verifySigned(String address, String signedMsg, String msg, int nonce) {
        StringBuilder data = new StringBuilder();
        data.append(address).append(nonce).append(msg);
        String hashData = keccakHash(data.toString());

        // TODO: 2017/4/7  验证以太坊签名
        return false;
    }
}

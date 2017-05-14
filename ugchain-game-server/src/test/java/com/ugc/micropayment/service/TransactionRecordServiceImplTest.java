package com.ugc.micropayment.service;

import com.ugc.gameserver.config.AppConfig;
import com.ugc.gameserver.service.AccountService;
import com.ugc.gameserver.service.TransactionRecordService;
import com.ugc.micropayment.configuration.ConfigurationTest;
import com.ugc.gameserver.domain.BlockRecord;
import com.ugc.gameserver.domain.OrderStatusEnum;
import com.ugc.gameserver.domain.OrderTypeEnum;
import com.ugc.gameserver.mapper.BlockRecordMapper;
import com.ugc.gameserver.util.Keccak;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthSign;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.math.BigInteger;

import static com.ugc.gameserver.util.HexUtil.getHex;
import static com.ugc.gameserver.util.Parameters.KECCAK_256;

/**
 * Created by fanjl on 2017/4/7.
 */
@SpringBootTest(classes = {ConfigurationTest.class})
@RunWith(SpringRunner.class)
@EnableConfigurationProperties(AppConfig.class)
public class TransactionRecordServiceImplTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRecordService transactionRecordService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private BlockRecordMapper blockRecordMapper;


    Web3j web3j ;
    Parity parity;
    @Before
    public void initWeb3j() throws Exception {
        parity = Parity.build(new UnixIpcService(appConfig.getGethLocation()));
        web3j = Web3j.build(new UnixIpcService(appConfig.getGethLocation()));
    }

    @Test
    public void recharge() throws Exception {
        BlockRecord blockRecord = new BlockRecord();
        blockRecord.setAmount(BigInteger.valueOf(30));
        blockRecord.setBlockRecordId(transactionRecordService.nextTransactionId());
        blockRecord.setFee(appConfig.getFee());
        blockRecord.setTargetAddress(appConfig.getSellerAddress());
        blockRecord.setType(OrderTypeEnum.RECHARGE.getId());
        blockRecord.setStatus(OrderStatusEnum.SUCCESS.getId());
        blockRecordMapper.insertBlockRecord(blockRecord);
    }

    @Test
    public void transfer() throws Exception {

    }

    @Test
    public void withDraw() throws Exception {

    }

    @Test
    public void verifySigned() throws Exception {
        StringBuilder data = new StringBuilder();
        data.append("0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54").append(4).append("123");
        Request<?, Web3Sha3> hashData = web3j.web3Sha3(data.toString());
        String s = getHex("abc".getBytes());

        Keccak keccak = new Keccak();
        String datab = keccak.getHash(getHex(data.toString().getBytes()),KECCAK_256);
        System.out.println(datab);
        System.out.println("keccak-256 = " + keccak.getHash(s, KECCAK_256));
        Request<?,EthSign> ethSignRequest =web3j.ethSign("0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54","0x"+keccak.getHash(s, KECCAK_256));
        PersonalUnlockAccount personalUnlockAccount = parity.personalUnlockAccount("0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54", "123").sendAsync().get();
        if (personalUnlockAccount.accountUnlocked()) {
            // send a transaction, or use parity.personalSignAndSendTransaction() to do it all in one
            EthSign ethSign = ethSignRequest.send();
            System.out.println(ethSign.getSignature());
        }
    }

}
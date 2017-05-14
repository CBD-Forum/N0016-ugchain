package com.ugc.gameserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugc.gameserver.domain.UserToken;
import com.ugc.gameserver.domain.UserTokenStatusEnum;
import com.ugc.gameserver.mapper.SequenceMapper;
import com.ugc.gameserver.mapper.UserTokenMapper;
import com.ugc.gameserver.util.Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by fanjl on 2017/4/25.
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenServiceImpl.class);
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private Web3jService web3jService;

    Encrypt encrypt = new Encrypt();

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int nextUserTokenId() {
        return sequenceMapper.nextId(sequenceMapper.USER_TOKEN);
    }

    @Override
    public UserToken getUserTokenById(int userTokenId) {

        return userTokenMapper.getUserTokenById(userTokenId);
    }

    @Override
    public UserToken insertAndGet(String userName,String token, String data, int status) {
//        Optional<UserToken> utOp = getUserTokenByToken(token);
//        if(utOp.isPresent()){
//            return utOp.get();
//        }
        List<String> dermas = new LinkedList<String>();
        dermas.add("0");
        UserToken usertoken = new UserToken();
        usertoken.setUserName(userName);
        usertoken.setDerma(dermas);
        usertoken.setUserTokenId(nextUserTokenId());
        usertoken.setToken(token);
        usertoken.setData(data);
        usertoken.setStatus(UserTokenStatusEnum.USEING.getId());
        userTokenMapper.insertUserToken(usertoken);
        return usertoken;
    }

    @Override
    public boolean updateDerma(String token, List<String> derma) {
        Optional<UserToken> utOp = getUserTokenByToken(token);
        if(!utOp.isPresent()){
            return false;
        }
        userTokenMapper.updateDerma(derma,token);
        return true;
    }

    @Override
    public boolean updateData(String token, int data) {
        Optional<UserToken> utOp = getUserTokenByToken(token);
        if(!utOp.isPresent()){
            return false;
        }
        userTokenMapper.updateData(data,token);
        return true;
    }

    @Override
    public boolean updateStatus(String token, int status) {
        Optional<UserToken> utOp = getUserTokenByToken(token);
        if(!utOp.isPresent()){
            return false;
        }
        userTokenMapper.updateStatus(status,token);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean onSelling(int gameId,String token, int status, BigDecimal prices) {
        Optional<UserToken> utOp = getUserTokenByToken(token);
        if(!utOp.isPresent()){
            return false;
        }
        String value = "";
        try {
            value = objectMapper.writeValueAsString(utOp.get());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("convert from user token to json error ", e);
        }

        int assetId = web3jService.queryAssetIdByToken(token);
        String proveHash = encrypt.SHA256(value);
        web3jService.sell(gameId,proveHash,prices,assetId);
        userTokenMapper.updateStatusAndPrices(status,prices,token);
        return true;
    }


    @Override
    public boolean isExistsUserToken(String token) {

        return userTokenMapper.isExistsUserToken(token);
    }

    @Override
    public List<UserToken> getUserTokenListByStatus(List<Integer> status) {
        return userTokenMapper.getUserTokenList(status);
    }

    @Override
    public Optional<UserToken> getUserTokenByToken(String token) {
        UserToken userToken = userTokenMapper.getUserTokenByToken(token);
        return Optional.ofNullable(userToken);
    }
}

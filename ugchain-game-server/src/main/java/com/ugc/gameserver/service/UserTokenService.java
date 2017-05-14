package com.ugc.gameserver.service;

import com.ugc.gameserver.domain.UserToken;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by fanjl on 2017/4/25.
 */
public interface UserTokenService {
    int nextUserTokenId();

    UserToken getUserTokenById(int userTokenId);

    UserToken insertAndGet(String userName,String token,String data,int status);

    boolean updateDerma(String token,List<String> derma);

    boolean updateData(String token,int data);

    boolean updateStatus(String token,int status);

    boolean onSelling(int gameId,String token, int status, BigDecimal prices);

    boolean isExistsUserToken(String token);

    List<UserToken> getUserTokenListByStatus(List<Integer> status);

    Optional<UserToken> getUserTokenByToken(String token);
}

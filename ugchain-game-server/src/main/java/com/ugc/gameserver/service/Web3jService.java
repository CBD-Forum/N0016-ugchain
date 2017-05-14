package com.ugc.gameserver.service;

import java.math.BigDecimal;

/**
 * Created by fanjl on 2017/5/8.
 */
public interface Web3jService {
    void init();

    int getGameId();

    void listenRecharge();

    void listenBuyEvent();

    void listenSellEvent();

    int queryAssetIdByToken(String token);

    String queryTokenByAssetId(int assetId);

    String queryGameNameById(int gameId);

    boolean isOnSell(int assetId);

    void sell(int gameId,String proveHash, BigDecimal prices,int assetId);
}

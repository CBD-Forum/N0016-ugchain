package com.ugc.gameserver.service;

import com.ugc.gameserver.domain.Account;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by fanjl on 2017/4/5.
 */

public interface AccountService {

    void createAccount(String address,BigInteger amount);   //account 表插入一条记录;

    boolean isExistsAddress(String address);//account查询账户是否存在;

    boolean isAmountEnough(String address, BigInteger amount);//account查询是否有足够的份额；

    boolean updateAmount(String address,BigInteger amount,int type);//type,增加或减少,AmountChangeTypeEnum

    Optional<Account> getAccountByAddress(String address);

    int nextAccountId();
}

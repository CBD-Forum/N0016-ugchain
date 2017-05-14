package com.ugc.gameserver.service;


import com.ugc.gameserver.domain.Account;
import com.ugc.gameserver.domain.AccountStatusEnum;
import com.ugc.gameserver.mapper.AccountMapper;
import com.ugc.gameserver.mapper.SequenceMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by fanjl on 2017/4/6.
 */
@Component
@EnableTransactionManagement
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRecordServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private SequenceMapper sequenceMapper;
	


	
    @Override
    public void createAccount(String address,BigInteger amount) {
    	Account account = new Account();
    	account.setAccountId(nextAccountId());
    	account.setAddress(address);
    	account.setNonce(0);
    	account.setStatus(AccountStatusEnum.NORMAL.getId());
    	account.setAmount(amount);
    	accountMapper.insertAccount(account);
    }

    @Override
    public boolean isExistsAddress(String address) {
    	int i = accountMapper.findAddress(address);
    	//判断查询返回结果：0则不存在
    	if (i == 0) {			
    		return false;
		}else {
			return true;
		}
    }

    
    @Override
    public boolean isAmountEnough(String address, BigInteger amount) {
    	
    	int i = accountMapper.queryAmountEnough(address,amount);
    	//判断返回结果，0则证明账户金额不足
    	if (i == 0) {			
    		return false;
		}else {
			return true;
		}
    }

	@Override
	@Transactional
	public boolean updateAmount(String address, BigInteger amount,int type) {
		
		int i = 0;
		
		synchronized (this) {
			try {
				Optional<Account> optional = getAccountByAddress(address);
				Account account = optional.get();
				BigInteger OldAmount = account.getAmount();
				int nonce = account.getNonce();
				if (type==1) {
					account.setAmount(OldAmount.add(amount));
					account.setNonce(++nonce);
					i = accountMapper.updateAmount(account);
					if (i == 1) {
						LOGGER.info(String.format("Account is add amount:%s address:%s ", amount,address));
						return true;
					} else {
						return false;
					}
				}
				else {
					boolean enough = isAmountEnough(address, OldAmount);
					if (enough) {
						account.setAmount(OldAmount.subtract(amount));
						account.setNonce(++nonce);
						i = accountMapper.updateAmount(account);
						if (i == 1) {
							LOGGER.info(String.format("Account is subtraction amount:%s address:%s ", amount,address));
							return true;
						} else {
							return false;
						}
					}else {
						return false;
					}
					
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				return false;
			}
		}
	}

	@Override
	public Optional<Account> getAccountByAddress(String address) {
		
		Account account = accountMapper.getAccountByAddress(address);
		
		return Optional.ofNullable(account);
	}

	@Override
	public int nextAccountId() {
		return sequenceMapper.nextId(sequenceMapper.ACCOUNT);
	}
}

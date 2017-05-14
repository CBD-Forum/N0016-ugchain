package com.ugc.gameserver;

import com.ugc.gameserver.service.Web3jServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class UGCGameServerApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UGCGameServerApplication.class, args);
		context.getBean(Web3jServiceImpl.class).init();
		context.getBean(Web3jServiceImpl.class).listenRecharge();
		context.getBean(Web3jServiceImpl.class).listenSellEvent();
		context.getBean(Web3jServiceImpl.class).listenBuyEvent();

	}

}

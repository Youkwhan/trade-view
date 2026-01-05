package com.youkwhan.trade_view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TradeViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeViewApplication.class, args);
	}

}

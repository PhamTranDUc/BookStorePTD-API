package com.BookStorePTD.BookStorePTD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopBookPtdApplication {

	public static void main(String[] args) {
		System.setProperty("server.error.include-stacktrace", "never");
		SpringApplication.run(ShopBookPtdApplication.class, args);
	}


}

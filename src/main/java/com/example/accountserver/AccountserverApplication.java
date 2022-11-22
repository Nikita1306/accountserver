package com.example.accountserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AccountserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountserverApplication.class, args);
	}

}

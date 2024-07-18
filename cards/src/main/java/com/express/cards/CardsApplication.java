package com.express.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CardsApplication {

	public final Logger logger = LoggerFactory.getLogger(CardsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}

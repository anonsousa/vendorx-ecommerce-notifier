package com.antoniosousa.ecommerce.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcommerceNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceNotifierApplication.class, args);
	}

}

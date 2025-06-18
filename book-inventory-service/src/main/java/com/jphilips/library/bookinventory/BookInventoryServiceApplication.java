package com.jphilips.library.bookinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookInventoryServiceApplication.class, args);
	}

}

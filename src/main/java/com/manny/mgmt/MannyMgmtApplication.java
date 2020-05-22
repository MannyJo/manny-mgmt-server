package com.manny.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MannyMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MannyMgmtApplication.class, args);

		System.out.println("Hello, World!!!");
		System.out.println("Server started.");
	}

}

package com.group7.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FavouritesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouritesServiceApplication.class, args);
	}

}

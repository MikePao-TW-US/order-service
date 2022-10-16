package com.multicart.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.multicart.api.*"})
@EnableJpaRepositories(basePackages = "com.multicart.api.repository")
public class MultiCartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiCartApiApplication.class, args);
	}

}

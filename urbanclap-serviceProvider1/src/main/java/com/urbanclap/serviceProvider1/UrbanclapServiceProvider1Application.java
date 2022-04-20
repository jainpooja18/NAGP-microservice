package com.urbanclap.serviceProvider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrbanclapServiceProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(UrbanclapServiceProvider1Application.class, args);
	}

}

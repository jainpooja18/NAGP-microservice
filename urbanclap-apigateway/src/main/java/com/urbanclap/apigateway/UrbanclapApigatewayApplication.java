package com.urbanclap.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrbanclapApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanclapApigatewayApplication.class, args);
	}

}

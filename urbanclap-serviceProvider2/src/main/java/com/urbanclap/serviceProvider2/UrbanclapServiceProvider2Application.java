package com.urbanclap.serviceProvider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrbanclapServiceProvider2Application {

	public static void main(String[] args) {
		SpringApplication.run(UrbanclapServiceProvider2Application.class, args);
	}

}

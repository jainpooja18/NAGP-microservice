package com.urbanclap.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UrbanclapServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanclapServiceDiscoveryApplication.class, args);
	}

}

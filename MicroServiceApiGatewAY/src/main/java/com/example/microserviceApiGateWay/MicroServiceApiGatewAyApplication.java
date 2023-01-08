package com.example.microserviceApiGateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroServiceApiGatewAyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApiGatewAyApplication.class, args);
	}

}

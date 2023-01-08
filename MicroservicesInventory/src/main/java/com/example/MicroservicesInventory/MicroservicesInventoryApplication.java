package com.example.MicroservicesInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
public class MicroservicesInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesInventoryApplication.class, args);
		
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan("com.example.MicroservicesInventory");
		
	}
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplet()
	{
		return new RestTemplate();
	}

}

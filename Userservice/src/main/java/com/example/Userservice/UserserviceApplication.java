package com.example.Userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@EnableEurekaClient
@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) 
	{
		
		SpringApplication.run(UserserviceApplication.class, args);
		
		
		
	}
	
	
	@LoadBalanced
	@Bean
	public RestTemplate getRest()
	{
		RestTemplate rest = new RestTemplate();
		return rest;
	}

}

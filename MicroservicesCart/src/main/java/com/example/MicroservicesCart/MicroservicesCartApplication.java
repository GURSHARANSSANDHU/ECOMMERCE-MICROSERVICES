package com.example.MicroservicesCart;

import org.hibernate.annotations.Loader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
public class MicroservicesCartApplication {

	public static void main(String[] args) 
	{
		
		SpringApplication.run(MicroservicesCartApplication.class, args);
		
		 
		
	}
	

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplet()
	{
		return new RestTemplate();
	}

}

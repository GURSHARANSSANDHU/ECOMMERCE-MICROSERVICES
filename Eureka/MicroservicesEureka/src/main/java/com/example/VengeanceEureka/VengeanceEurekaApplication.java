package com.example.VengeanceEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class VengeanceEurekaApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(VengeanceEurekaApplication.class, args);
	}

}

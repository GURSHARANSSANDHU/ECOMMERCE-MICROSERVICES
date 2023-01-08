package com.example.Userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.accept.MappingMediaTypeFileExtensionResolver;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class Configure 
{
	
	
	@Bean
	public WebClient getWebClient()
	{
		WebClient wb;
		wb = WebClient.builder().baseUrl("http://Microservices-Inventory/Inventory").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		return wb;
		
	}

}

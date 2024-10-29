package com.employee.micro.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

    @Value("${address.service.base.url}")
    String addressServiceBaseUrl;

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}


    @Bean
    WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(addressServiceBaseUrl)
                .build();
    }
	
}

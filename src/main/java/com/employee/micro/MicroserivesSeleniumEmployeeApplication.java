package com.employee.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserivesSeleniumEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserivesSeleniumEmployeeApplication.class, args);
	}

}

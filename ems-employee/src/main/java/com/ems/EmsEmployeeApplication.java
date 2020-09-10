package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmsEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsEmployeeApplication.class, args);
	}

}

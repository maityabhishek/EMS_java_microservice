package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsAdminApplication.class, args);
	}

}

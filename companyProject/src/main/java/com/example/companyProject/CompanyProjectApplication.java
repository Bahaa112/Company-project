package com.example.companyProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyProjectApplication.class, args);
	}

}

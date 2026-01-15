package com.example.springapi09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class Springapi09Application {

	public static void main(String[] args) {
		SpringApplication.run(Springapi09Application.class, args);
	}

}

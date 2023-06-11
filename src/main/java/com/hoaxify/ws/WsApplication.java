package com.hoaxify.ws;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMappers() {
		return new ModelMapper();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoders() {
		return new BCryptPasswordEncoder();
	}
}

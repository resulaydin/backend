package com.hoaxify.ws.udemy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "hoaxify")
public class ApplicationConfiguration {
	
	private String uploadedPath;

}

package com.hoaxify.ws.udemy.configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//	// Tekil Yöntem
//	@Value("${uploaded-path}")
//	String uploadedPath;

	@Autowired
	ApplicationConfiguration applicationConfiguration;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// https://localhost:8080/image/profile.png
		registry.addResourceHandler("/images/**")
//		.addResourceLocations("file:./picture-storage/")	1. Yöntem
//		.addResourceLocations("file:./"+uploadedPath+"/")	2. Yöntem
				.addResourceLocations("file:./" + applicationConfiguration.getUploadedPath() + "/")
				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
	}
	
	
	@Bean
	CommandLineRunner createStorageDirectories() {
		return (args) -> {
			File folder = new File(applicationConfiguration.getUploadedPath());
			boolean folderExist = folder.exists() && folder.isDirectory();
			if(!folderExist) {
				folder.mkdir();
			}
		};
		
	}
}

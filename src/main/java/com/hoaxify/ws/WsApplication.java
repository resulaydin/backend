package com.hoaxify.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserService;

@SpringBootApplication
@RestControllerAdvice
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner createInitialUserss(UserService userService) {
//		return new CommandLineRunner() {
//			
//			@Override
//			public void run(String... args) throws Exception {
//				User user = new User();
//				user.setUsername("user1");
//				user.setDisplayName("display1");
//				user.setPassword("P1234");
//				userService.add(user);
//				
//			}
//		};
//	}
	
	@Bean
	CommandLineRunner createInitialUsers(UserService userService) {
		return (args) -> {		
					User user = new User();
					user.setUsername("user1");
					user.setDisplayName("display1");
					user.setPassword("P4ssword");
					userService.add(user);
			
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoders() {
		return new BCryptPasswordEncoder();
	}
}

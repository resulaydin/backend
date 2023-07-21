package com.hoaxify.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
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

	@Bean
	@Profile("dev")
	CommandLineRunner createInitialUsers(UserService userService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for (int i = 0; i < 35; i++) {
					User user = new User();
					user.setUsername("user"+(i+1));
					user.setDisplayName("display"+(i+1));
					user.setPassword("P1234");
					userService.add(user);
				}

			}
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoders() {
		return new BCryptPasswordEncoder();
	}
}

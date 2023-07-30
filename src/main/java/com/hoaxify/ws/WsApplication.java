package com.hoaxify.ws;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoaxify.ws.udemy.hoax.Hoax;
import com.hoaxify.ws.udemy.hoax.HoaxService;
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
	CommandLineRunner createInitialUsers(UserService userService, HoaxService hoaxService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for (int i = 0; i < 8; i++) {
					User user = new User();
					user.setUsername("user" + (i + 1));
					user.setDisplayName("display" + (i + 1));
					user.setPassword("P1234");
					userService.add(user);
					for (int j = 0; j < 5; j++) {
						Hoax hoax = new Hoax();
						hoax.setContent("hoax ("+(j+1)+") \n");
						hoax.setTimestamp(new Date());
						hoaxService.add(user,hoax);
					}
				}



			}
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoders() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

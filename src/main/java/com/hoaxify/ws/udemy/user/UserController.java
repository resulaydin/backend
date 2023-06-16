package com.hoaxify.ws.udemy.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.udemy.shared.GenericResponse;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	final static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/api/v1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createUser(@Valid @RequestBody User user) {
		userService.add(user);
		return new GenericResponse("user created - basarili");

	}

	@GetMapping("/api/v1.0/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAll() {
		return userService.getAll();
	}
	
	
	
	
	
	
	
	
}

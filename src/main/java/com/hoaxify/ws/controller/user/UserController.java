package com.hoaxify.ws.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.business.concretes.UserManager;
import com.hoaxify.ws.business.requests.CreateUserRequest;
import com.hoaxify.ws.udemy.shared.GenericrResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserManager userService;

	final static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/api/v1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericrResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		userService.add(createUserRequest);
		return new GenericrResponse("user created - basarili");

	}
}

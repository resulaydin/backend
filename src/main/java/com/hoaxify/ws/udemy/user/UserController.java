package com.hoaxify.ws.udemy.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.udemy.shared.Views;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	final static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/api/v1.0/users")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Base.class)
	public User createUser(@Valid @RequestBody User user) {
		return userService.add(user);

	}

	@GetMapping("/api/v1.0/users")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.Base.class)
	public Page<UserProjection> getAll(Pageable page) {
		return userService.getAll(page);
	}

}

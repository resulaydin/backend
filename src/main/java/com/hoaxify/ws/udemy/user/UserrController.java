package com.hoaxify.ws.udemy.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.udemy.shared.GenericrResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserrController {

	private UserrService userrService;

	final static Logger log = LoggerFactory.getLogger(UserrController.class);

	@PostMapping("/api/v1.0/userrs")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericrResponse createUser(@Valid @RequestBody Userr userr) {
		userrService.add(userr);
		return new GenericrResponse("user created - basarili");

	}
}

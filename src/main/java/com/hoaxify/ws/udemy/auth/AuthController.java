package com.hoaxify.ws.udemy.auth;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.udemy.configuration.HoaxifyUserDetails;
import com.hoaxify.ws.udemy.shared.Views;
import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserService;

@RestController
public class AuthController {


	@Autowired
	UserService userrService;

	@PostMapping("/api/v1.0/auth")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.Base.class)
	public ResponseEntity<?> handleAuthentation(@RequestHeader(name = "Authorization") String authorization) {

		
		String authHeader = authorization.split("Basic ")[1];
		authHeader = new String(Base64.getDecoder().decode(authHeader));
		String username = authHeader.split(":")[0];
		String password = authHeader.split(":")[1];
		System.out.println(username + " ve " + password);

		User user = userrService.findByUsername(username);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}
package com.hoaxify.ws.udemy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.udemy.shared.CurrentUser;
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

	public ResponseEntity<?> handleAuthentation(@CurrentUser User user) {	

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}

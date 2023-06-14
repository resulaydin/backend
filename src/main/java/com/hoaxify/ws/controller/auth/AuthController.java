package com.hoaxify.ws.controller.auth;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.business.abstracts.UserService;
import com.hoaxify.ws.business.responses.CreateUserResponse;
import com.hoaxify.ws.core.utilities.error.ApiError;

import lombok.Data;

@RestController
@Data
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	ApiError apiError;

	final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/api/v1.0/auth")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> handleAuthentation(
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		if (authorization == null) {
			apiError = new ApiError(401, "Unauthorized - ", "/ap/v1.0/auth -");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}

		String authHeader = authorization.split("Basic ")[1];
		authHeader = new String(Base64.getDecoder().decode(authHeader));

		String username = authHeader.split(":")[0];
		String password = authHeader.split(":")[1];
		System.out.println(username + "ve " + password);

		CreateUserResponse user = userService.findByUsername(username);
		
		String hashedPassword = user.getPassword();
		
		if (!passwordEncoder.matches(password, hashedPassword)) {
			apiError = new ApiError(401, "unAuthorizated", "/api/v1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}

		// Burada username db de varmı kontrolü yapılacaktır.

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

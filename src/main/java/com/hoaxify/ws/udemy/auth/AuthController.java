package com.hoaxify.ws.udemy.auth;

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

import com.hoaxify.ws.udemy.error.ApiError;
import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserService;

@RestController
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userrService;
	ApiError apirError;

	final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/api/v1.0/auth")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> handleAuthentation(
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		if (authorization == null) {
			apirError = new ApiError(401, "Unauthorized - ", "/ap/v1.0/authr -");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apirError);
		}

		String authHeader = authorization.split("Basic ")[1];
		authHeader = new String(Base64.getDecoder().decode(authHeader));

		String username = authHeader.split(":")[0];
		String password = authHeader.split(":")[1];
		System.out.println(username + "ve " + password);

		User userr = userrService.findByUsername(username);

		if (userr == null) {
			apirError = new ApiError(401, "Unauthorized user", "/api/v1.0/authr");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apirError);
		}

		String hashedPassword = userr.getPassword();

		if (!passwordEncoder.matches(password, hashedPassword)) {
			apirError = new ApiError(401, "unAuthorizated", "/api/v1.0/authr");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apirError);
		}

		// Burada username db de varmı kontrolü yapılacaktır.

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

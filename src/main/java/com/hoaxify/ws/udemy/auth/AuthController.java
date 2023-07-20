package com.hoaxify.ws.udemy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.udemy.shared.CurrentUser;
import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserService;
import com.hoaxify.ws.udemy.user.vm.UserVM;

@RestController
public class AuthController {


	@Autowired
	UserService userrService;

	@PostMapping("/api/v1.0/auth")
	@ResponseStatus(HttpStatus.OK)
	public UserVM handleAuthentation(@CurrentUser User user) {	
		System.out.println("user: " + user );
		return new UserVM(user);
	}
}

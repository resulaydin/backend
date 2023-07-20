package com.hoaxify.ws.udemy.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.udemy.error.ApiError;
import com.hoaxify.ws.udemy.shared.CurrentUser;
import com.hoaxify.ws.udemy.user.vm.UserUpdateVM;
import com.hoaxify.ws.udemy.user.vm.UserVM;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

	@Autowired
	private UserService userService;

	final static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@Valid @RequestBody User user) {
		return userService.add(user);

	}

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public Page<UserVM> getAll(Pageable page, @CurrentUser User user) {
		return userService.getAll(page, user).map(UserVM::new);
	}

	@GetMapping("/users/{username}")
	@ResponseStatus(HttpStatus.OK)
	public UserVM getUser(@PathVariable String username) {
		User user = userService.getByUsername(username);
		System.out.println("getUser: " + user);
		return new UserVM(user);
	}
	
	
	@PutMapping("/users/{username}")
	@PreAuthorize("#username == principal.username")
	public UserVM updateUser(@RequestBody UserUpdateVM userUpdateVM,@PathVariable String username) {
		User userInDB= userService.updateUser(userUpdateVM,username);
		return new UserVM(userInDB);	
	}

}

//Page<User> findByUsernameNot(String username,Pageable page);
//
//"Spring Data JPA" ile yukarıdaki gibi yazarak username' ye sahip olmayanların 
//listesini dönebilmekteyiz. Harika
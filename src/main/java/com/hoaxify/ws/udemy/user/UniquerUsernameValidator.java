package com.hoaxify.ws.udemy.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoaxify.ws.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquerUsernameValidator implements ConstraintValidator<UniquerUsername, String> {

	@Autowired
	UserRepository userRepository;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(userRepository.existsByUsername(value)) {
			return false;
		}
		return true;
	}

}

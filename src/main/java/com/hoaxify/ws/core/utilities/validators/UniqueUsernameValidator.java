package com.hoaxify.ws.core.utilities.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoaxify.ws.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

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

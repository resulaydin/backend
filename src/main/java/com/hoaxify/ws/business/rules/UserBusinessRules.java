package com.hoaxify.ws.business.rules;

import org.springframework.stereotype.Service;

import com.hoaxify.ws.core.utilities.exceptions.BusinessException;
import com.hoaxify.ws.core.utilities.security.PasswordEncoderService;
import com.hoaxify.ws.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserBusinessRules {
	private UserRepository userRepository;
	private PasswordEncoderService passwordEncoderService;

	public void checkIfUserNameExists(String name) {

		if (userRepository.existsByUsername(name)) {
			throw new BusinessException("username already exists");
		}

	}

	public String encodePassword(String password) {

		return passwordEncoderService.encodeMessage(password);
	}

}

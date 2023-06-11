package com.hoaxify.ws.core.utilities.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PasswordEncoderManager implements PasswordEncoderService {
	PasswordEncoder passwordEncoder;

	@Override
	public String encodeMessage(String password) {
		System.out.println(passwordEncoder);
		return passwordEncoder.encode(password);

	}

}
package com.hoaxify.ws.service.concretes;

import org.springframework.stereotype.Service;

import com.hoaxify.ws.core.utilities.mappers.ModelMapperService;
import com.hoaxify.ws.core.utilities.security.PasswordEncoderService;
import com.hoaxify.ws.entities.User;
import com.hoaxify.ws.repository.UserRepository;
import com.hoaxify.ws.service.abstracts.UserService;
import com.hoaxify.ws.service.requests.CreateUserRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private PasswordEncoderService passwordEncoderService;

	@Override
	public void add(CreateUserRequest createUserRequest) {
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		user.setPassword(passwordEncoderService.encodeMessage(user.getPassword()));
		userRepository.save(user);
	}

}

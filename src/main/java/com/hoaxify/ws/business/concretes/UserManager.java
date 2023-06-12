package com.hoaxify.ws.business.concretes;

import org.springframework.stereotype.Service;

import com.hoaxify.ws.business.abstracts.UserService;
import com.hoaxify.ws.business.requests.CreateUserRequest;
import com.hoaxify.ws.business.rules.UserBusinessRules;
import com.hoaxify.ws.core.utilities.mappers.ModelMapperService;
import com.hoaxify.ws.core.utilities.security.PasswordEncoderService;
import com.hoaxify.ws.entities.User;
import com.hoaxify.ws.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private UserBusinessRules businessRules;

	@Override
	public void add(CreateUserRequest createUserRequest) {
		businessRules.checkIfUserNameExists(createUserRequest.getUsername());
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		user.setPassword(businessRules.encodePassword(user.getPassword()));
		System.out.println(user.getPassword());
		userRepository.save(user);
	}

}

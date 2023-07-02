package com.hoaxify.ws.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserRepository;

import lombok.Data;

@Data
@Component
public class UserAuthService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user  == null) {
			throw new UsernameNotFoundException("User not found --");
		}
		return user;
	}

}

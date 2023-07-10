package com.hoaxify.ws.udemy.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return (User) userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Page<User> getAll(Pageable page) {
		return userRepository.findAll(page);
	}

//	void update(UpdateBrandRequest updateBrandRequest);
//	void deleteById(int id);
//	void delete(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse getById(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse  getByName(String name);

}

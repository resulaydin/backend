package com.hoaxify.ws.udemy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.udemy.error.NotFoundException;
import com.hoaxify.ws.udemy.user.vm.UserUpdateVM;

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

//	public User findByUsername(String username) {
//		return userRepository.findByUsername(username);
//	}

//	public Page<UserVM> getAll(Pageable page) {
//		return userRepository.findAll(page).map((user) -> new UserVM(user));
//	}

//	public Page<UserVM> getAll(Pageable page, User user) {
//		return userRepository.findAll(page).map( UserVM::new);
//	}

	public User getByUsername(String username) {
		User inDB = userRepository.findByUsername(username);
		if (inDB == null) {
			throw new NotFoundException();
		}
		return inDB;
	}

	public Page<User> getAll(Pageable page, User user) {
		if (user != null) {
			return userRepository.findByUsernameNot(user.getUsername(), page);
		}
		return userRepository.findAll(page);
	}

	public User updateUser(UserUpdateVM userUpdateVM, String username) {
		User userInDB = getByUsername(username);
		userInDB.setDisplayName(userUpdateVM.getDisplayName());
		if(userUpdateVM.getImage() != null) {
			userInDB.setImage(userUpdateVM.getImage());
		}
		return userRepository.save(userInDB);
	}

//	void update(UpdateBrandRequest updateBrandRequest);
//	void deleteById(int id);
//	void delete(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse getById(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse  getByName(String name);

}

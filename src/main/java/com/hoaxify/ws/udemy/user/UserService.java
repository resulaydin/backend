package com.hoaxify.ws.udemy.user;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.udemy.error.NotFoundException;
import com.hoaxify.ws.udemy.file.FileService;
import com.hoaxify.ws.udemy.user.vm.UserUpdateVM;

import lombok.Data;

@Data
@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private FileService fileService;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder, FileService fileService) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.fileService= fileService;
	}
	
	

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
//			userInDB.setImage(userUpdateVM.getImage());
			String oldImageName = userInDB.getImage();
			try {
				String fileName = fileService.writeBase64EncodedStringToFile(userUpdateVM.getImage());
				userInDB.setImage(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileService.deleteFile(oldImageName);
		}
		return userRepository.save(userInDB);
	}



//	void update(UpdateBrandRequest updateBrandRequest);
//	void deleteById(int id);
//	void delete(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse getById(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse  getByName(String name);

}

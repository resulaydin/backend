package com.hoaxify.ws.udemy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class UserrService {

	@Autowired
	private UserrRepository userrRepository;

	public void add(Userr userr) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		userr.setPassword(bCryptPasswordEncoder.encode(userr.getPassword()));
		userrRepository.save(userr);
	}

	public Userr findByUsername(String username) {
		return userrRepository.findByUsername(username);
	}
//	void update(UpdateBrandRequest updateBrandRequest);
//	void deleteById(int id);
//	void delete(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse getById(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse  getByName(String name);

}

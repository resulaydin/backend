package com.hoaxify.ws.udemy.hoax;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.udemy.user.User;
import com.hoaxify.ws.udemy.user.UserService;


@Service
public class HoaxService {

//	alt + shift + A    -> çok faydalı kesinlikle kulan

	private HoaxRepository hoaxRepository;
	private UserService userService;

	public HoaxService(HoaxRepository hoaxRepository,UserService userService) {
		super();
		this.hoaxRepository = hoaxRepository;
		this.userService= userService;
	}

//	public Hoax add(CreateHoaxRequest createHoaxRequest) {
//		createHoaxRequest.setTimespan(new Date());
//		Hoax hoax = modelMapperService.forReqest().map(createHoaxRequest, Hoax.class);
//		return hoaxRepository.save(hoax);
//	}
	
	public Page<Hoax> getHoaxes(Pageable page) {
	    return  hoaxRepository.findAll(page);

	}

	public void add(User user, Hoax hoax) {
		User inDB = userService.getByUsername(user.getUsername());
		hoax.setContent(hoax.getContent() +" added by "+user.getUsername());
		hoax.setTimestamp(new Date());
		hoax.setUser(inDB);
		hoaxRepository.save(hoax);
	}

	public Page<Hoax> getHoaxesOfUser(String username, Pageable page) {
		User inDB = userService.getByUsername(username);
		return hoaxRepository.findByUser(inDB, page);
	}

	public Page<Hoax> getHoaxesRelative(long id, Pageable page) {
		return hoaxRepository.findByIdLessThan(id, page);
	}

	public Page<Hoax>  getHoaxesOfUserRelative(long id, String username, Pageable page) {
		User inDB = userService.getByUsername(username);
		return hoaxRepository.findByIdLessThanAndUser(id, inDB, page);
	}

	public long getNewHoaxesCount(long id) {
		return hoaxRepository.countByIdGreaterThan(id);
	}

	public Page<Hoax> getNewHoaxes(long id, Pageable page) {
		return hoaxRepository.findByIdGreaterThan(id,page);
	}

	public long getNewHoaxesOfUserRelative(long id,String username) {
		User inDB = userService.getByUsername(username);
		return hoaxRepository.countByIdGreaterThanAndUser(id, inDB);
	}

	public Page<Hoax> getNewHoaxesOfUser(long id, String username, Pageable page) {
		User inDB = userService.getByUsername(username);
		return hoaxRepository.findByIdGreaterThanAndUser(id,inDB,page);
	}

}

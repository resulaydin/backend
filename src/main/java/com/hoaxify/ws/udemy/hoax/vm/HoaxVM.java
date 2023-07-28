package com.hoaxify.ws.udemy.hoax.vm;

import com.hoaxify.ws.udemy.hoax.Hoax;
import com.hoaxify.ws.udemy.user.vm.UserVM;

import lombok.Data;


@Data
public class HoaxVM {
	
	private long id;
	private String content;
	// Bu long hale çevirmektedir.Bu şekilde göndermemizin nedeni client tarafında bir kütüphane kullanacğız.
	private long timestamp;
	private UserVM user; 

	public HoaxVM(Hoax hoax) {
		this.setId(hoax.getId());
		this.setContent(hoax.getContent());
		this.setTimestamp(hoax.getTimestamp().getTime());// Bu long hale çevirmektedir.
		this.setUser(new UserVM(hoax.getUser()));
	}
	
	

}

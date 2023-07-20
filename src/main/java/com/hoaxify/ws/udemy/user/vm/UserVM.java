package com.hoaxify.ws.udemy.user.vm;


import com.hoaxify.ws.udemy.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVM {
	private String username;
	private String displayName;
	private String image;
	
	public UserVM(User user) {
		this.setUsername(user.getUsername());
		this.setDisplayName(user.getDisplayName());
		this.setImage(user.getImage());
	}

}

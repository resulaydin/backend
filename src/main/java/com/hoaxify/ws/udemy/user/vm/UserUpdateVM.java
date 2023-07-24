package com.hoaxify.ws.udemy.user.vm;

import com.hoaxify.ws.udemy.shared.FileType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateVM {

	private String username;
	@NotNull(message = "{hoaxify.diplayName.constraints.NOTNULL.message}")
	@NotBlank(message = "{hoaxify.diplayName.constraints.NOTBLANK.message}")
	@Size(min = 3, max = 255, message = "{hoaxify.diplayName.constraints.SIZE.message}")
	private String displayName;

	@FileType(types = {"jpeg","png"})
	private String image;
	
	
//	@Profile
//	private String image;
}

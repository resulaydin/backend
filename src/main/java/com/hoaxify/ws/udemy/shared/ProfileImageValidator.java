package com.hoaxify.ws.udemy.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoaxify.ws.udemy.file.FileService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProfileImageValidator implements ConstraintValidator<ProfileImage, String> {

	@Autowired
	FileService fileService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) {
			System.out.println(true);
			return true;
		}
		String fileType = fileService.detectType(value);
		if (fileType.equalsIgnoreCase("image/png") || fileType.equalsIgnoreCase("image/jpeg") || fileType.equalsIgnoreCase("image/jpg")) {
			return true;
		}
		return false;
	}

}

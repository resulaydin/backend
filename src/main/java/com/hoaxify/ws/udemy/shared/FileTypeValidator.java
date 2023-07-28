package com.hoaxify.ws.udemy.shared;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hoaxify.ws.udemy.file.FileService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {

	@Autowired
	FileService fileService;

	String[] types;

	@Override
	public void initialize(FileType constraintAnnotation) {
		this.types = constraintAnnotation.types();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			System.out.println(true);
			return true;
		}
		String fileType = fileService.detectType(value);

		for (String supportedFileType : types) {
			if (fileType.contains(supportedFileType)) {
				return true;
			}
		}

		String supportedTypes = Arrays.stream(this.types).collect(Collectors.joining(", "));

		context.disableDefaultConstraintViolation();
		HibernateConstraintValidatorContext hibernateConstraintValidatorContext = context
				.unwrap(HibernateConstraintValidatorContext.class);
		hibernateConstraintValidatorContext.addMessageParameter("types", supportedTypes);
		hibernateConstraintValidatorContext
				.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
				.addConstraintViolation();

		return false;

//		if (fileType.equalsIgnoreCase("image/png") || fileType.equalsIgnoreCase("image/jpeg") || fileType.equalsIgnoreCase("image/jpg")) {
//			return true;
//		}
//		return false;
	}

}

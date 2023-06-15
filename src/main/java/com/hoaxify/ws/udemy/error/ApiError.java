package com.hoaxify.ws.udemy.error;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL )
public class ApiError {
	
	int status;
	String message;
	String path;
	Long timestamp= new Date().getTime();
	HashMap<String,String> validationErrors;
	
	public ApiError(int status,String message,String path) {
		this.status = status;
		this.message = message;
		this.path=path;
	}
}

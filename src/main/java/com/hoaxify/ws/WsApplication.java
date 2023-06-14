package com.hoaxify.ws;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoaxify.ws.business.abstracts.UserService;
import com.hoaxify.ws.business.requests.CreateUserRequest;
import com.hoaxify.ws.core.utilities.error.ApiError;
import com.hoaxify.ws.core.utilities.exceptions.BusinessException;
import com.hoaxify.ws.core.utilities.exceptions.ProblemDetails;
import com.hoaxify.ws.core.utilities.exceptions.ValidationProblemDetails;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestControllerAdvice
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setValidationErrors(new HashMap<>());
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return validationProblemDetails;

	}

//	@ExceptionHandler
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ProblemDetails handleBusinessException(IllegalArgumentException illegalArgumentException) {
//		ProblemDetails problemDetails = new ProblemDetails();
//		problemDetails.setMessage("source cannot be null");
//		return problemDetails;
//		
//	}
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleBusinessException(IllegalArgumentException illegalArgumentException) {
		ApiError apiError = new ApiError(401, "unAuthorizated", "/api/v1.0/WsApplication");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);

	}

	@Bean
	public CommandLineRunner createInitialUserss(UserService userService) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				CreateUserRequest createUserRequest = new CreateUserRequest("user1", "display1", "P1234");
				userService.add(createUserRequest);
			}
		};
	}

	@Bean
	public ModelMapper modelMappers() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoders() {
		return new BCryptPasswordEncoder();
	}
}

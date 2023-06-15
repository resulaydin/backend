package com.hoaxify.ws;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoaxify.ws.udemy.user.Userr;
import com.hoaxify.ws.udemy.user.UserrService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestControllerAdvice
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

//	@ExceptionHandler
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ProblemDetails handleBusinessException(BusinessException businessException) {
//		ProblemDetails problemDetails = new ProblemDetails();
//		problemDetails.setMessage(businessException.getMessage());
//		return problemDetails;
//	}

//	@ExceptionHandler
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ProblemDetails handleBusinessException(MethodArgumentNotValidException methodArgumentNotValidException) {
//		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
//		validationProblemDetails.setValidationErrors(new HashMap<>());
//		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
//			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
//		}
//		return validationProblemDetails;
//
//	}

//	@ExceptionHandler
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ProblemDetails handleBusinessException(IllegalArgumentException illegalArgumentException) {
//		ProblemDetails problemDetails = new ProblemDetails();
//		problemDetails.setMessage("source cannot be null");
//		return problemDetails;
//		
//	}
//	@ExceptionHandler
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ResponseEntity<?> handleBusinessException(IllegalArgumentException illegalArgumentException) {
//		ApirError apiError = new ApirError(401, "unAuthorizated", "/api/v1.0/WsApplication");
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
//
//	}

	@Bean
	public CommandLineRunner createInitialUserss(UserrService userrService) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				Userr userr = new Userr();
				userr.setUsername("userr1");
				userr.setDisplayName("displayr1");
				userr.setPassword("P1234");
				userrService.add(userr);
				
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

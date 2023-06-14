package com.hoaxify.ws.business.responses;

import com.hoaxify.ws.core.utilities.validators.UniqueUsername;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
	@NotNull(message = "{hoaxify.username.constraints.NOTNULL.message}")
	@NotBlank
	@UniqueUsername
	@Size(min = 5, max = 255)
	private String username;
	
	@NotNull
	@NotBlank
	@Size(min = 4, max = 12)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.password.constraints.PATTERN.message}")
	private String password;
}

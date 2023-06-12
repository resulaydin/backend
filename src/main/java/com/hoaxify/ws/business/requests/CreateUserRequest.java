package com.hoaxify.ws.business.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

	@NotNull
	@NotBlank
	private String username;
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	@Size(min = 8, max = 12)
	private String password;
}

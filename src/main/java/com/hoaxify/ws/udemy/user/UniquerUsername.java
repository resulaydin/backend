package com.hoaxify.ws.udemy.user;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({  FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {UniquerUsernameValidator.class })
public @interface UniquerUsername {
	
	String message() default "{hoaxify.message.constraints.UNIQUE.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

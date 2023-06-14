package com.hoaxify.ws.core.utilities.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({  FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueUsernameValidator.class })
public @interface UniqueUsername {
	
	String message() default "{hoaxify.message.constraints.UNIQUE.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

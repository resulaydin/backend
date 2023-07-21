package com.hoaxify.ws.udemy.shared;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { ProfileImageValidator.class })
public @interface ProfileImage {

	String message() default "{hoaxify.constraint.image.PROFILEIMAGE.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

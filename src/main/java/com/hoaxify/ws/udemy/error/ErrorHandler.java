package com.hoaxify.ws.udemy.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ErrorHandler implements ErrorController {

	/**
	 * Default implementation of {@link ErrorAttributes}. Provides the following
	 * attributes when possible:
	 * <ul>
	 * <li>timestamp - The time that the errors were extracted</li>
	 * <li>status - The status code</li>
	 * <li>error - The error reason</li>
	 * <li>exception - The class name of the root exception (if configured)</li>
	 * <li>message - The exception message (if configured)</li>
	 * <li>errors - Any {@link ObjectError}s from a {@link BindingResult} exception
	 * (if configured)</li>
	 * <li>trace - The exception stack trace (if configured)</li>
	 * <li>path - The URL path when the exception was raised</li>
	 * </ul>
	 */

	@Autowired
	private ErrorAttributes errorAttributes;

	ApiError apiError;

	@RequestMapping("/error")
	ApiError handleError(WebRequest webRequest, Exception excption) {
		Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest,
				ErrorAttributeOptions.of(Include.MESSAGE, Include.BINDING_ERRORS));

		String message = (String) attributes.get("message");
		String path = (String) attributes.get("path");
		int status = (int) attributes.get("status");
		System.out.println("Costumized ErrorHandler : )");

		apiError = new ApiError(status, message, path);

		if (attributes.containsKey("errors")) {
			Map<String, String> validationErrors = new HashMap<>();
			@SuppressWarnings("unchecked")
			List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");

			for (FieldError fieldError : fieldErrors) {
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			apiError.setValidationErrors(validationErrors);
		}

		return apiError;
	}

}

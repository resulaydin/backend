package com.hoaxify.ws.core.utilities.exceptions;




public class BusinessException extends RuntimeException{
	
	/**
	 * Yeni versionlarda serialize olmadığı söylendi. Ayrıca bunlar Markup Class' lardır.
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
}

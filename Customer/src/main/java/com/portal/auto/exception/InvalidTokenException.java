package com.portal.auto.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {

	 public InvalidTokenException(String username) {
	        super(format("Token invalido para o User %s", username));
	    }
}

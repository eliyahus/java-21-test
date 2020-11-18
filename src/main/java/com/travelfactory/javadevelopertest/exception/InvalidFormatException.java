package com.travelfactory.javadevelopertest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	
    public InvalidFormatException(String message) {
    	super(message);
    }
}

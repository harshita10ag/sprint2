package com.cg.corona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * @author HARSHITA AGARWAL
 * if patient id does not exist they pass exception 
 * service not found handle the exception if occur
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7079839739955007353L;

	public ServiceNotFoundException(String message) {
		super(message);
		
	}

}

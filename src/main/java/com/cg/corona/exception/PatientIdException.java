package com.cg.corona.exception;

public class PatientIdException extends Exception {
	
	public PatientIdException() {
		super();
	}
	
	public PatientIdException(String message) {
		super(message);
		System.out.println(message);
	}

}

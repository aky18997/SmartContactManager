package com.scm.helpers;

import org.aspectj.apache.bcel.ExceptionConstants;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found !!");
		
	}

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
	
	
	

}

package com.aditya.myapp.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2064062647835470065L;

	public DataNotFoundException(String message){
		super(message);
	}
}

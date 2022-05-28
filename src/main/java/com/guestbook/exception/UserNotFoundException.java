package com.guestbook.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4436993651061907923L;

	public UserNotFoundException(String errMsg, Exception exception) {
		super(errMsg, exception);
	}
	
	public UserNotFoundException(String errMsg) {
		super(errMsg);
	}
	
	public UserNotFoundException(){
		
	}

}

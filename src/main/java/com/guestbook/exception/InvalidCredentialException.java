package com.guestbook.exception;

public class InvalidCredentialException extends RuntimeException {

	private static final long serialVersionUID = 4436993651061907923L;

	public InvalidCredentialException(String errMsg, Exception exception) {
		super(errMsg, exception);
	}
	
	public InvalidCredentialException(String errMsg) {
		super(errMsg);
	}

}

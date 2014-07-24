package com.geminicode.site.exception;

public class AlreadyExistException extends Exception {

	String message = "";

	public AlreadyExistException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

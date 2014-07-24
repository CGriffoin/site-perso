package com.geminicode.site.exception;

public class ForbiddenException extends Exception {

	String message = "";

	public ForbiddenException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

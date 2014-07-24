package com.geminicode.site.exception;

public class CannotDeleteException extends Exception {

	String message = "";

	public CannotDeleteException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

package com.geminicode.site.exception;

public class BadFormatException extends Exception {

	String message = "";

	public BadFormatException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

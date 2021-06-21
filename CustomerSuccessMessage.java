package com.cg.salon.dto;

public class CustomerSuccessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomerSuccessMessage(String message) {
		super();
		this.message = message;
	}
}

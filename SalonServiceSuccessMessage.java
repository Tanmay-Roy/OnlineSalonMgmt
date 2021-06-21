package com.cg.salon.dto;

public class SalonServiceSuccessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SalonServiceSuccessMessage(String message) {
		super();
		this.message = message;
	}

}
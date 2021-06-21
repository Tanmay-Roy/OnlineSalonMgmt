package com.cg.salon.dto;

public class AppointmentSuccessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppointmentSuccessMessage(String message) {
		super();
		this.message = message;
	}
}

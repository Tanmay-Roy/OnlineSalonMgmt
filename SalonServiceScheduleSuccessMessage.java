package com.cg.salon.dto;

public class SalonServiceScheduleSuccessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SalonServiceScheduleSuccessMessage(String message) {
		super();
		this.message = message;
	}

}
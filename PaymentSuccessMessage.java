package com.cg.salon.dto;

public class PaymentSuccessMessage {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PaymentSuccessMessage(String message) {
		super();
		this.message = message;
	}

}

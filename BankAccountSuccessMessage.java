package com.cg.salon.dto;

public class BankAccountSuccessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BankAccountSuccessMessage(String message) {
		super();
		this.message = message;
	}

}

package com.cg.salon.dto;

import java.util.List;

public class AppointmentErrorMessage {

	public String status;
	public String message;

	private List<String> messages;

	public AppointmentErrorMessage(String status, List<String> messages) {
		super();
		this.status = status;
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppointmentErrorMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}

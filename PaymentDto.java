package com.cg.salon.dto;

public class PaymentDto {

	private Integer paymentId;

	private String type;

	private String status;

	private Integer appointmentId;

	private Integer cvvNo;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getCvvNo() {
		return cvvNo;
	}

	public void setCvvNo(Integer cvvNo) {
		this.cvvNo = cvvNo;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentDto() {
		super();

	}

	public PaymentDto(Integer paymentId, String type, String status, Integer appointmentId, Integer cvvNo) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.appointmentId = appointmentId;
		this.cvvNo = cvvNo;
	}

}
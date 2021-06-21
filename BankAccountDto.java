package com.cg.salon.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;

import javax.validation.constraints.Pattern;

public class BankAccountDto {

	private String cardnumber;

	@Min(value = 500, message = "Bank balance must be atleast 500")
	private Double amount;

	private String bankName;

	private String cardName;

	@FutureOrPresent(message = "Expiry date must not be in the past")
	private LocalDate expiryDate;

	private Integer cvvNo;
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
	private String ifscNo;

	public BankAccountDto() {

		super();
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public BankAccountDto(String cardnumber, Double amount, String bankName, String cardName, LocalDate expiryDate,
			Integer cvvNo, String ifscNo) {
		super();
		this.cardnumber = cardnumber;
		this.amount = amount;
		this.bankName = bankName;
		this.cardName = cardName;
		this.expiryDate = expiryDate;
		this.cvvNo = cvvNo;
		this.ifscNo = ifscNo;
	}

	public String getIfscNo() {
		return ifscNo;
	}

	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCvvNo() {
		return cvvNo;
	}

	public void setCvvNo(Integer cvvNo) {
		this.cvvNo = cvvNo;
	}

}

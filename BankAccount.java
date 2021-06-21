package com.cg.salon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cg_bank_acc")
public class BankAccount {

	@Column(name = "bank_card_no", length = 16, nullable = false, unique = true)
	private String cardNumber;

	@Column(name = "bank_amt")
	private Double amount;

	@Column(name = "bank_name", length = 25, nullable = false)
	private String bankName;

	@Column(name = "bank_card_name", length = 25, nullable = false)
	private String cardName;

	@Column(name = "bank_exp_date")
	private LocalDate expiryDate;

	@Id
	@Column(name = "bank_cvv_no")
	private Integer cvvNo;

	@Column(name = "bank_ifsc_no")
	private String ifscNo;

	@Override
	public String toString() {
		return " " + cardNumber + " " + amount + " " + bankName + " " + cardName + " " + expiryDate + " " + cvvNo + " "
				+ ifscNo + " ";
	}

	public String getIfscNo() {
		return ifscNo;
	}

	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}

	public String getCardName() {
		return cardName;
	}

	public BankAccount() {
		super();

	}

	public BankAccount(String cardNumber, Double amount, String bankName, String cardName, LocalDate expiryDate,
			Integer cvvNo, String ifscNo) {
		super();
		this.cardNumber = cardNumber;
		this.amount = amount;
		this.bankName = bankName;
		this.cardName = cardName;
		this.expiryDate = expiryDate;
		this.cvvNo = cvvNo;
		this.ifscNo = ifscNo;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}

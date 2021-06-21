package com.cg.salon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cg_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	@SequenceGenerator(name = "seq1", sequenceName = "cust_seq1", allocationSize = 1)
	@Column(name = "cust_user_id")
	private Integer userId;

	@Column(name = "cust_name", length = 25, nullable = false)
	private String name;

	@Column(name = "cust_email", length = 25, nullable = false, unique = true)
	private String email;

	@Column(name = "cust_contact", length = 25, nullable = false)
	private String contactNo;

	@Column(name = "cust_dob")
	private LocalDate dob;

	@Column(name = "cust_doorno", length = 25, nullable = false)
	private String doorNo;

	@Column(name = "cust_street", length = 25, nullable = false)
	private String street;

	@Column(name = "cust_area", length = 25, nullable = false)
	private String area;

	@Column(name = "cust_city", length = 25, nullable = false)
	private String city;

	@Column(name = "cust_state", length = 25, nullable = false)
	private String state;

	@Column(name = "cust_pin")
	private Integer pincode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Customer() {
		super();
	}

	public Customer(Integer userId, String name, String email, String contactNo, LocalDate dob, String doorNo,
			String street, String area, String city, String state, Integer pincode) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return " " + userId + " " + name + " " + email + " " + contactNo + " " + dob + " " + doorNo + " " + street + " "
				+ area + " " + city + " " + state + " " + pincode + " ";
	}
}
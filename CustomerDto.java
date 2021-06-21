package com.cg.salon.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDto {

	private Integer userId;

	@NotBlank(message = "Customer name must not be blank")
	@Pattern(regexp = "([a-zA-Z]+)|([a-zA-Z]+[\\s][a-zA-Z]+)", message = "customer name must contain only alphabets")
	private String name;

	@NotBlank(message = "Customer email must not be blank")
	private String email;

	@NotBlank(message = "Customer contact no. must not be blank")
	private String contactNo;

	private LocalDate dob;

	@NotBlank(message = "Customer door no. must not be blank")
	private String doorNo;

	@NotBlank(message = "Customer street must not be blank")
	private String street;

	@NotBlank(message = "Customer area must not be blank")
	private String area;

	@NotBlank(message = "Customer city must not be blank")
	private String city;

	@NotBlank(message = "Customer state must not be blank")
	private String state;

	private Integer pincode;

	public CustomerDto() {

	}

	public CustomerDto(Integer userId, String name, String email, String contactNo, LocalDate dob, String doorNo,
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

}

package com.cg.salon.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.cg.util.SalonConstants;

public class SalonServiceDto {

	private Integer serviceId;

	@NotBlank(message = SalonConstants.SERVICE_NAME_NOT_BLANK)
	@Pattern(regexp = SalonConstants.SERVICE_NAME_REGULAR_EXPRESSION, message = SalonConstants.SERVICE_NAME_MESSAGE)
	private String serviceName;

	@Min(value= SalonConstants.MIN_SERVICE_PRICE, message= SalonConstants.SERVICE_PRICE_MESSAGE)
	private Integer servicePrice;

	private String serviceDuration;

	@Max(value = SalonConstants.MAX_SERVICE_DISCOUNT, message = SalonConstants.SERVICE_DISCOUNT_MESSAGE)
	private Integer discount;

	private String salonAddress;

	private String salonLocation;

	private String salonCentreName;

	public SalonServiceDto() {
		
		super();
	}
	
	

	public SalonServiceDto(Integer serviceId,String serviceName,Integer servicePrice,String serviceDuration,Integer discount,
			String salonAddress, String salonLocation, String salonCentreName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.serviceDuration = serviceDuration;
		this.discount = discount;
		this.salonAddress = salonAddress;
		this.salonLocation = salonLocation;
		this.salonCentreName = salonCentreName;
	}

	


	public SalonServiceDto(String serviceName,Integer servicePrice,String serviceDuration,Integer discount,
			String salonAddress, String salonLocation, String salonCentreName) {
		super();
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.serviceDuration = serviceDuration;
		this.discount = discount;
		this.salonAddress = salonAddress;
		this.salonLocation = salonLocation;
		this.salonCentreName = salonCentreName;
	}



	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getSalonAddress() {
		return salonAddress;
	}

	public void setSalonAddress(String salonAddress) {
		this.salonAddress = salonAddress;
	}

	public String getSalonLocation() {
		return salonLocation;
	}

	public void setSalonLocation(String salonLocation) {
		this.salonLocation = salonLocation;
	}

	public String getSalonCentreName() {
		return salonCentreName;
	}

	public void setSalonCentreName(String salonCentreName) {
		this.salonCentreName = salonCentreName;
	}

}
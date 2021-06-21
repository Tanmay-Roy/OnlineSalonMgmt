package com.cg.salon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cg_salon_service")
public class SalonService {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	@SequenceGenerator(name = "seq1", sequenceName = "salon_service_seq1", allocationSize = 1)
	@Column(name = "salon_service_id")
	private Integer serviceId;

	@Column(name = "service_name", length = 25, nullable = false)
	private String serviceName;

	@Column(name = "service_price")
	private Integer servicePrice;

	@Column(name = "service_duration", length = 25, nullable = false)
	private String serviceDuration;

	@Column(name = "service_discount")
	private Integer discount;

	@Column(name = "salon_address")
	private String salonAddress;

	@Column(name = "salon_location")
	private String salonLocation;

	@Column(name = "salon_centre_name")
	private String salonCentreName;

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

	public SalonService() {
		super();
	}
	
	

	public SalonService(Integer serviceId, String serviceName, Integer servicePrice, String serviceDuration,
			Integer discount, String salonAddress, String salonLocation, String salonCentreName) {
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

	@Override
	public String toString() {
		return " " + serviceId + " " + serviceName + " " + servicePrice + " " + serviceDuration + " " + discount + " ";
	}

}

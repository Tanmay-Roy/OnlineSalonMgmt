package com.cg.salon.dto;

import java.time.LocalDate;


public class SalonServiceScheduleDto {

	private Integer noofappointments;
	private LocalDate scheduleDate;
	private String slot;
	private Integer salonServiceId;
	private String scheduleStatus;

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public SalonServiceScheduleDto() {
		super();
	}

	public Integer getSalonServiceId() {
		return salonServiceId;
	}

	public void setSalonServiceId(Integer salonServiceId) {
		this.salonServiceId = salonServiceId;
	}

	public Integer getNoofappointments() {
		return noofappointments;
	}

	public void setNoofappointments(Integer noofappointments) {
		this.noofappointments = noofappointments;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

}

package com.cg.salon.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cg_salon_service_schedule")
public class SalonServiceSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	@SequenceGenerator(name = "seq1", sequenceName = "schedule_seq1", allocationSize = 1)
	@Column(name = "service_schedule_id")
	private Integer serviceScheduleId;

	@Column(name = "no_of_appointments")
	private Integer noofappointments;

	@Column(name = "schedule_date")
	private LocalDate scheduleDate;

	@Column(name = "schedule_status", length = 25)
	private String scheduleStatus;

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	@Column(name = "schedule_slot", length = 10, nullable = false)
	private String slot;

	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "salon_service_id")
	private SalonService salonService;

	public Integer getServiceScheduleId() {
		return serviceScheduleId;
	}

	public void setServiceScheduleId(Integer serviceScheduleId) {
		this.serviceScheduleId = serviceScheduleId;
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

	@Override
	public String toString() {
		return " " + serviceScheduleId + " " + scheduleDate + " " + slot + " ";
	}

	public SalonService getSalonService() {
		return salonService;
	}

	public Integer getNoofappointments() {
		return noofappointments;
	}

	public void setNoofappointments(Integer noofappointments) {
		this.noofappointments = noofappointments;
	}

	public void setSalonService(SalonService salonService) {
		this.salonService = salonService;
	}

	public SalonServiceSchedule() {
		super();
	}

	public SalonServiceSchedule(Integer serviceScheduleId, Integer noofappointments, LocalDate scheduleDate,
			String scheduleStatus, String slot, SalonService salonService) {
		super();
		this.serviceScheduleId = serviceScheduleId;
		this.noofappointments = noofappointments;
		this.scheduleDate = scheduleDate;
		this.scheduleStatus = scheduleStatus;
		this.slot = slot;
		this.salonService = salonService;
	}
	
	

}

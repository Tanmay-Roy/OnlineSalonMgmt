package com.cg.salon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.salon.entity.Appointment;
import com.cg.salon.exceptions.AppointmentNotFoundException;

@Repository("appointmentdao")
public interface IAppointmentDao extends JpaRepository<Appointment, Integer> {

	@Query("from Appointment a inner join fetch a.customer c where c.userId=:custId")
	public List<Appointment> findByCustomerId(@Param("custId") int custId) throws AppointmentNotFoundException;

	@Query("from Appointment a inner join fetch a.salonServiceSchedule s where s.serviceScheduleId=:sid")
	public List<Appointment> findByScheduleId(@Param("sid") int serviceScheduleId) throws AppointmentNotFoundException;
	
	public List<Appointment> findByAppointmentId(Integer appointmentId) throws AppointmentNotFoundException;

	public List<Appointment> findAll();
}

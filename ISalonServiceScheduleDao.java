package com.cg.salon.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.salon.entity.SalonServiceSchedule;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;

@Repository("salonservicescheduledao")
public interface ISalonServiceScheduleDao extends JpaRepository<SalonServiceSchedule, Integer> {

	@Query("from SalonServiceSchedule s inner join fetch s.salonService t where t.serviceId=:sid")
	public List<SalonServiceSchedule> viewSalonServiceScheduleByServiceId(@Param("sid") int serid)
			throws SalonServiceScheduleNotFoundException;

	public List<SalonServiceSchedule> findByScheduleDate(LocalDate scheduleDate);

	public Integer findByNoofappointments(Integer noofappointments);
}

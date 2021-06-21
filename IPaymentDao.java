package com.cg.salon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.salon.entity.Payment;
import com.cg.salon.exceptions.PaymentNotFoundException;

@Repository("paymentdao")
public interface IPaymentDao extends JpaRepository<Payment, Integer> {

	@Query("from Payment p inner join fetch p.appointment a where a.appointmentId=:aid")
	public List<Payment> viewPaymentByAppointmentId(@Param("aid") int aid) throws PaymentNotFoundException;

}
package com.cg.salon.service;

import java.util.List;

import com.cg.salon.dto.PaymentDto;
import com.cg.salon.entity.Payment;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.salon.exceptions.PaymentNotFoundException;

public interface IPaymentService {

	public Long addPayment(PaymentDto dto)
			throws PaymentNotFoundException, AppointmentNotFoundException, BankAccountNotFoundException;

	public Payment viewPaymentByPaymentId(Integer pid) throws PaymentNotFoundException;

	public List<Payment> viewAllPayment() throws PaymentNotFoundException;

}

package com.cg.salon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.IAppointmentDao;
import com.cg.salon.dao.IBankAccountDao;
import com.cg.salon.dao.IPaymentDao;
import com.cg.salon.dto.PaymentDto;
import com.cg.salon.entity.Appointment;
import com.cg.salon.entity.BankAccount;

import com.cg.salon.entity.Payment;

import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.BankAccountNotFoundException;

import com.cg.salon.exceptions.PaymentNotFoundException;

import com.cg.util.SalonConstants;

/*
 * @Author - Ankush Mukherjee
 * Description - This service class contains the service implementations regarding Payment Management
 */
@Service("paymentservice")
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IPaymentDao paymentdao;
	@Autowired
	private IAppointmentDao appdao;
	@Autowired
	private IBankAccountDao bankaccdao;

	
	/*
	 * Method Name - addPayment
	 *  Return Type - Integer 
	 *  Parameter - Instance of PaymentDto
	 *  Description - Add a new Payment Details
	 */  
	@Override
	@Transactional
	public Long addPayment(PaymentDto dto)
			throws PaymentNotFoundException, AppointmentNotFoundException, BankAccountNotFoundException {

		Optional<Appointment> optsalon = appdao.findById(dto.getAppointmentId());
		if (!optsalon.isPresent())
			throw new AppointmentNotFoundException(SalonConstants.APPOINTMENT_NOT_FOUND);

		Optional<BankAccount> optbank = bankaccdao.findById(dto.getCvvNo());
		if (!optbank.isPresent())
			throw new BankAccountNotFoundException(SalonConstants.BANK_ACCOUNT_NOT_FOUND);

		Payment payment = new Payment();

		payment.setPaymentId(dto.getPaymentId());
		payment.setType(dto.getType());
		payment.setStatus(SalonConstants.PAYMENT_ADDED);
		payment.setAppointment(optsalon.get());
		payment.setBankAccount(optbank.get());
		Payment savedpayment = paymentdao.save(payment);
		return savedpayment.getPaymentId();
	}
	
	/*
	 * Method Name - viewPaymentByPaymentId 
	 * Return Type -  Payment
	 * Parameter - Payment Id 
	 * Description - returns the instance for the Payment corresponding to the given Payment Id 
	 * Throws - PaymentNotFoundException, if the Payment id does not exist
	 */

	@Override
	public Payment viewPaymentByPaymentId(Integer pid) throws PaymentNotFoundException {
		Optional<Payment> optservice = paymentdao.findById(pid);
		if (!optservice.isPresent())
			throw new PaymentNotFoundException(SalonConstants.PAYMENT_NOT_EXIST + pid);
		return optservice.get();
	}

	/*
	 * Method Name - viewAllPayment 
	 * Return Type -  List
	 * Parameter - no parameter
	 * Description - returns all the payment details 
	 * Throws - PaymentNotFoundException, if the Payment List does not contain any values
	 */
	@Override
	public List<Payment> viewAllPayment() throws PaymentNotFoundException {
		List<Payment> lst = paymentdao.findAll();
		if (lst.isEmpty())
			throw new PaymentNotFoundException(SalonConstants.PAYMENT_NOT_FOUND);
		return lst;

	}

}
package com.cg.salon.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.salon.dto.PaymentDto;
import com.cg.salon.dto.PaymentSuccessMessage;

import com.cg.salon.entity.Payment;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.salon.exceptions.PaymentNotFoundException;

import com.cg.salon.exceptions.ValidatePaymentException;

import com.cg.salon.service.IPaymentService;
import com.cg.util.SalonConstants;

/*
 * @Author - Ankush Mukherjee
 * Description - This service class contains the service implementations regarding Payment Management
 */
@RestController
public class PaymentRestController {

	@Autowired
	private IPaymentService service;

	
	/*
	 * Method Name - addPayment
	 *  Return Type - Integer 
	 *  Parameter - Instance of PaymentDto
	 *  Description - Add a new Payment Details
	 */  

	@PostMapping("addpaymentservice")
	public PaymentSuccessMessage addPayment(@Valid @RequestBody PaymentDto paymentdto, BindingResult br)
			throws ValidatePaymentException, PaymentNotFoundException, AppointmentNotFoundException,
			BankAccountNotFoundException {

		if (br.hasErrors())
			throw new ValidatePaymentException(br.getFieldErrors());
		service.addPayment(paymentdto);

		return new PaymentSuccessMessage(SalonConstants.PAYMENT_ADDED);

	}

	/*
	 * Method Name - viewPaymentByPaymentId 
	 * Return Type -  Payment
	 * Parameter - Payment Id 
	 * Description - returns the instance for the Payment corresponding to the given Payment Id 
	 * Throws - PaymentNotFoundException, if the Payment id does not exist
	 */
	@GetMapping("viewbypaymentid/{pid}")
	public Payment viewPaymentByPaymentId(@PathVariable("pid") Integer paymentId) throws PaymentNotFoundException {
		return service.viewPaymentByPaymentId(paymentId);
	}

	
	/*
	 * Method Name - viewAllPayment 
	 * Return Type -  List
	 * Parameter - no parameter
	 * Description - returns all the payment details 
	 * Throws - PaymentNotFoundException, if the Payment List does not contain any values
	 */
	@GetMapping("viewallpayment")
	public List<Payment> viewAllPayment() throws PaymentNotFoundException {
		return service.viewAllPayment();
	}

}

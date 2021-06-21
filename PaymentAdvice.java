package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.salon.dto.PaymentErrorMessage;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.salon.exceptions.ValidatePaymentException;

@RestControllerAdvice
public class PaymentAdvice extends GlobalAdvice {
	@ExceptionHandler(BankAccountNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public PaymentErrorMessage handleExceptionEmpNotFound(BankAccountNotFoundException ex) {
		return new PaymentErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());

	}

	@ExceptionHandler(ValidatePaymentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public PaymentErrorMessage handleException2(ValidatePaymentException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new PaymentErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

	@ExceptionHandler(AppointmentNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public PaymentErrorMessage handleExceptionAppointmentNotFound(AppointmentNotFoundException ex) {
		return new PaymentErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
}

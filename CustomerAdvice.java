package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.salon.dto.CustomerErrorMessage;
import com.cg.salon.exceptions.CustomerNotFoundException;
import com.cg.salon.exceptions.ValidateCustomerException;

public class CustomerAdvice extends GlobalAdvice {

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public CustomerErrorMessage handleExceptionCustNotFound(CustomerNotFoundException ex) {
		return new CustomerErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(ValidateCustomerException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public CustomerErrorMessage handleExceptionNext(ValidateCustomerException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());

		return new CustomerErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}
}

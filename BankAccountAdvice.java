package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.salon.dto.BankAccountErrorMessage;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.salon.exceptions.ValidateBankAccountException;

@RestControllerAdvice
public class BankAccountAdvice extends GlobalAdvice {

	@ExceptionHandler(BankAccountNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public BankAccountErrorMessage handleExceptionEmpNotFound(BankAccountNotFoundException ex) {
		return new BankAccountErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(ValidateBankAccountException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public BankAccountErrorMessage handleException2(ValidateBankAccountException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new BankAccountErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

}

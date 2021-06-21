package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.salon.dto.SalonServiceErrorMessage;
import com.cg.salon.exceptions.SalonServiceNotFoundException;
import com.cg.salon.exceptions.ValidateSalonServiceException;

@RestControllerAdvice
public class SalonAdvice extends GlobalAdvice {

	@ExceptionHandler(SalonServiceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public SalonServiceErrorMessage handleExceptionEmpNotFound(SalonServiceNotFoundException ex) {
		return new SalonServiceErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(ValidateSalonServiceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public SalonServiceErrorMessage handleException2(ValidateSalonServiceException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new SalonServiceErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

}

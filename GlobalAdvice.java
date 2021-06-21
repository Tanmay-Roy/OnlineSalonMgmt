package com.cg.salon.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.salon.dto.SalonServiceErrorMessage;

@RestControllerAdvice
public class GlobalAdvice {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public SalonServiceErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new SalonServiceErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern");
		return new SalonServiceErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public SalonServiceErrorMessage handleException3(HttpMessageConversionException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new SalonServiceErrorMessage(HttpStatus.BAD_REQUEST.toString(),
					"Invalid Date Pattern , follow yyyy-M-d");
		return new SalonServiceErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}
}

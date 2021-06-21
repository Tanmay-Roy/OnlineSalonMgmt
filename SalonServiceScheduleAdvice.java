package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.salon.dto.SalonServiceScheduleErrorMessage;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;
import com.cg.salon.exceptions.ValidateSalonServiceScheduleException;

@RestControllerAdvice
public class SalonServiceScheduleAdvice extends GlobalAdvice {

	@ExceptionHandler(SalonServiceScheduleNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public SalonServiceScheduleErrorMessage handleExceptionEmpNotFound(SalonServiceScheduleNotFoundException ex) {
		return new SalonServiceScheduleErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(ValidateSalonServiceScheduleException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public SalonServiceScheduleErrorMessage handleException2(ValidateSalonServiceScheduleException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new SalonServiceScheduleErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

}

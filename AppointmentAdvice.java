package com.cg.salon.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.ServiceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.salon.dto.AppointmentErrorMessage;
import com.cg.salon.exceptions.AppointmentCancelException;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.ValidateAppointmentException;

@RestControllerAdvice
public class AppointmentAdvice extends GlobalAdvice {

	@ExceptionHandler(AppointmentNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public AppointmentErrorMessage handleExceptionAppointmentNotFound(AppointmentNotFoundException ex) {
		return new AppointmentErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(AppointmentCancelException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public AppointmentErrorMessage handleExceptionAppointmentCancel(AppointmentCancelException ex) {
		return new AppointmentErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(ValidateAppointmentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public AppointmentErrorMessage handleException2(ValidateAppointmentException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new AppointmentErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

	@ExceptionHandler(ServiceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public AppointmentErrorMessage handleExceptionServiceNotFound(ServiceNotFoundException ex) {
		return new AppointmentErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

}

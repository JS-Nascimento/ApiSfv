package dev.jstec.apisfv.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.jstec.apisfv.exception.BusinessRoleException;
import dev.jstec.apisfv.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRoleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBusinessRoleException(BusinessRoleException exception) {
		
		String errorMessage = exception.getMessage();
		return new ApiErrors(errorMessage);
		
	}

}

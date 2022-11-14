package dev.jstec.apisfv.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.jstec.apisfv.exception.BusinessRoleException;
import dev.jstec.apisfv.exception.OrderNotFoundException;
import dev.jstec.apisfv.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRoleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBusinessRoleException(BusinessRoleException exception) {
		
		String errorMessage = exception.getMessage();
		return new ApiErrors(errorMessage);
		
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleOrderNotFoundException(OrderNotFoundException exception) {
		
		String errorMessage = exception.getMessage();
		return new ApiErrors(errorMessage);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		
		List<String> errors = exception.getBindingResult().getAllErrors().stream()
							.map( errHandle -> errHandle.getDefaultMessage())
							.collect(Collectors.toList());
		
		return new ApiErrors(errors);
	}

}

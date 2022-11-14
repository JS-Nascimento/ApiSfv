package dev.jstec.apisfv.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;




public class ApiErrors {
	@Getter
	private List<String> errors;
	
	
	
	public ApiErrors(String errorsMessage) {
		 this.errors = Arrays.asList(errorsMessage);
	}



	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	

}

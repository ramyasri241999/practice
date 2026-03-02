package com.epam.practice.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // only works for rest apis but not simple java. nly works for: Exceptions thrown inside Spring MVC request lifecycle
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex){
		System.out.println("Exeption displayed in handleAccountNotFound "+ex.getMessage());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}

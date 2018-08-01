package com.managment.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.managment.employee.model.ReponseDetail;

@RestControllerAdvice
public class LogInExceptionHandler {
	
	@ExceptionHandler(LogInException.class)
	public ResponseEntity<ReponseDetail> throwDbException (LogInException ex) {
		ReponseDetail respObj= new ReponseDetail(HttpStatus.BAD_REQUEST.toString(),"failure",ex.getMessage(),null); 
		
		return new ResponseEntity<>(respObj,HttpStatus.BAD_REQUEST);
		
	}

}

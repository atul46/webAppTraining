package com.managment.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.managment.employee.model.ReponseDetail;

@RestControllerAdvice
public class DbExceptionHandler {
	
	@ExceptionHandler(DbException.class)
	public ResponseEntity<ReponseDetail> throwDbException (DbException ex) {
		ReponseDetail respObj= new ReponseDetail(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"failure",ex.getMessage(),null); 
		
		return new ResponseEntity<>(respObj,HttpStatus.NOT_FOUND);
		
	}

}

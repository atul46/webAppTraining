package com.managment.employee.exception;

public class ServiceException extends RuntimeException {

	public ServiceException (String response) {
		super(response);
	}
}

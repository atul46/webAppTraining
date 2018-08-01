package com.managment.employee.exception;

public class LogInException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogInException(String response) {
		 super(response);
	 }
}

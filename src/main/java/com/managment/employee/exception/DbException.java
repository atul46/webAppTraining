package com.managment.employee.exception;

public class DbException extends RuntimeException {
	
	public DbException (String response) {
		super(response);
	}

}

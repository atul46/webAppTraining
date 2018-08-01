package com.managment.employee.model;

import java.io.Serializable;

public class ReponseDetail implements Serializable {
	
	private String statusCode;
	private String status;
	private String statusMessage;
	private Iterable<EmployeeDetail> empDetailIter;
	
   public ReponseDetail(String statusCode,String status,
			String statusMessage,Iterable<EmployeeDetail> empIter) {
		setStatusCode(statusCode);
		setStatus(status);
		setStatusMessage(statusMessage);
		setEmpDetailIter(empIter);
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Iterable<EmployeeDetail> getEmpDetailIter() {
		return empDetailIter;
	}
	public void setEmpDetailIter(Iterable<EmployeeDetail> empDetailIter) {
		this.empDetailIter = empDetailIter;
	}

}

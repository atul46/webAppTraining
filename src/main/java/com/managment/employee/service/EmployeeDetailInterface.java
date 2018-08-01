package com.managment.employee.service;

import org.springframework.validation.BindingResult;

import com.managment.employee.model.EmployeeDetail;
import com.managment.employee.model.ReponseDetail;

public interface EmployeeDetailInterface {
	
	public Iterable<EmployeeDetail> getAllEmployeeDetail();
	public EmployeeDetail getEmployeeDetailById(long id);
	public boolean saveEmployeeDetail(EmployeeDetail empDetail,BindingResult result);
	public ReponseDetail deleteEmployeeDetailbyId(long id);
	public ReponseDetail checkLogIn(String userName,String password);

}

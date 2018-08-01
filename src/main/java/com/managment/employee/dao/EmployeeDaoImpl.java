package com.managment.employee.dao;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.managment.employee.exception.DbException;
import com.managment.employee.model.EmployeeDetail;

@Component
public class EmployeeDaoImpl implements EmployeeDetailDaoInterface {
	
	@Autowired
	EmployeeDetailRepo employeeDetailRepo;

	@Override
	public Iterable<EmployeeDetail> getAllEmployees()  throws DbException{
		try {
		Iterable<EmployeeDetail> empDetailIterable=employeeDetailRepo.findAll();
		return empDetailIterable;
		}
		catch(Exception e) {
			throw new DbException("There is some issue at server side. Please check the log");
		}
		
	}

	@Override
	public EmployeeDetail getEmployeeDetailById(long id) throws DbException {
		try {
		Optional<EmployeeDetail> empDetail=employeeDetailRepo.findById(id); 
		if(empDetail.isPresent()) {
			return empDetail.get();
		}
		}
		catch(Exception e) {
			throw new DbException("There is some issue at server side. Please check the log");
		}
		
		return null;
		
		
	}

	@Override
	
	public String insertEmployeeDetail(EmployeeDetail employeeDetail) throws DbException {
		try {
		employeeDetailRepo.save(employeeDetail);
		}
		catch(Exception e) {
			throw new DbException("There is some issue at server side. Please check the log");
		}
		return null;
	}

	@Override
	
	public String deleteEmployeeDetail(long id) throws DbException {
		try {
		employeeDetailRepo.deleteById(id);
		}
		catch(Exception e) {
			throw new DbException("There is some issue at server side. Please check the log");
		}
		return null;
	}

	@Override
	public EmployeeDetail getByUserName(String userName) throws DbException {
		
		try {
		EmployeeDetail empDetail=employeeDetailRepo.findEmployeeDetailByUserName(userName);
		return empDetail;
		}
		catch(Exception e) {
			throw new DbException("There is some issue at server side. Please check the log");
		}
	}

}

package com.managment.employee.dao;

import org.springframework.data.repository.CrudRepository;

import com.managment.employee.model.EmployeeDetail;

public interface EmployeeDetailRepo extends CrudRepository<EmployeeDetail, Long> {

	EmployeeDetail findEmployeeDetailByUserName(String userName);
}

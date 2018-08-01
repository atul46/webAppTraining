package com.managment.employee.dao;

import com.managment.employee.model.EmployeeDetail;

public interface EmployeeDetailDaoInterface {
    public Iterable<EmployeeDetail> getAllEmployees();
    public EmployeeDetail getEmployeeDetailById(long id);
    public String insertEmployeeDetail(EmployeeDetail employeeDetail);
    public String deleteEmployeeDetail(long id);
    public EmployeeDetail getByUserName(String userName);
}

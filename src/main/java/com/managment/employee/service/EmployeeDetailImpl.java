package com.managment.employee.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.managment.employee.dao.EmployeeDetailDaoInterface;
import com.managment.employee.exception.DbException;
import com.managment.employee.exception.LogInException;
import com.managment.employee.exception.ServiceException;
import com.managment.employee.model.EmployeeDetail;
import com.managment.employee.model.ReponseDetail;

@Component
public class EmployeeDetailImpl implements EmployeeDetailInterface {
	
	@Autowired
	EmployeeDetailDaoInterface employeeDao;
	
	@CacheEvict(allEntries = true)
    public void clearCache(){}

	@Override
	public Iterable<EmployeeDetail> getAllEmployeeDetail() throws DbException,ServiceException {
		 Iterable<EmployeeDetail> empDetailIter= employeeDao.getAllEmployees();
		 
		return empDetailIter;
	}

	@Override
	public EmployeeDetail getEmployeeDetailById(long id) throws DbException,ServiceException {
		if(id!=0 && id>0) {
			EmployeeDetail empDetailObj=employeeDao.getEmployeeDetailById(id);
			
			return empDetailObj;
		}
		else {
			throw new ServiceException("id Not found or id inaddmisible");
		}
		
		
	}

	@Override
	@Transactional
	public boolean saveEmployeeDetail(@Validated EmployeeDetail empDetail,
			BindingResult result) throws DbException,ServiceException {
		clearCache();
		ReponseDetail responseObj =null;
		if(result.hasErrors()) {
			return false;
		}
		
		else {
			EmployeeDetail empObj=employeeDao.getByUserName(empDetail.getUserName());
			if(empObj!=null && !empObj.getUserName().equals(empDetail.getUserName())) {
				employeeDao.insertEmployeeDetail(empDetail);
			return true;
			}
			else if(empObj==null) {
				employeeDao.insertEmployeeDetail(empDetail);
				return true;
			}
			else {
				
				return false;
			}
			
		}
		
		
		
	}

	@Override
	public ReponseDetail deleteEmployeeDetailbyId(long id) throws DbException,ServiceException {
		if(id!=0 && id>0) {
			employeeDao.deleteEmployeeDetail(id);
			ReponseDetail respObj= new ReponseDetail(HttpStatus.ACCEPTED.toString(),"success",
					"deleted Successfully",null);
			return respObj;
		}
		else {
			throw new ServiceException("id not found or inadmissible");
		}
		
	}

	@Override
	public ReponseDetail checkLogIn(String userName, String password) throws DbException,ServiceException {
		
	      ReponseDetail responseObj= null;
		 if(userName!=null && !userName.isEmpty() 
				 && password!=null && !password.isEmpty()) {
		EmployeeDetail empOb=employeeDao.getByUserName(userName);
		if(userName.equals(empOb.getUserName()) && password.equals(empOb.getPassword())) {
			  responseObj= new ReponseDetail(HttpStatus.OK.toString(),"success", "LoggedIn successfully", null);
		}
		else {
			throw new LogInException("Invalied Username or Password");
		}
		}
		  else {
			 throw new ServiceException("either username or password is invalid");
		 }
		return responseObj;
		
	} 

}

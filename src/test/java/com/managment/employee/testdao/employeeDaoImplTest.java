package com.managment.employee.testdao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.internal.Iterables;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.managment.employee.EmployeemanagementApplicationTests;
import com.managment.employee.dao.EmployeeDetailDaoInterface;
import com.managment.employee.model.EmployeeDetail;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Ignore
public class employeeDaoImplTest{
	
	@Autowired
    EmployeeDetailDaoInterface empDaoInterface;
	
	
	
	@Test
	
	public void findAllTest() {
		List<EmployeeDetail> employeeIter=(List<EmployeeDetail>) empDaoInterface.getAllEmployees();
		
		assertEquals(employeeIter.size(),1);
	}
	
	@Test
	
	public void findById() {
		EmployeeDetail employeeObj= empDaoInterface.getEmployeeDetailById(2);
		assertEquals(employeeObj.getEmpId(),2);
	}
	
	
	@Test
	
	public void findByUserName() {
		EmployeeDetail employeeObj= empDaoInterface.getByUserName("us");
		assertEquals(employeeObj.getUserName(),"us");
	}
	
	@Test
	@Rollback
	
	public void saveEmployeeDetail() {
		EmployeeDetail empObj= new EmployeeDetail();
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih@fdf.com");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		 String result=empDaoInterface.insertEmployeeDetail(empObj);
		 assertNull(result);
	}
	
	@Test
	@Rollback
	
	public void deleteEmployeeDetail() {
		
		 String result=empDaoInterface.deleteEmployeeDetail(2);
		 assertNull(result);
	}
}

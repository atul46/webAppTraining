package com.managment.employee.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.IterableUtil;
import com.managment.employee.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.managment.employee.dao.EmployeeDetailDaoInterface;
import com.managment.employee.exception.DbException;
import com.managment.employee.exception.LogInException;
import com.managment.employee.model.EmployeeDetail;
import com.managment.employee.model.ReponseDetail;
import com.managment.employee.service.EmployeeDetailImpl;
import com.managment.employee.service.EmployeeDetailInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value=EmployeeDetailImpl.class)
@AutoConfigureMockMvc
public class EmployeeDetailImplTest {

	@Autowired
	EmployeeDetailInterface empInterface;
	
	@MockBean
	EmployeeDetailDaoInterface empdao;
	
	
	
	EmployeeDetail empObj= new EmployeeDetail();
	
	
	@Test
	public void getAllEmpSuccessTest() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		List<EmployeeDetail> empDetailIter= new ArrayList();
		empDetailIter.add(empObj);
		Mockito.when(empdao.getAllEmployees()).thenReturn(empDetailIter);
		 Iterable<EmployeeDetail>empIter=empInterface.getAllEmployeeDetail();
		 
		int size = IterableUtil.sizeOf(empIter);
		assertThat(size).isEqualTo(1);
	}
	
	@Test
	public void getEmployeeByIdSuccess() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		List<EmployeeDetail> empDetailIter= new ArrayList();
		empDetailIter.add(empObj);
		Mockito.when(empdao.getEmployeeDetailById(Matchers.anyLong())).thenReturn(empObj);
		 EmployeeDetail empObj=empInterface.getEmployeeDetailById(4);
		 		
		assertThat(empObj.getEmpId()).isEqualTo(4);
	}
	
	@Test(expected=ServiceException.class)
	public void getEmployeeByInvalidId()  {
		
		Mockito.when(empdao.getEmployeeDetailById(Matchers.anyLong())).thenReturn(null);
		EmployeeDetail empObj=empInterface.getEmployeeDetailById(0);
		
}
	
	@Test
	public void deleteEmployeeById() {
		ReponseDetail respObj= new ReponseDetail(HttpStatus.ACCEPTED.toString(),"success",
				"deleted Successfully",null);
		Mockito.when(empdao.deleteEmployeeDetail(Matchers.anyLong())).thenReturn(null);
		ReponseDetail obj=empInterface.deleteEmployeeDetailbyId(9);
		assertEquals("deleted Successfully",obj.getStatusMessage());
	}
	
	@Test(expected=ServiceException.class)
	public void deleteEmployeeByInvalidId() {
		ReponseDetail respObj= new ReponseDetail(HttpStatus.BAD_REQUEST.toString(),"failure",
				"id not found or inadmissible",null);
		Mockito.when(empdao.deleteEmployeeDetail(Matchers.anyLong())).thenReturn(null);
		ReponseDetail responseObj=empInterface.deleteEmployeeDetailbyId(0);
		
	}
	
	@Test
	public void checkLogintest() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(empObj);
		ReponseDetail responseObj=empInterface.checkLogIn("us", "fgf");
		assertEquals("LoggedIn successfully",responseObj.getStatusMessage());
		
	}
	
	@Test(expected=LogInException.class)
	public void checkLoginFailuretest() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(empObj);
		ReponseDetail responseObj=empInterface.checkLogIn("user1", "user2");
		
		
	}
	
	@Test(expected=ServiceException.class)
	public void checkLoginInvalidtest() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(empObj);
		ReponseDetail responseObj=empInterface.checkLogIn("", "user2");
		}
	
	
	@Test
	public void insertEmployeeDetailSuccess() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		EmployeeDetail empObj2= new EmployeeDetail();
		empObj2.setEmpId(4);
		empObj2.setUserName("us1");
		empObj2.setPassword("fgf1");
		empObj2.setFullName("us");
		empObj2.setEmpMailId("muih");
		empObj2.setDob("13-07-1977");
		empObj2.setGender("Male");
		empObj2.setSecQuestion("fav car");
		empObj2.setSecAnswer("Maserati");
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(empObj);
		Mockito.when(empdao.insertEmployeeDetail(empObj2)).thenReturn(null);
		BindingResult result = mock(BindingResult.class);
		boolean isSaved=empInterface.saveEmployeeDetail(empObj2, result);
		assertTrue(isSaved);
	}
	
	@Test
	public void insertEmployeeDetailFailed() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(empObj);
		Mockito.when(empdao.insertEmployeeDetail(empObj)).thenReturn(null);
		BindingResult result = mock(BindingResult.class);
		boolean isSaved=empInterface.saveEmployeeDetail(empObj, result);
		assertFalse(isSaved);
	}
	
	@Test
	public void insertEmployeeDetailWithUserNameNotPresent() {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		Mockito.when(empdao.getByUserName(Matchers.anyString())).thenReturn(null);
		Mockito.when(empdao.insertEmployeeDetail(empObj)).thenReturn(null);
		BindingResult result = mock(BindingResult.class);
		boolean isSaved=empInterface.saveEmployeeDetail(empObj, result);
		assertTrue(isSaved);
	}
	
	
}

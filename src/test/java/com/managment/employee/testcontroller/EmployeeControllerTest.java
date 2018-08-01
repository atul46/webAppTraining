package com.managment.employee.testcontroller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.managment.employee.controller.EmployeeController;
import com.managment.employee.dao.EmployeeDetailDaoInterface;
import com.managment.employee.exception.DbException;
import com.managment.employee.model.EmployeeDetail;
import com.managment.employee.model.ReponseDetail;
import com.managment.employee.service.EmployeeDetailInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeDetailDaoInterface empDao;

	@MockBean
	EmployeeDetailInterface empInterface;

	EmployeeDetail empObj = new EmployeeDetail();

	@Test

	public void addEmployeeDetailInvalidId() throws Exception {

		BindingResult result = mock(BindingResult.class);
		Mockito.when(empDao.getByUserName(Matchers.anyString())).thenReturn(Matchers.any(EmployeeDetail.class));
		Mockito.when(empInterface.saveEmployeeDetail(Matchers.any(EmployeeDetail.class), result)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.put("/EmpMgt/addEmp").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(
						"{\"username\":\"us1\",\"password\":\"1234\",\"fullName\":\"asfs\",\"empMailId\":\"dfsdfa@dfgsdf.com\",\"dob\":\"11-01-98\",\"gender\":\"MALE\",\"secQuestion\":\"Whzxvzxc\",\"secAnswer\":\"tyrr\"}"))
				.andExpect(status().isOk()).andExpect(content().json(
						"{\"statusCode\":\"400\",\"status\":\"failure\",\"statusMessage\":\"userName already exists or invalidId\",\"empDetailIter\":null}"));
	}

	@Test
	public void getEmployeeDetailByIdTest() throws Exception {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		List<EmployeeDetail> empDetailIter = new ArrayList();
		empDetailIter.add(empObj);
		ReponseDetail respoObj = new ReponseDetail(HttpStatus.OK.toString(), "success", "Fetched Successfully",
				empDetailIter);
		Mockito.when(empInterface.getEmployeeDetailById(Matchers.anyLong())).thenReturn(empObj);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/4")).andExpect(status().isOk())
				.andExpect(content().json(
						"{\"statusCode\":\"200\",\"status\":\"success\",\"statusMessage\":\"Fetched Successfully\",\"empDetailIter\":[{\"empId\":4,\"userName\":\"us\",\"password\":\"fgf\",\"fullName\":\"us\",\"empMailId\":\"muih\",\"dob\":\"13-07-1977\",\"gender\":\"Male\",\"secQuestion\":\"fav car\",\"secAnswer\":\"Maserati\"}]}"));
	}

	@Test
	public void getEmployeeByIdFailureTest() throws Exception {
		
		Mockito.when(empInterface.getEmployeeDetailById(Matchers.anyLong()))
				.thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/4")).andExpect(status().is4xxClientError())
				.andExpect(content().json(
						"{\"statusCode\":\"400\",\"status\":\"failure\",\"statusMessage\":\"Id not found Exception\"}"));
	}

	

	@Test
	public void getAllEmpTest() throws Exception {
		empObj.setEmpId(4);
		empObj.setUserName("us");
		empObj.setPassword("fgf");
		empObj.setFullName("us");
		empObj.setEmpMailId("muih");
		empObj.setDob("13-07-1977");
		empObj.setGender("Male");
		empObj.setSecQuestion("fav car");
		empObj.setSecAnswer("Maserati");
		List<EmployeeDetail> empDetailIter = new ArrayList();
		empDetailIter.add(empObj);
		ReponseDetail respoObj = new ReponseDetail(HttpStatus.OK.toString(), "success", "Fetched Successfully",
				empDetailIter);
		Mockito.when(empInterface.getAllEmployeeDetail()).thenReturn(empDetailIter);
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
				.andExpect(content().json(
						"{\"statusCode\":\"200\",\"status\":\"success\",\"statusMessage\":\"Fetched Successfully\",\"empDetailIter\":[{\"empId\":4,\"userName\":\"us\",\"password\":\"fgf\",\"fullName\":\"us\",\"empMailId\":\"muih\",\"dob\":\"13-07-1977\",\"gender\":\"Male\",\"secQuestion\":\"fav car\",\"secAnswer\":\"Maserati\"}]}"));

	}

	@Test
	public void deleteEmpbyId() throws Exception {
		ReponseDetail respoObj = new ReponseDetail(HttpStatus.ACCEPTED.toString(), "success", "deleted Successfully",
				null);
		Mockito.when(empInterface.deleteEmployeeDetailbyId(Matchers.anyLong())).thenReturn(respoObj);
		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/5")).andExpect(status().isOk())
				.andExpect(content().json(
						"{\"statusCode\": \"202\",\"status\": \"success\", \"statusMessage\": \"deleted Successfully\", \"empDetailIter\": null}"));
	}
	
	@Test
	public void checkLoginTest() throws Exception {
		 ReponseDetail responseObj= new ReponseDetail(HttpStatus.OK.toString(),"success","LoggedIn successfully", null);
		Mockito.when(empInterface.checkLogIn(Matchers.anyString(),Matchers.anyString())).
		thenReturn(responseObj);
		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin").content("{\"userName\":\"user1\",\"password\":\"user2\"}").contentType("application/json")).andExpect(status().isOk())
		.andExpect(content().json("{\"statusCode\": \"200\",\"status\": \"success\",\"statusMessage\": \"LoggedIn successfully\",\"empDetailIter\": null}"));
	}

}

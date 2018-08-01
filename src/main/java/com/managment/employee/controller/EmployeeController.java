package com.managment.employee.controller;

import java.util.ArrayList;
import java.util.List;

import com.managment.employee.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.managment.employee.model.EmployeeDetail;
import com.managment.employee.model.ReponseDetail;
import com.managment.employee.service.EmployeeDetailInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/EmpMgt")
@Api(value="Employee Detail API", description="Operations to fectch employee details efficiently") 

public class EmployeeController {
	
	EmployeeDetailInterface employeeDetailInterface;
	@Autowired
    public EmployeeController(EmployeeDetailInterface EmployeeDetailInterface) {
        this.employeeDetailInterface = EmployeeDetailInterface;
    }
	
	@ApiOperation(value = "Add employee detail into database", response = Iterable.class)

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully Inserted"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"), 
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), 
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@RequestMapping(value="/addEmp",method=RequestMethod.PUT,consumes= {"application/json"},produces= {"application/json"})
	 public ReponseDetail saveEmpDetail(@Validated @RequestBody EmployeeDetail empObj,BindingResult result){
		boolean isSaved=employeeDetailInterface.saveEmployeeDetail(empObj,result);
		ReponseDetail responseDetail=null; 
		if(isSaved) {
			responseDetail = new ReponseDetail(HttpStatus.OK.toString(),"success",
					"Successfully Inserted",null);
		}
		else {
			responseDetail = new ReponseDetail(HttpStatus.BAD_REQUEST.toString(),"failure",
			"userName already exists or invalidId",null);
		}
		return responseDetail;
	 }
	
	
	@ApiOperation(value = "retrieve every employeeDetail ", response = Iterable.class)

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"), 
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), 
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	 @GetMapping("/getAllEmpDetails")
	@Cacheable("alluserscache")
	 public ReponseDetail getAllEmps(){
		 Iterable<EmployeeDetail> empIter=employeeDetailInterface.getAllEmployeeDetail();
		 ReponseDetail respDetail= new ReponseDetail(HttpStatus.OK.toString(),
				  "success","Fetched Successfully",empIter);
		return respDetail;
		 
	 }
	 
	@ApiOperation(value = "retrieve employee by Identifier ", response = Iterable.class)

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"), 
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), 
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	 @GetMapping("/getByEmpId/{empId}")
	 public ReponseDetail getPlayerById(@PathVariable("empId") Long empId ) {
		 ReponseDetail respDetail=null;
		 EmployeeDetail empDetail=employeeDetailInterface.getEmployeeDetailById(empId);
		 if(empDetail==null) {
			 throw new ServiceException("Id not found Exception");
		  }
		 List<EmployeeDetail> empObject= new ArrayList<>();
		 empObject.add(empDetail);
		 respDetail= new ReponseDetail(HttpStatus.OK.toString(),
				 "success","Fetched Successfully",empObject);
		 return respDetail ;		 
	 }
	 
	@ApiOperation(value = "purge employeedetail by identifier ", response = Iterable.class)

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully purged"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"), 
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), 
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	 @RequestMapping(value="/deleteEmp/{empId}",method=RequestMethod.DELETE)
	 public ReponseDetail deletePlayerById(@PathVariable(value="empId") Long empId) {
		
			 return employeeDetailInterface.deleteEmployeeDetailbyId(empId);
	 }
	
	@ApiOperation(value = "validate login ", response = Iterable.class)

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully loggedIn"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"), 
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), 
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})	
	 @RequestMapping(value="/checkLogin",method=RequestMethod.POST,consumes= {"application/json"},produces= {"application/json"})
	 public ReponseDetail validateLogIn(@RequestBody EmployeeDetail empObj) {
		
			 return employeeDetailInterface.checkLogIn(empObj.getUserName(), empObj.getPassword());
	 }
		 }



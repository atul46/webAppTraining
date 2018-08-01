package com.managment.employee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

import io.swagger.annotations.ApiModelProperty;



@Entity
@Table(name="emp_details")
public class EmployeeDetail implements Serializable{
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@ApiModelProperty(notes="The database generated product ID")
@Column(name="emp_id")
private long empId;

@NotNull(message="userName cannot be null")
@NotEmpty(message="fullname cannot be empty")
@ApiModelProperty(notes="employee username")
@NotBlank
@Column(name="emp_username")
private String userName;


@NotEmpty(message="password cannot be empty")
@ApiModelProperty(notes="employee password")
@NotBlank
@Column(name="emp_password")
private String password;

@NotNull(message="fullname cannot be null")
@NotEmpty(message="fullname cannot be empty")
@ApiModelProperty(notes="employee fullname")
@NotBlank
@Column(name="emp_fullname")
private String fullName;

@NotNull(message="mailId cannot be null")
@NotEmpty(message="mailId cannot be null")
@Email(message="mailId invalid")
@ApiModelProperty(notes="employee mailId")
@NotBlank
@Column(name="emp_mailid")
private String empMailId;

@NotNull(message="dob cannot be null")
@NotEmpty(message="dob cannot be empty")
@ApiModelProperty(notes="employee date of birth")
@Column(name="emp_dob")

private String dob;

@NotNull(message="emp_gender cannot be null")
@NotEmpty(message="emp_gender cannot be empty")
@ApiModelProperty(notes="employee gender")
@Column(name="emp_gender")
private String gender;

@NotNull(message="emp_secque cannot be null")
@NotEmpty(message="emp_secque cannot be empty")
@ApiModelProperty(notes="security question keyed in by the user")
@Column(name="emp_secque")
private String secQuestion;

@NotNull(message="emp_secans cannot be null")
@NotEmpty(message="emp_secans cannot be empty")
@ApiModelProperty(notes="security answer keyed in by the user")
@Column(name="emp_secans")
private String secAnswer;



public long getEmpId() {
	return empId;
}
public void setEmpId(long empId) {
	this.empId = empId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getEmpMailId() {
	return empMailId;
}
public void setEmpMailId(String empMailId) {
	this.empMailId = empMailId;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getSecQuestion() {
	return secQuestion;
}
public void setSecQuestion(String secQuestion) {
	this.secQuestion = secQuestion;
}
public String getSecAnswer() {
	return secAnswer;
}
public void setSecAnswer(String secAnswer) {
	this.secAnswer = secAnswer;
}
	

}

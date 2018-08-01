DROP table emp_details;
CREATE TABLE emp_details (emp_id INT NOT NULL AUTO_INCREMENT,
emp_username VARCHAR(45) NOT NULL,
emp_password VARCHAR(45) NOT NULL,
emp_fullname VARCHAR(45) NOT NULL, 
emp_mailid VARCHAR(100) NOT NULL, 
emp_dob VARCHAR(45)  NOT NULL, 
emp_gender VARCHAR(45) NOT NULL,
emp_secque VARCHAR(45) NOT NULL,
emp_secans VARCHAR(45)  NOT NULL,
PRIMARY KEY (emp_id)); 
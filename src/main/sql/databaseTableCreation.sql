CREATE DATABASE IF NOT EXISTS employeesdb;
USE employeesdb;

DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
	EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    NamePrefix VARCHAR(5) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    InitialMiddleName VARCHAR(1) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Gender CHAR(1) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    DateOfJoining DATE NOT NULL,
    Salary DECIMAL(15,2) NOT NULL
);
package com.spartaglobal.database;

import com.spartaglobal.migrationproject.Employee;

import java.util.ArrayList;

public interface EmployeeDAO {
    void createEmployeesTable();
    void insertEmployee(ArrayList<Employee> data);
    ArrayList<Employee>selectAllEmployees();
    ArrayList<Employee> selectOneEmployee(String employeeID);
}

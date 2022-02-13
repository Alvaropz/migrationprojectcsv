package com.spartaglobal.database;

import com.spartaglobal.migrationproject.Employee;

import java.util.ArrayList;

public interface EmployeeDAO {
    void createEmployeesTable();
    void insertEmployee(ArrayList<Employee> data);
    ArrayList<String[]> selectAllEmployees();
    ArrayList<String[]> selectOneEmployee(String employeeID);
}

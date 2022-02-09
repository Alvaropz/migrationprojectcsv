package com.spartaglobal.database;

import java.util.ArrayList;

public interface EmployeeDAO {
    void createEmployeesTable();
    void insertEmployee(ArrayList<String[]> data);
    ArrayList<String[]> selectAllEmployees();
}

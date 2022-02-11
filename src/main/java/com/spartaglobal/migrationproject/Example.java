package com.spartaglobal.migrationproject;

import com.spartaglobal.database.CSVDAOFactory;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Example {
    public static void main(String[] args) {
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecords.csv");
        List<String> duplicates = DuplicatesHandler.arrayDuplicates(data);
        data = DuplicatesHandler.filterDuplicates(data, duplicates);

        long startTime = System.nanoTime();
        employeedao.insertEmployee(data);
        long endTime = System.nanoTime() - startTime;

        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
        StringBuffer dataEmployee = new StringBuffer();
        for (String[] employeeRow : retrievedData) {
            for (String employeeData : employeeRow) {
                dataEmployee.append(employeeData + " ");
            }
            dataEmployee.append("\n");
        }
        try {
            CSVDAOFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(dataEmployee);
        System.out.println(endTime);
    }
}
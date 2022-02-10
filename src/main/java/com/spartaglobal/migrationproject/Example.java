package com.spartaglobal.migrationproject;

import com.spartaglobal.DisplayManager;
import com.spartaglobal.database.CSVDAOFactory;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Example {
    public static void main(String[] args) throws ParseException {
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecords.csv");
        List<String> duplicates = DuplicatesHandler.arrayDuplicates(data);
        data = DuplicatesHandler.filterDuplicates(data, duplicates);
        employeedao.insertEmployee(data);
        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
        DisplayManager view = new DisplayManager();
//        view.dataOneEmployee();
        view.displayResults(retrievedData);
        try {
            CSVDAOFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
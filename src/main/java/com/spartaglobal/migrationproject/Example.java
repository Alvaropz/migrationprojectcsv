package com.spartaglobal.migrationproject;

import com.spartaglobal.view.DisplayManager;
import com.spartaglobal.database.CSVDAOFactory;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Example {
    public static void main(String[] args) throws ParseException {
        DisplayManager view = new DisplayManager();
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecords.csv");
        List<String> duplicates = DuplicatesHandler.arrayDuplicates(data);
//        view.displayDuplicates(duplicates);
//        data = DuplicatesHandler.filterDuplicates(data, duplicates);
//        employeedao.insertEmployee(data);
//        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
//        view.dataOneEmployee();
//        view.displayResultsChoice(retrievedData);
        try {
            CSVDAOFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
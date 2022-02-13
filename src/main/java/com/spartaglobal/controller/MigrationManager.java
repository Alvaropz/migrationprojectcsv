package com.spartaglobal.controller;

import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;
import com.spartaglobal.migrationproject.*;
import com.spartaglobal.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MigrationManager {
    public static Logger logger = LogManager.getLogger("Controller - Migration Manager");

    public void run() {
        logger.info("Program starts");
        DisplayManager view = new DisplayManager();
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        employeedao.createEmployeesTable();

        StreamsClass lambaRead = new StreamsClass();
//        view.displayDuplicates(duplicates, 57);
//        data = DuplicatesHandler.filterDuplicates(data, duplicates);

        Long StartTime = System.nanoTime();
//        ArrayList<MyThread> threads = MultiThreadingManager.loadThreads(numberOfThreads, data);
//        MultiThreadingManager.runThreads(threads);
        Long endTime = System.nanoTime() - StartTime;
        System.out.println("It took " + TimeUnit.NANOSECONDS.toSeconds(endTime) + " seconds to create, cast, insert and retrieve the data from the database.");
//
//        view.dataOneEmployee();
//        view.displayResultsChoice(retrievedData);
        logger.info("Program starts");
    }
}

//    DisplayManager view = new DisplayManager();
//    DAOFactory factoryType = DAOFactory.getDAOFactory();
//    EmployeeDAO employeedao = factoryType.getEmployeeDAO();
//        employeedao.createEmployeesTable();
//                ArrayList<String[]> dataStringArray = ReadFromCSV.read("EmployeeRecords.csv");
//        ArrayList<String> duplicatesStringArray = (ArrayList<String>) DuplicatesHandler.arrayDuplicates(dataStringArray);
//        ArrayList<String[]> filteredDataStringArray = DuplicatesHandler.filterDuplicates(dataStringArray, duplicatesStringArray);
//        ArrayList<Employee> employeeList = new ArrayList<>();
//        for (String[] row : filteredDataStringArray) {
//        employeeList.add(new Employee(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9]));
//        }
//        employeedao.insertEmployee(employeeList);
//        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
//
//        long sequentialEndTime = System.nanoTime();
//
//
//        view.dataOneEmployee();
//        view.displayResultsChoice(retrievedData);
//        try {
//        CSVDAOFactory.closeConnection();
//        } catch (SQLException e) {
//        e.printStackTrace();
//        }
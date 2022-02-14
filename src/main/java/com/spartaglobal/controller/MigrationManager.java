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

        StreamsClass lambaRead = new StreamsClass();
        view.displayDuplicates(lambaRead.getDuplicates());
        int numberOfThreads = view.numberOfThreads();

        System.out.println("Working on the database, please wait...");
        Long StartTime = System.nanoTime();
        ArrayList<MyThread> threads = MultiThreadingManager.loadThreads(numberOfThreads, lambaRead.dataGet());
        MultiThreadingManager.runThreads(threads);
        ArrayList<Employee> dataRetrieved = employeedao.selectAllEmployees();
        Long endTime = System.nanoTime() - StartTime;
        System.out.println("It took " + TimeUnit.NANOSECONDS.toSeconds(endTime) + " seconds to create, cast, insert and retrieve the data from the database.");

        view.dataOneEmployee();
        view.displayResultsChoice(dataRetrieved);
        logger.info("Program ends");
    }
}
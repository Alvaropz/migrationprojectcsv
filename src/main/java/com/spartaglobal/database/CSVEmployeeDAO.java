package com.spartaglobal.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSVEmployeeDAO implements EmployeeDAO{
    private static Logger logger = LogManager.getLogger("CSVEmployeeDAO Logger");

    @Override
    public void createEmployeesTable() {
        logger.info("Employees table created in the database");
        PreparedStatement preparedStatement = null;

        try {
            Connection connection = CSVDAOFactory.getConnectionDAO();
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS employees");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(
                    "CREATE TABLE employees (\n" +
                            "\tEmployeeID INT AUTO_INCREMENT PRIMARY KEY UNIQUE,\n" +
                            "    NamePrefix VARCHAR(5) NOT NULL,\n" +
                            "    FirstName VARCHAR(255) NOT NULL,\n" +
                            "    InitialMiddleName VARCHAR(1) NOT NULL,\n" +
                            "    LastName VARCHAR(255) NOT NULL,\n" +
                            "    Gender CHAR(1) NOT NULL,\n" +
                            "    Email VARCHAR(255) NOT NULL,\n" +
                            "    DateOfBirth DATE NOT NULL,\n" +
                            "    DateOfJoining DATE NOT NULL,\n" +
                            "    Salary DECIMAL(15,2) NOT NULL\n" +
                            ");");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void insertEmployee(ArrayList<String[]> data) {
        logger.info("Insert an employee data in the Employees table");
        PreparedStatement preparedStatement = null;
        DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String convertedDate;
        try {
            Connection connection = CSVDAOFactory.getConnectionDAO();
            for (String[] employeeArray : data) {
                preparedStatement = connection.prepareStatement("INSERT INTO employees (EmployeeID, NamePrefix, FirstName, InitialMiddleName, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, Integer.parseInt(employeeArray[0]));
                preparedStatement.setString(2, employeeArray[1]);
                preparedStatement.setString(3, employeeArray[2]);
                preparedStatement.setString(4, employeeArray[3]);
                preparedStatement.setString(5, employeeArray[4]);
                preparedStatement.setString(6, employeeArray[5]);
                preparedStatement.setString(7, employeeArray[6]);
                date = userDateFormat.parse(employeeArray[7]);
                convertedDate = dateFormatNeeded.format(date);
                preparedStatement.setString(8, convertedDate);
                date = userDateFormat.parse(employeeArray[8]);
                convertedDate = dateFormatNeeded.format(date);
                preparedStatement.setString(9, convertedDate);
                preparedStatement.setInt(10, Integer.parseInt(employeeArray[9]));
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException|IOException|ParseException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String[]> selectAllEmployees() {
        logger.info("Retrieved all records from the Employees Table");
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<String[]> retrievedData = new ArrayList<>();
        try {
            Connection connection = CSVDAOFactory.getConnectionDAO();

            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                retrievedData.add(new String[]{rs.getString("EmployeeID"), rs.getString("NamePrefix"), rs.getString("FirstName"), rs.getString("InitialMiddleName"), rs.getString("LastName"), rs.getString("Gender"), rs.getString("Email"), rs.getString("DateOfBirth"), rs.getString("DateOfJoining"), rs.getString("Salary")});
            }
            rs.close();
            statement.close();
        } catch (SQLException|IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return retrievedData;
    }

    @Override
    public ArrayList<String[]> selectOneEmployee(String EmployeeID) {
        logger.info("Retrieved the information related to one employee from the Employees Table");
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<String[]> retrievedData = new ArrayList<>();

        try {
            Connection connection = CSVDAOFactory.getConnectionDAO();
            preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE EmployeeID=?");
            preparedStatement.setString(1, EmployeeID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                retrievedData.add(new String[]{rs.getString("EmployeeID"), rs.getString("NamePrefix"), rs.getString("FirstName"), rs.getString("InitialMiddleName"), rs.getString("LastName"), rs.getString("Gender"), rs.getString("Email"), rs.getString("DateOfBirth"), rs.getString("DateOfJoining"), rs.getString("Salary")});
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException|IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return retrievedData;
    }
}

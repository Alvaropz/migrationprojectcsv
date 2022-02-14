package com.spartaglobal.database;

import com.spartaglobal.migrationproject.Employee;
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
    public Connection connection;
    public PreparedStatement preparedStatement;
    {
        try {
            connection = CSVDAOFactory.getConnectionDAO();
        } catch (IOException|SQLException e) {
            logger.error(e);
            System.out.println();
        }
    }

    public void initialise() {
        try {
            connection = CSVDAOFactory.getConnectionDAO();
            preparedStatement = connection.prepareStatement("INSERT INTO employees (EmployeeID, NamePrefix, FirstName, InitialMiddleName, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        } catch (IOException|SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployeesTable() {
        logger.info("Employees table created in the database");
        PreparedStatement preparedStatement = null;

        try {
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
            logger.info("Connection between the program and MySQL closed");
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }


    @Override
    public void insertEmployee(ArrayList<Employee> data) {
        logger.info("Insert an employee data in the Employees table");
        DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String convertedDate;
        try {
            for (Employee employee : data) {
                preparedStatement = connection.prepareStatement("INSERT INTO employees (EmployeeID, NamePrefix, FirstName, InitialMiddleName, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, Integer.parseInt(employee.getEmployeeID()));
                preparedStatement.setString(2, employee.getNamePrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, employee.getMiddleInitial());
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, employee.getGender());
                preparedStatement.setString(7, employee.getEmail());
                date = userDateFormat.parse(employee.getDateOfBirth());
                convertedDate = dateFormatNeeded.format(date);
                preparedStatement.setString(8, convertedDate);
                date = userDateFormat.parse(employee.getDateOfJoin());
                convertedDate = dateFormatNeeded.format(date);
                preparedStatement.setString(9, convertedDate);
                preparedStatement.setInt(10, Integer.parseInt(employee.getSalary()));
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            logger.info("Connection between the program and MySQL closed");
        } catch (SQLException| ParseException e) {
            e.printStackTrace();
        }
    }

    public void insertIndivual(Employee employee)
    {
        DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        String convertedDate;
        try {
            preparedStatement.setInt(1, Integer.parseInt(employee.getEmployeeID()));
            preparedStatement.setString(2, employee.getNamePrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleInitial());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getEmail());
            date = userDateFormat.parse(employee.getDateOfBirth());
            convertedDate = dateFormatNeeded.format(date);
            preparedStatement.setString(8, convertedDate);
            date = userDateFormat.parse(employee.getDateOfJoin());
            convertedDate = dateFormatNeeded.format(date);
            preparedStatement.setString(9, convertedDate);
            preparedStatement.setInt(10, Integer.parseInt(employee.getSalary()));
            preparedStatement.addBatch();
        } catch (SQLException|ParseException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Employee> selectAllEmployees() {
        logger.info("Retrieved all records from the Employees Table");
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Employee> retrievedData = new ArrayList<>();
        try {

            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                retrievedData.add(new Employee(rs.getString("EmployeeID"), rs.getString("NamePrefix"), rs.getString("FirstName"),
                        rs.getString("InitialMiddleName"), rs.getString("LastName"), rs.getString("Gender"), rs.getString("Email"),
                        rs.getString("DateOfBirth"), rs.getString("DateOfJoining"), rs.getString("Salary")));
            }
            rs.close();
            statement.close();
            logger.info("Connection between the program and MySQL closed");
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return retrievedData;
    }

    @Override
    public ArrayList<Employee> selectOneEmployee(String EmployeeID) {
        logger.info("Retrieved the information related to one employee from the Employees Table");
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<Employee> retrievedData = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE EmployeeID=?");
            preparedStatement.setString(1, EmployeeID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                retrievedData.add(new Employee(rs.getString("EmployeeID"), rs.getString("NamePrefix"), rs.getString("FirstName"),
                        rs.getString("InitialMiddleName"), rs.getString("LastName"), rs.getString("Gender"), rs.getString("Email"),
                        rs.getString("DateOfBirth"), rs.getString("DateOfJoining"), rs.getString("Salary")));
            }

            rs.close();
            preparedStatement.close();
            logger.info("Connection between the program and MySQL closed");
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return retrievedData;
    }
}

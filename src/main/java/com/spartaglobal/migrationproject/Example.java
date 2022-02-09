package com.spartaglobal.migrationproject;

import com.spartaglobal.database.BasicDAOFactory;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Example {
    public static void main(String[] args) {
        DAOFactory factoryType = DAOFactory.getDAOFactory(1);
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecordsLarge.csv");
        data.remove(0); // This line removes the first array as it's the column names of the csv file
        List<String> duplicates = ReadFromCSV.duplicateCheck(data);
        int i = 0;
        for (Iterator<String[]> iterator = data.iterator(); iterator.hasNext();) {
            String[] row = iterator.next();
            if(duplicates.contains(row[0])) {
                iterator.remove();
            }
        }

        employeedao.insertEmployee(data);
        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
        StringBuffer dataEmployee = new StringBuffer();
        for (String[] employeeRow : retrievedData) {
            for (String employeeData : employeeRow) {
                dataEmployee.append(employeeData + " ");
            }
            dataEmployee.append("\n");
        }
        try {
            BasicDAOFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//        data.add(new String[]{"198429", "Mrs.", "Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008","69294"});
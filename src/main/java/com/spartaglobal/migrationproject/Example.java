package com.spartaglobal.migrationproject;

import com.spartaglobal.database.BasicDAOFactory;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;


public class Example {
    public static void main(String[] args) {
        DAOFactory factoryType = DAOFactory.getDAOFactory(1);
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"198429", "Mrs.", "Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008","69294"});
        employeedao.insertEmployee(data);
        ArrayList<String[]> retrievedData = employeedao.selectAllEmployees();
        StringBuffer dataEmployee = new StringBuffer();
        for (String[] employeeRow : retrievedData) {
            for (String employeeData : employeeRow) {
                dataEmployee.append(employeeData + " ");
            }
            dataEmployee.append("\n");
            System.out.println(dataEmployee);
        }
        try {
            BasicDAOFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

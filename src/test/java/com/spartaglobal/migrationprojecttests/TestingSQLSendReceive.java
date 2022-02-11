package com.spartaglobal.migrationprojecttests;

import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestingSQLSendReceive {

    @Test
    @DisplayName("Given an arrayList of positive integers, correctly send and receive the data from SQL")
    public void givenAnArrayListOfPositiveIntegers_CorrectlySendAndReceiveBackToJava(){
        ArrayList<String[]> testArray = new ArrayList<>();
        String[]  expected = {"647173","Mr.","Milan","F","Krawczyk","M","milan.krawczyk@hotmail.com","4/4/1980","1/19/2012","123681"};
        testArray.add(expected);


        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        employeedao.insertEmployee(testArray);


        ArrayList<String[]> actual = employeedao.selectAllEmployees();
//        StringBuffer actual = new StringBuffer();
//        for (String[] employeeRow : retrievedData) {
//            for (String employeeData : employeeRow) {
//                actual.append(employeeData + " ");
//            }
//            actual.append("\n");
//        }
//
//        StringBuffer expected = new StringBuffer();
//        for (String[] employeeRow : retrievedData) {
//            for (String employeeData : employeeRow) {
//                actual.append(employeeData + " ");
//            }
//            actual.append("\n");
//        }


        System.out.println(testArray);
        System.out.println(actual);
        Assertions.assertArrayEquals(testArray.toArray(), actual.toArray());

        // Pass into Alvaros method
        // Class alvaro = new Class
        // Something results = alvaro.method(testArray)
    }

    @Test
    @DisplayName("Given an arrayList of zeros, correctly send and receive the data from SQL")
    public void givenAnArrayListOfZeros_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(0);
        testArray.add(0);
        testArray.add(0);

    }

    @Test
    @DisplayName("Given an arrayList of negative values, correctly send and receive the data from SQL")
    public void givenAnArrayListOfNegativeValues_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(-1);
        testArray.add(-2);
        testArray.add(-3);

    }

    @Test
    @DisplayName("Given an arrayList of strings, correctly send and receive the data from SQL")
    public void givenAnArrayListOfStrings_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add("test1");
        testArray.add("test2");
        testArray.add("test3");

    }

    @Test
    @DisplayName("Given an arrayList of strings and integers, correctly send and receive the data from SQL")
    public void givenAnArrayListOfStringsAndIntegers_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(1);
        testArray.add("test2");
        testArray.add(3);

    }

    @Test
    @DisplayName("Given an arrayList of null, correctly send and receive the data from SQL")
    public void givenAnArrayListOfNull_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(null);
        testArray.add(null);
        testArray.add(null);

    }
}

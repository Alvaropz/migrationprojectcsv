//package com.spartaglobal.migrationprojecttests;
//import com.spartaglobal.database.DAOFactory;
//import com.spartaglobal.database.EmployeeDAO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//
//public class TestingSQLSendReceive {
//
//    @Test
//    @DisplayName("Given an arrayList of data 1 row long, correctly send and receive the data from SQL")
//    public void givenAnArrayListOfDataOneRowLong_CorrectlySendAndReceiveBackToJava(){
//        // Creating array to be sent and received from SQL
//        ArrayList<String[]> a = new ArrayList<>();
//        String[]  row1a = {"1111111","Mr.","Bob","F","Smith","M","bob.smith@hotmail.com","1/1/2000","1/1/2000","1111111"};
//        a.add(row1a);
//
//        // Sending and receiving
//        DAOFactory factoryType = DAOFactory.getDAOFactory();
//        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
//        employeedao.createEmployeesTable();
//        employeedao.insertEmployee(a);
//        ArrayList<String[]> actualArrList = employeedao.selectAllEmployees();
//
//        // Putting expected data into SQL format
//        ArrayList<String[]> expectedArrList = new ArrayList<>();
//        String[]  row1e = {"1111111","Mr.","Bob","F","Smith","M","bob.smith@hotmail.com","2000-01-01","2000-01-01","1111111.00"};
//        expectedArrList.add(row1e);
//
//        // Comparing arrays
//        var actual = actualArrList.toArray();
//        var expected = expectedArrList.toArray();
//        Assertions.assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("Given an arrayList of data multiple rows long, correctly send and receive the data from SQL")
//    public void givenAnArrayListOfDataMultipleRowsLong_CorrectlySendAndReceiveBackToJava() {
//        // Creating array to be sent and received from SQL
//        ArrayList<String[]> a = new ArrayList<>();
//        String[] row1a = {"111111", "Mr.", "Bob", "F", "Smith", "M", "bob.smith@hotmail.com", "1/1/2000", "1/1/2000", "1111111"};
//        String[] row2a = {"222222", "Mr.", "Jim", "A", "Jones", "M", "jim.jones@hotmail.com", "2/2/2000", "2/2/2000", "2222222"};
//        a.add(row1a);
//        a.add(row2a);
//
//        // Sending and receiving
//        DAOFactory factoryType = DAOFactory.getDAOFactory();
//        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
//        employeedao.createEmployeesTable();
//        employeedao.insertEmployee(a);
//        ArrayList<String[]> actualArrList = employeedao.selectAllEmployees();
//
//        // Putting expected data into SQL format
//        ArrayList<String[]> expectedArrList = new ArrayList<>();
//        String[] row1e = {"111111", "Mr.", "Bob", "F", "Smith", "M", "bob.smith@hotmail.com", "2000-01-01", "2000-01-01", "1111111.00"};
//        String[] row2e = {"222222", "Mr.", "Jim", "A", "Jones", "M", "jim.jones@hotmail.com", "2000-02-02", "2000-02-02", "2222222.00"};
//        expectedArrList.add(row1e);
//        expectedArrList.add(row2e);
//
//        // Comparing arrays
//        var actual = actualArrList.toArray();
//        var expected = expectedArrList.toArray();
//        Assertions.assertArrayEquals(expected, actual);
//    }
//}

package com.spartaglobal.migrationprojecttests;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;
import com.spartaglobal.migrationproject.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestingSQLSendReceive {

    @Test
    @DisplayName("Given an arrayList of data 1 row long, correctly send and receive the data from SQL")
    public void givenAnArrayListOfDataOneRowLong_CorrectlySendAndReceiveBackToJava(){
        // Creating array to be sent and received from SQL
        ArrayList<Employee> emArr = new ArrayList<>();
        Employee e = new Employee("1111111","Mr.","Bob","F","Smith",
                "M","bob.smith@hotmail.com","1/1/2000","1/1/2000","1111111");
        emArr.add(e);

        // Sending and receiving
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        employeedao.insertEmployee(emArr);
        ArrayList<Employee> actualArrList = employeedao.selectAllEmployees();

        // Putting expected data into SQL format
        ArrayList<Employee> expectedArrList = new ArrayList<>();
        expectedArrList.add(new Employee("1111111","Mr.","Bob","F","Smith","M","bob.smith@hotmail.com","2000-01-01","2000-01-01","1111111.00"));

        // Comparing arrays
        var actual = actualArrList.toArray();
        var expected = expectedArrList.toArray();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Given an arrayList of data multiple rows long, correctly send and receive the data from SQL")
    public void givenAnArrayListOfDataMultipleRowsLong_CorrectlySendAndReceiveBackToJava() {
        // Creating array to be sent and received from SQL
        ArrayList<Employee> emArr = new ArrayList<>();
        Employee e1 = new Employee("1111111","Mr.","Bob","F","Smith",
                "M","bob.smith@hotmail.com","1/1/2000","1/1/2000","1111111");
        Employee e2 = new Employee("222222", "Mr.", "Jim", "A", "Jones",
                "M", "jim.jones@hotmail.com", "02/02/2000", "02/02/2000", "2222222");

        emArr.add(e1);
        emArr.add(e2);

        // Sending and receiving
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        employeedao.insertEmployee(emArr);
        ArrayList<Employee> actualArrList = employeedao.selectAllEmployees();

        // Putting expected data into SQL format
        ArrayList<Employee> expectedArrList = new ArrayList<>();


        expectedArrList.add(new Employee("222222", "Mr.", "Jim", "A", "Jones", "M", "jim.jones@hotmail.com", "2000-02-02", "2000-02-02", "2222222.00"));
        expectedArrList.add(new Employee("1111111","Mr.","Bob","F","Smith","M","bob.smith@hotmail.com","2000-01-01","2000-01-01","1111111.00"));

        // Comparing arrays
        var actual = Arrays.asList(actualArrList);
        var expected = Arrays.asList(expectedArrList);
        Assertions.assertEquals(expected, actual);
    }
}

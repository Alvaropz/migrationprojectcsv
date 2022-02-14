package com.spartaglobal.view;

import com.spartaglobal.database.CSVEmployeeDAO;
import com.spartaglobal.migrationproject.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DisplayManager {
    private static Logger logger = LogManager.getLogger("Viewer Logger");

    public int numberOfThreads() {
        logger.info("numberOfThreads - User input");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int number = 0;
        while (input instanceof String) {
            System.out.print("Please insert a number between 1 and 100 to choose the number of threads: ");
            input = scanner.next();
            try {
                number = Integer.parseInt(input);
                if (number > 0 && number < 101) {
                    break;
                }
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
                System.out.println("Invalid data");
            }
        }
        return number;
    }

    public void displayDuplicates(List<Employee> duplicates) {
        logger.info("displayDuplicates - Gives the option of printing an Array of Duplicates");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("y") && !input.equals("n")) {
            System.out.print("Do you want to print the duplicates? Please type Y for \"yes\", N for \"no\": ");
            input = scanner.next().toLowerCase();
        }
        if (input.equals("y")){
            if(duplicates.isEmpty()){
                System.out.println("There are no duplicates to show");
            } else{
                for (Employee employee : duplicates) {
                    System.out.println(employee);
                }
                System.out.println("There are: " + duplicates.size() + " duplicates");
            }

        }
    }

    public void displayResultsChoice(List<Employee> data) {
        logger.info("displayResultsChoice - Allows the user to choose if display the results");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("y") && !input.equals("n")) {
            System.out.print("Do you want to print the data? Please type Y for \"yes\", N for \"no\": ");
            input = scanner.next().toLowerCase();
        }
        if (input.equals("y")) {
            try {
                DisplayManager.displayResults(data);
            } catch (ParseException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void dataOneEmployee() {
        logger.info("dataOneEmployee - Data from an employee can be retrieved");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        ArrayList<Employee> retrievedData = new ArrayList<>();
        CSVEmployeeDAO connection = new CSVEmployeeDAO();

        while (retrievedData.size() == 0 && !input.equals("exit")) {
            System.out.print("Please, type an employee number ID (e.g. 111282), type \"exit\" to finish the program: ");
            input = scanner.next();
            try {
                retrievedData = connection.selectOneEmployee(input);
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
                System.out.println("Invalid data");
            }
        }
        if (retrievedData.size() > 0) {
            try {

                DisplayManager.displayResults(retrievedData);
            } catch (ParseException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void displayResults(List<Employee> data) throws ParseException {
        logger.info("Display Results");
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
        Date dobDate;
        Date dojDate;
        String dob;
        String doj;
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-10s%-40s%-20s%-20s%-20s\n", "Employee ID", "Courtesy Title", "First Name", "MN Initial", "Last Name", "Gender", "Email", "Date Of Birth", "Date of Joining", "Salary");
        for (Employee employee : data) {
            dobDate = sdfSource.parse(employee.getDateOfBirth());
            dojDate = sdfSource.parse(employee.getDateOfJoin());
            dob = sdfDestination.format(dobDate);
            doj = sdfDestination.format(dojDate);
            System.out.format("%-15s%-20s%-20s%-20s%-20s%-10s%-40s%-20s%-20s%-20s\n", employee.getEmployeeID(), employee.getNamePrefix(), employee.getFirstName(), employee.getMiddleInitial(), employee.getLastName(), employee.getGender(), employee.getEmail(), dob, doj, employee.getSalary());
        }
    }
}

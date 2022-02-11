package com.spartaglobal.view;

import com.spartaglobal.database.CSVEmployeeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DisplayManager {

    public int numberOfThreads() {
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
                System.out.println("Invalid data");
            }
        }
        return number;
    }

    public void dataOneEmployee() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int EmployeeID;

        ArrayList<String[]> retrievedData = new ArrayList<>();
        CSVEmployeeDAO connection = new CSVEmployeeDAO();

        while (retrievedData.size() == 0 && !input.equals("exit")) {
            System.out.print("Please, type an employee number ID (e.g. 111282), type \"exit\" to finish the program: ");
            input = scanner.next();
            try {
                EmployeeID = Integer.parseInt(input);
                retrievedData = connection.selectOneEmployee(input);
                try {
                    DisplayManager.displayResults(retrievedData);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid data");
            }
        }
    }

    public void displayResultsChoice(ArrayList<String[]> data) {
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
                e.printStackTrace();
            }
        }
    }


    public static void displayResults(ArrayList<String[]> data) throws ParseException {
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
        Date dobDate;
        Date dojDate;
        String dob;
        String doj;
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-10s%-40s%-20s%-20s%-20s\n", "Employee ID", "Courtesy Title", "First Name", "MN Initial", "Last Name", "Gender", "Email", "Date Of Birth", "Date of Joining", "Salary");
        for (String[] employeeRow : data) {
            dobDate = sdfSource.parse(employeeRow[7]);
            dojDate = sdfSource.parse(employeeRow[8]);
            dob = sdfDestination.format(dobDate);
            doj = sdfDestination.format(dojDate);
            System.out.format("%-15s%-20s%-20s%-20s%-20s%-10s%-40s%-20s%-20s%-20s\n", employeeRow[0], employeeRow[1], employeeRow[2], employeeRow[3], employeeRow[4], employeeRow[5], employeeRow[6], dob, doj, employeeRow[9]);
        }
    }
}

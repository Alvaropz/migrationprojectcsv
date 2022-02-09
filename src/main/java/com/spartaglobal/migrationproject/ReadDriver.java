package com.spartaglobal.migrationproject;

import java.util.ArrayList;

public class ReadDriver {
    public static void main(String[] args) {
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecords.csv");
        int duplicates = ReadFromCSV.duplicateCheck(data);
    }
}

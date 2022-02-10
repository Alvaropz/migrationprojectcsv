package com.spartaglobal.migrationproject;

import com.spartaglobal.database.CSVEmployeeDAO;

import java.io.BufferedReader;
import java.util.ArrayList;

public class MyThread extends Thread {

    BufferedReader inFile;
    int startPos;
    int endPos;
    CSVEmployeeDAO CSV;
    ArrayList<String[]> data;

    public MyThread(CSVEmployeeDAO CSV, int startPos, int endPos, ArrayList<String[]> data) {
        this.inFile = inFile;
        this.startPos = startPos;
        this.endPos = endPos;
        this.data = data;
        this.CSV = new CSVEmployeeDAO();
    }

    @Override
    public void run()
    {
        CSV.initialise();
        int i;
        for (i = startPos; i < endPos; i++) {
            CSV.insertIndivual(data.get(i));
        }
        System.out.println(i);
    }

}

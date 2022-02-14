package com.spartaglobal.migrationproject;

import com.spartaglobal.database.CSVEmployeeDAO;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyThread extends Thread {

    int startPos;
    int endPos;
    CSVEmployeeDAO CSV;
    ArrayList<Employee> data;

    public MyThread(int startPos, int endPos, ArrayList<Employee> data) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.data = data;

    }

    @Override
    public void run()
    {

        CSV = new CSVEmployeeDAO();
        CSV.initialise();

        try {
            CSV.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int i;
        for (i = startPos; i < endPos; i++) {
            CSV.insertIndivual(data.get(i));
        }
        try {
            CSV.preparedStatement.executeLargeBatch();
            CSV.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

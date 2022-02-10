package com.spartaglobal.migrationproject;

import com.spartaglobal.database.CSVEmployeeDAO;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MultiThreadingManager {

    public static void main(String[] args) {
        DAOFactory factoryType = DAOFactory.getDAOFactory();
        EmployeeDAO employeedao = factoryType.getEmployeeDAO();
        employeedao.createEmployeesTable();
        ArrayList<String[]> data = new ArrayList<>();
        data = ReadFromCSV.read("EmployeeRecords.csv");
        data = DuplicatesHandler.filterDuplicates(data, DuplicatesHandler.arrayDuplicates(data));

        ArrayList<MyThread> threads = loadThreads(10, data);

        ArrayList<Thread> allThreads = new ArrayList<>();
        //threads.get(0).run();
        Long Starttime = System.nanoTime();
        for (MyThread t: threads) {
            Thread newThread = new Thread(t);
            allThreads.add(newThread);
            newThread.start();
        }
        Long endTime = System.nanoTime() - Starttime;

        for (Thread t: allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finishing " + endTime);
    }
    //takes in the amount of threads to use, and then runs them
    public static ArrayList<MyThread> loadThreads(int amountOfThreads, ArrayList<String[]> data)
    {
        ArrayList<MyThread> allThreads = new ArrayList<>();


        //get the amount of lines in the file and then how many each thread will handle
        Path path = Paths.get("EmployeeRecords.CSV");
        int Fullstacksize = 0;
        try {
            Fullstacksize = (int) Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        int arrayStackSize = Fullstacksize/amountOfThreads;
        int stackCounter = 0;
        for(int i = 0; i < amountOfThreads; i++)
        {
            CSVEmployeeDAO CSV = new CSVEmployeeDAO();
            MyThread newThread;
            if(i+1 == amountOfThreads)
            {
                newThread = new MyThread(CSV, stackCounter, (int) data.stream().count(), data);
            }
            else
            {
                int endPos = stackCounter+arrayStackSize;
                newThread = new MyThread(CSV, stackCounter+1, endPos, data);
                stackCounter += arrayStackSize;
            }
            allThreads.add(newThread);

        }
        return allThreads;
    }
}

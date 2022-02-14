package com.spartaglobal.migrationproject;

import com.spartaglobal.database.CSVEmployeeDAO;
import com.spartaglobal.database.DAOFactory;
import com.spartaglobal.database.EmployeeDAO;
import com.spartaglobal.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import static com.mysql.cj.conf.PropertyKey.logger;

public class MultiThreadingManager {

    public static Logger logger = LogManager.getLogger("ThreadingManager");
    public static void runThreads(ArrayList<MyThread> threads)
    {
        ArrayList<Thread> allThreads = new ArrayList<>();
        Long Starttime = System.nanoTime();
        for (MyThread t: threads) {
            Thread newThread = new Thread(t);
            allThreads.add(newThread);
            newThread.start();
        }

        for (Thread t: allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Long endTime = System.nanoTime() - Starttime;
        logger.info("System was ran for " + allThreads.stream().count() + " which took a total of: " + endTime + " nanoseconds");
    }

    public static ArrayList<MyThread> loadThreads(int amountOfThreads, ArrayList<Employee> data)
    {
        logger.info("LoadThreads method called");
        ArrayList<MyThread> allThreads = new ArrayList<>();

        int Fullstacksize = (int) data.stream().count();
        int arrayStackSize = Fullstacksize/amountOfThreads;
        int stackCounter = 0;

        for(int i = 0; i < amountOfThreads; i++)
        {
            MyThread newThread;

            if(i+1 == amountOfThreads)
            {
                newThread = new MyThread(stackCounter, (int) data.stream().count(), data);
            }
            else
            {
                int endPos = stackCounter+arrayStackSize;

                newThread = new MyThread(stackCounter, endPos, data);
                stackCounter += arrayStackSize;
            }
            allThreads.add(newThread);

        }
        return allThreads;
    }
}

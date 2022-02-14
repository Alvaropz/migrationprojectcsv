package com.spartaglobal;
import com.spartaglobal.migrationproject.DuplicatesHandler;
import com.spartaglobal.migrationproject.Employee;
import com.spartaglobal.migrationproject.ReadFromCSV;
import com.spartaglobal.migrationproject.StreamsClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class StreamsCompareMain {
    public static Logger logger = LogManager.getLogger("Stream/Buffered readers comparison");
    public static void main(String[] args) {
        logger.info("Reading methods comparison initialized");
        logger.info("Starting stream reader");
        long streamStartTime = System.nanoTime();
        StreamsClass stream = new StreamsClass();
        try {
            List<Employee> records = Files.lines(Path.of("EmployeeRecordsLarge.csv"))
                    .skip(1)
                    .map(s -> {
                        String[] values = s.split(",");
                        return new Employee(values[0], values[1], values[2],
                                values[3], values[4], values[5], values[6],
                                values[7], values[8], values[9]);})
                    .toList();

            List<Employee> employeesList = records.stream()
                    .distinct().toList();
            ArrayList<Employee> employeesArrayList = new ArrayList<>(employeesList);

        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("IO exception thrown");
        }
        long streamEndTime = System.nanoTime();
        logger.info("Stream reader completed in: " + (streamEndTime - streamStartTime) + " nanoseconds");
        System.out.println("The Stream took: " + TimeUnit.NANOSECONDS.toMillis((streamEndTime - streamStartTime)) + " milliseconds to complete");

        logger.info("Starting buffered reader");
        long bufferedReaderStartTime = System.nanoTime();
        ArrayList<String[]> data = ReadFromCSV.read("EmployeeRecordsLarge.csv");
        List<String> duplicates = DuplicatesHandler.arrayDuplicates(data);
        data = DuplicatesHandler.filterDuplicates(data, duplicates);
        long bufferedReaderEndTime = System.nanoTime();
        logger.info("Buffered reader completed in: " + (bufferedReaderEndTime - bufferedReaderStartTime) + " nanoseconds");
        System.out.println("The buffered reader took: " + TimeUnit.NANOSECONDS.toSeconds((bufferedReaderEndTime - bufferedReaderStartTime)) + " seconds to complete");
    }
}



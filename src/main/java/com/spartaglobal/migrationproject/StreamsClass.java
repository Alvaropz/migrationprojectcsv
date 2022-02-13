package com.spartaglobal.migrationproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsClass {
    public static Logger logger = LogManager.getLogger("Stream File Reader");
    public List<Employee> recordsGet(){
        try {
            List<Employee> records = Files.lines(Path.of("EmployeeRecordsLarge.csv"))
                    .skip(1)
                    .map(s -> {
                        String[] values = s.split(",");
                        return new Employee(values[0], values[1], values[2],
                                values[3], values[4], values[5], values[6],
                                values[7], values[8], values[9]);})
                    .toList();
            return records;
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("IOException Thrown");
            return null;
        }
    }

    public ArrayList<Employee> dataGet() {
        logger.info("Starting stream reader");
        List<Employee> records = recordsGet();
        logger.info("Removing duplicates");
        List<Employee> employeesList = records.stream()
                .distinct().toList();
        ArrayList<Employee> employeesArrayList = new ArrayList<>(employeesList);
        return employeesArrayList;
    }

    public ArrayList<Employee> getDuplicates(){
        logger.info("Finding duplicates");
        List<Employee> records = recordsGet();
        List<Employee> duplicates = records.stream()
                .collect(Collectors.groupingBy(Function.identity()
                        , Collectors.counting()))
                .entrySet().stream()
                .filter((m -> m.getValue() > 1))
                .map(Map.Entry::getKey)
                .toList();
        ArrayList<Employee> duplicatesArrayList = new ArrayList<>(duplicates);
        return duplicatesArrayList;
    }

}
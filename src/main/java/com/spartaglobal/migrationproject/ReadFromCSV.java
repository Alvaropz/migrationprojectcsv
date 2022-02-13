package com.spartaglobal.migrationproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadFromCSV {

    public static Logger logger = LogManager.getLogger("File Reader");

    public static ArrayList<String[]> read(String csv) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            logger.info("File Read");
            logger.info("Reading file lines");
            while ((line = br.readLine()) != null) {

                String[] lines = (line.split(","));
                data.add(lines);
            }
        } catch (IOException e) {
            logger.error("File read error");
            e.printStackTrace();
        }
        //System.out.println(data);
        logger.info("File read complete");
        return data;
    }
}

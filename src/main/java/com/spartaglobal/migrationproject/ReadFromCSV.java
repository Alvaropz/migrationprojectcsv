package com.spartaglobal.migrationproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromCSV {
    public static Logger logger = LogManager.getLogger("Stream/Buffered readers comparison");
    public static ArrayList<String[]> read(String csv) {
        logger.info("read method called");
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] lines = (line.split(","));
                data.add(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

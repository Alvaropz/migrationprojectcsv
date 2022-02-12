package com.spartaglobal.migrationproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromCSV {
    public static ArrayList<String[]> read(String csv) {
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
        //System.out.println(data);
        return data;
    }
}

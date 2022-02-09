package com.spartaglobal.migrationproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFromCSV {
    public static ArrayList<String[]> read(String csv) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Arrays.toString(line.split(",")));
                String[] lines = (line.split(","));
                data.add(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
            public static int duplicateCheck(ArrayList<String[]> data){
                int duplicates = 0;
                List<String> dupids = new ArrayList<>();
                for(String[] i : data) {
                    String id = i[0];
                    int f = 0;
                    if (dupids.contains(id)) {
                        continue;
                    }
                    for (String[] x : data) {
                        if (x[0].equals(id)) {
                            f++;
                            if (f > 1 && dupids.contains(id) == false) {
                                dupids.add(id);
                                duplicates++;
                            }
                        }
                    }
                }
                System.out.println(dupids);
                return duplicates;
            }
        }

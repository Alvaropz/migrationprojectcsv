package com.spartaglobal.migrationproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadFromCSV {
    public static void read(String csv){
        try(BufferedReader br = new BufferedReader(new FileReader(csv))){
            String line;
            int duplicates = 0;
            int f = 0;
            ArrayList<String[]> data = new ArrayList<>();
            while((line = br.readLine()) != null){
                //System.out.println(Arrays.toString(line.split(",")));
                String[] lines = (line.split(","));
                System.out.println(Arrays.toString(lines));
                data.add(lines);
            }

            for(String[] i : data){
                String id = i[0];
                for(String[] x : data){
                    String id2 = x[0];
                    if(id.equals(id2)){
                        f++;
                        if(f > 1){
                            duplicates++;
                        }
                    }
                }

            }
            System.out.println(duplicates);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

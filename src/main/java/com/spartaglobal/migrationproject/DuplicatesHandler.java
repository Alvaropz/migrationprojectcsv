package com.spartaglobal.migrationproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DuplicatesHandler {

    public static int duplicateCheck(ArrayList<String[]> data){
        int duplicates = 0;
        List<String> dupids = new ArrayList<>();
        List<String> safe = new ArrayList<>();
        for(String[] i : data) {
            String id = i[0];
            if(id.equals((String)i[0]) && i[1].length() <= 3 && i[2].equals((String)i[2]) && i[3].length() == 1 && i[4].equals((String)i[4])
                    && i[5].length() == 1 && i[6].contains("@") && i[7].contains("/") && i[8].equals((String) i[8])){
                safe.add(id);
                continue;
            }
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
        return duplicates;
    }

        public static List<String> arrayDuplicates(ArrayList<String[]> data){
        data.remove(0);
        List<String> dupids = new ArrayList<>();
        int dups = 0;
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
                        dups++;
                        dupids.add(id);
                    }
                }
            }
        }
        return dupids;
    }

    public static ArrayList<String[]> filterDuplicates(ArrayList<String[]> data, List<String> duplicates) {
        for (Iterator<String[]> iterator = data.iterator(); iterator.hasNext();) {
            String[] row = iterator.next();
            if(duplicates.contains(row[0])) {
                iterator.remove();
                duplicates.remove(row[0]);
            }
        }
        return data;
    }
}

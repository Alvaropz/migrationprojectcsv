package com.spartaglobal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DuplicatesHandler {

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

        public static List<String> arrayDuplicates(ArrayList<String[]> data){
        data.remove(0);
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
                    }
                }
            }
        }
        return dupids;
    }

    public static ArrayList<String[]> filterDuplicates(ArrayList<String[]> data, List<String> duplicates) {
        System.out.println(duplicates);
        for (Iterator<String[]> iterator = data.iterator(); iterator.hasNext();) {
            String[] row = iterator.next();
            if(duplicates.contains(row[0])) {
                iterator.remove();
                duplicates.remove(row[0]);
            }
        }
        System.out.println(duplicates);
        return data;
    }
}

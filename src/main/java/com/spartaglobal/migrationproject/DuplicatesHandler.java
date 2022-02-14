package com.spartaglobal.migrationproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DuplicatesHandler {

    private static Logger logger = LogManager.getLogger("Duplicates Handler Class");

    // This method is only used in the Testing Reader file to check corrupted data.
    public static int duplicateCheck(ArrayList<String[]> data){
        logger.info("Duplicate Check method called");
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
        logger.info("Retrieved an array of Strings with all duplicates in the list");
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
        logger.info("List retrieved after filtering the duplicate data");
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

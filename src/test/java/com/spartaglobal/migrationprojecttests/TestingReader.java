package com.spartaglobal.migrationprojecttests;


import com.spartaglobal.migrationproject.ReadFromCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestingReader {

    @Test
    @DisplayName("given a line of data, make sure it is record properly.")
    void checkDataisReadCorrectly()
    {
        ReadFromCSV frc = new ReadFromCSV();
        ArrayList<String[]> al =  frc.read("test1");
        ArrayList<String[]> Actual = new ArrayList<>();
        Actual.add(new String[]{"Emp ID", "Name Prefix", "First Name", "Middle Initial", "Last Name", "Gender", "E Mail", "Date of Birth", "Date of Joining", "Salary"});
        Assertions.assertEquals(Arrays.stream(al.get(0)).toList(), Arrays.stream(Actual.get(0)).toList());
    }

    @Test
    @DisplayName("given lines of data, make sure Duplicates are picked up.")
    void checkDuplicatesarereturned()
    {
        ReadFromCSV frc = new ReadFromCSV();
        ArrayList<String[]> al =  frc.read("test2");
        ArrayList<String[]> Actual = new ArrayList<>();

        Assertions.assertEquals(1, frc.duplicateCheck(al));
    }

    @Test
    @DisplayName("Given there are no duplicates, make sure it returns 0")
    void checkDuplicatesarenotReturned()
    {
        ReadFromCSV frc = new ReadFromCSV();
        ArrayList<String[]> al =  frc.read("test3");
        ArrayList<String[]> Actual = new ArrayList<>();

        Assertions.assertEquals(0, frc.duplicateCheck(al));
    }
}

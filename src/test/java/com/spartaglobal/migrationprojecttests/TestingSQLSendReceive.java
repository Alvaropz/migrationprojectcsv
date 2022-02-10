package com.spartaglobal.migrationprojecttests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestingSQLSendReceive {

    @Test
    @DisplayName("Given an arrayList of positive integers, correctly send and receive the data from SQL")
    public void givenAnArrayListOfPositiveIntegers_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);

        // Pass into Alvaros method
        // Class alvaro = new Class
        // Something results = alvaro.method(testArray)
    }

    @Test
    @DisplayName("Given an arrayList of zeros, correctly send and receive the data from SQL")
    public void givenAnArrayListOfZeros_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(0);
        testArray.add(0);
        testArray.add(0);

    }

    @Test
    @DisplayName("Given an arrayList of negative values, correctly send and receive the data from SQL")
    public void givenAnArrayListOfNegativeValues_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(-1);
        testArray.add(-2);
        testArray.add(-3);

    }

    @Test
    @DisplayName("Given an arrayList of strings, correctly send and receive the data from SQL")
    public void givenAnArrayListOfStrings_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add("test1");
        testArray.add("test2");
        testArray.add("test3");

    }

    @Test
    @DisplayName("Given an arrayList of strings and integers, correctly send and receive the data from SQL")
    public void givenAnArrayListOfStringsAndIntegers_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(1);
        testArray.add("test2");
        testArray.add(3);

    }

    @Test
    @DisplayName("Given an arrayList of null, correctly send and receive the data from SQL")
    public void givenAnArrayListOfNull_CorrectlySendAndReceiveBackToJava(){
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(null);
        testArray.add(null);
        testArray.add(null);

    }
}

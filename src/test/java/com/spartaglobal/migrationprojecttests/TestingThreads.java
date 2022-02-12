package com.spartaglobal.migrationprojecttests;

import com.spartaglobal.migrationproject.DuplicatesHandler;
import com.spartaglobal.migrationproject.MultiThreadingManager;
import com.spartaglobal.migrationproject.MyThread;
import com.spartaglobal.migrationproject.ReadFromCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestingThreads {

    @Test
    @DisplayName("Test for correct number of threads created")
    void numberin(){
        int num_threads = 5;
        ReadFromCSV frc = new ReadFromCSV();
        ArrayList<String[]> data =  frc.read("test1");
        ArrayList<MyThread> threads = MultiThreadingManager.loadThreads(num_threads,data);
        int actual_num_threads = threads.size();
        Assertions.assertEquals(num_threads,actual_num_threads);
    }
}

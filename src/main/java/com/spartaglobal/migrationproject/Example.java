package com.spartaglobal.migrationproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {
    public static Logger logger = LogManager.getLogger("Sort Manager Logger");
    public static void main(String[] args) {
        logger.info("This is a test log to a file");
    }
}

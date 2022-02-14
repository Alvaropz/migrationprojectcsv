package com.spartaglobal.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DAOFactory {

    private static Logger logger = LogManager.getLogger("DAOFactory Logger");

    public abstract EmployeeDAO getEmployeeDAO();

    public static DAOFactory getDAOFactory() {
        logger.info("CSVDAOFactory class returned");
        return new CSVDAOFactory();
    }
}
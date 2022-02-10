package com.spartaglobal.database;

public abstract class DAOFactory {

    public abstract EmployeeDAO getEmployeeDAO();

    public static DAOFactory getDAOFactory() {
        return new CSVDAOFactory();
    }
}
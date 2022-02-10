package com.spartaglobal.database;

public abstract class DAOFactory {

    public static final int CSV = 1;

    public abstract EmployeeDAO getEmployeeDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case CSV:
                return new CSVDAOFactory();
            default:
                return null;
        }
    }
}
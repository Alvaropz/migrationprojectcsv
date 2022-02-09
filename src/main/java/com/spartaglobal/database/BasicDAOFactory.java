package com.spartaglobal.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BasicDAOFactory extends DAOFactory{

    BasicDAOFactory() {}

    private static Connection connection = null;

    public static Connection getConnectionDAO() throws IOException, SQLException {
        if (connection == null) {
            Properties props = new Properties();
            props.load(new FileReader("mysql.properties"));
            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword"));
        }
        return connection;
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new BasicEmployeeDAO();
    }

    public static void closeConnection() throws SQLException{
        if(connection != null) {
            connection.close();
        }
    }


}

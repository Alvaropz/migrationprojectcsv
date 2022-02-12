package com.spartaglobal.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CSVDAOFactory extends DAOFactory{

    CSVDAOFactory() {}

    private static Logger logger = LogManager.getLogger("CSVEmployeeDAO Logger");

    private static Connection connection = null;

    public static Connection getConnectionDAO() throws IOException, SQLException {
        logger.info("Database connection");
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
        logger.info("CSVEmployeeDAO class returned");
        return new CSVEmployeeDAO();
    }

    public static void closeConnection() throws SQLException{
        logger.info("Database connection close");
        if(connection != null) {
            connection.close();
        }
    }


}

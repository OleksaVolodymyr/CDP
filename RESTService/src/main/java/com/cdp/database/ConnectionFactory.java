package com.cdp.database;

import com.mysql.jdbc.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
    private static final String URL = "jdbc:mysql://localhost:3306/logs";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static ConnectionFactory instance = new ConnectionFactory();


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            Properties property = new Properties();
            property.setProperty("user", USER);
            property.setProperty("password", PASSWORD);
            property.setProperty("useUnicode", "true");
            property.setProperty("characterEncoding", "UTF-8");
            connection = (Connection) DriverManager.getConnection(URL, property);
        } catch (SQLException e) {
            LOG.error("ERROR: Unable to Connect to Database." + e.getMessage());
        }
        return connection;
    }

}

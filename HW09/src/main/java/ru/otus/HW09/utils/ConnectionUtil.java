package ru.otus.HW09.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static final String DB_URL_PROPERTY_NAME = "db.url";
    public static final String DB_USER_PROPERTY_NAME = "db.user";
    public static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    public static ConnectionUtil instance;

    private Connection connection;

    private ConnectionUtil() throws ClassNotFoundException, IOException, SQLException {
        PropertiesUtil props = new PropertiesUtil("config.properties");
        String dbUrl = props.get(DB_URL_PROPERTY_NAME);
        String dbUser = props.get(DB_USER_PROPERTY_NAME);
        String dbPassword = props.get(DB_PASSWORD_PROPERTY_NAME);

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private ConnectionUtil(String dbUrl, String dbUser, String dbPassword) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public static ConnectionUtil getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null) {
            instance = new ConnectionUtil();
        }
        return instance;
    }

    public static ConnectionUtil getInstance(String dbUrl, String dbUser, String dbPassword) throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new ConnectionUtil(dbUrl, dbUser, dbPassword);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

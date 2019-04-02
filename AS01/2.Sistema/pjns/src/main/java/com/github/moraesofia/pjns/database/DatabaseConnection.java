package com.github.moraesofia.pjns.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String PROPERTIES_FILE = "connection.properties";

    /**
     * Url of the database server.
     */
    private static String url;

    /**
     * Username to access the database.
     */
    private static String username;

    /**
     * Password to access the database.
     */
    private static String password;

    /**
     * Method that connects to the database.
     *
     * @return database connection.
     */
    public static Connection connect() throws ClassNotFoundException, IOException,
            SQLException, IllegalAccessException, InstantiationException {
        Properties prop = getProperties();
        username = prop.getProperty("Username");
        password = prop.getProperty("Password");
        url = prop.getProperty("Url");
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, username, password);
    }

    public static Properties getProperties() throws IOException {
        Properties props = new Properties();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PROPERTIES_FILE);
        props.load(inputStream);
        return props;
    }

}

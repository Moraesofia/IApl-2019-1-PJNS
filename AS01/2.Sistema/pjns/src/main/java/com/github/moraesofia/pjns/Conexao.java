package com.github.moraesofia.pjns;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private static String PROP_FILE = "connection.properties";

    /**
     * Status of the database connection.
     */
    protected static String status = "";

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
    public static Connection getConnection() {
        Connection c = null;

        try {
            Properties prop = getProp();
            username = prop.getProperty("Username");
            password = prop.getProperty("Password");
            url = prop.getProperty("Url");

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            c = DriverManager.getConnection(url, username, password);
            status = "Connection established";
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | SQLException | IOException e) {
            status = e.getMessage();
            e.printStackTrace();
        }

        return c;
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream file = new FileInputStream(
                classLoader.getResource(PROP_FILE).getFile());
        props.load(file);
        return props;
    }

}

package com.fuelordering;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = DatabaseConnection.class.getResourceAsStream("/db.properties")) {
                Properties props = new Properties();
                props.load(input);
                String url = props.getProperty("url");
                String user = props.getProperty("username");
                String pass = props.getProperty("password");

                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("✅ Connected to Database!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

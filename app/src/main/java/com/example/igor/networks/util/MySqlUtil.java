package com.example.igor.networks.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Igor Hnes on 6/6/17.
 */

public class MySqlUtil {


    public static final String URL = "jdbc:postgresql://ec2-23-23-93-255.compute-1.amazonaws.com:5432/d5t06k85ohsutd?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    public static final String USERNAME = "zqrtvxhfqmdjft";
    public static final String PASS = "5946f5d3a5434dc18a5fd32dc6759c9b62b37cb0e687cb7e1f80b27dbd09c7ba";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASS);
    }
}


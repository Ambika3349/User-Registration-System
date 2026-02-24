package com.user;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Load Oracle Driver
            Class.forName("oracle.jdbc.OracleDriver");

            // Check your database details carefully
            String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"'
            String user = "sa";
            String password = "";   // your real password

            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Database Connected Successfully");
            }

        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }

        return conn;
    }

}


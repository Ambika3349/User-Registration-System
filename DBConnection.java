package com.user;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Load Oracle Driver
            Class.forName("org.h2.Driver");

            // Check your database details carefully
            String url = "jdbc:h2:file:./data/testdb";
            String user = "sa";
            String password = "";   // your real password

            conn = DriverManager.getConnection(url, user, password);
            
            Statement st = conn.createStatement();

st.execute(
    "CREATE TABLE IF NOT EXISTS users (" +
    "id INT AUTO_INCREMENT PRIMARY KEY, " +
    "name VARCHAR(100), " +
    "email VARCHAR(100), " +
    "password VARCHAR(100))"
);

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




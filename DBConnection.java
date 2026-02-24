package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
    "CREATE TABLE IF NOT EXISTS USERS_REG (" +
    "USER_ID INT PRIMARY KEY, " +
    "USERNAME VARCHAR(100), " +
    "DOB DATE, " +
    "PASSWORD VARCHAR(100), " +
    "QUESTION1 VARCHAR(255), " +
    "ANSWER1 VARCHAR(255), " +
    "QUESTION2 VARCHAR(255), " +
    "ANSWER2 VARCHAR(255), " +
    "PWD_CHANGE_DATE DATE)"
);
            System.out.println("H2 Table Created Successfully");

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







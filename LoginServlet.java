package com.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn=DBConnection.getConnection();
            

            String sql = "SELECT PASSWORD, PWD_CHANGE_DATE FROM USERS_REG WHERE USER_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(userId));

            rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("PASSWORD");
                Date pwdDate = rs.getDate("PWD_CHANGE_DATE");

                // Check password match
                if (dbPassword.equals(password)) {

                    // Check password expiry
                    if (pwdDate != null) {
                        LocalDate changeDate = pwdDate.toLocalDate();
                        long days = ChronoUnit.DAYS.between(changeDate, LocalDate.now());

                        if (days > 30) {
                        	out.println("<script>");
                            out.println("alert('Your Password is Expired. Please Change');");
                            out.println("location='login.jsp';");
                            out.println("</script>");
                            return;
                        }
                    }

                    // Login success
                    out.println("<h3>Login Successful</h3>");
                    out.println("<a href='login.jsp'>Go Back</a>");

                } else {
                    out.println("<h3>Invalid Password</h3>");
                }

            } else {
                out.println("<h3>User ID not found</h3>");
            }

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
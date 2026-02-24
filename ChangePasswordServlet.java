package com.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String userId = req.getParameter("userId");
        String oldPwd = req.getParameter("oldPassword");
        String newPwd = req.getParameter("newPassword");
        String confirmPwd = req.getParameter("confirmPassword");

        // Basic validation
        if (userId == null || oldPwd == null || newPwd == null || confirmPwd == null ||
            userId.isEmpty() || oldPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {

            out.println("<h3>All fields are required</h3>");
            return;
        }

        if (!newPwd.equals(confirmPwd)) {
            out.println("<h3>New Password and Confirm Password must match</h3>");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Get connection from DBConnection (fixed)
            conn = DBConnection.getConnection();

            String sql = "UPDATE USERS_REG SET PASSWORD=?, PWD_CHANGE_DATE=SYSDATE WHERE USER_ID=? AND PASSWORD=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, newPwd);
            ps.setInt(2, Integer.parseInt(userId));  // Fixed datatype
            ps.setString(3, oldPwd);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                res.sendRedirect("login.jsp?cp=success");
                return;
            } else {
                res.sendRedirect("login.jsp?cp=fail");
                return;
            }

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
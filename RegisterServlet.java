package com.user;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    request.getRequestDispatcher("register.jsp").forward(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String dob = request.getParameter("dob");
        String password = request.getParameter("password");
        String q1 = request.getParameter("q1");
        String a1 = request.getParameter("a1");
        String q2 = request.getParameter("q2");
        String a2 = request.getParameter("a2");
        String pwdDate = request.getParameter("pwdDate");

        // Null and empty validation
        if (userId == null || userId.trim().isEmpty() ||
            userName == null || userName.trim().isEmpty() ||
            dob == null || dob.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            a1 == null || a1.trim().isEmpty() ||
            a2 == null || a2.trim().isEmpty() ||
            pwdDate == null || pwdDate.trim().isEmpty()) {

            out.println("<script>alert('All fields are required');history.back();</script>");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
               con = DBConnection.getConnection();

            String sql = "INSERT INTO USERS_REG VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(userId));
            ps.setString(2, userName);
            ps.setDate(3, java.sql.Date.valueOf(dob));
            ps.setString(4, password);
            ps.setString(5, q1); 
            ps.setString(6, a1.trim().toUpperCase());
            ps.setString(7, q2);
            ps.setString(8, a2.trim().toUpperCase());
            ps.setDate(9, java.sql.Date.valueOf(pwdDate));

            ps.executeUpdate();

            out.println("<script>");
            out.println("alert('Registration Successful!');");
            out.println("location='login.jsp';");
            out.println("</script>");

        } catch (SQLIntegrityConstraintViolationException e) {
            out.println("<script>alert('User ID already exists');history.back();</script>");
        } catch (Exception e) {
            e.printStackTrace(); // shows real error in console
            out.println("<script>alert('Database Error: " + e.getMessage() + "');history.back();</script>");
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {}
        }
    }
}
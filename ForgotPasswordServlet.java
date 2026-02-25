package com.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String userId = req.getParameter("userId");
        String ans1 = req.getParameter("a1");
        String ans2 = req.getParameter("a2");

        // Trim values
        if (userId != null) userId = userId.trim();
        if (ans1 != null) ans1 = ans1.trim();
        if (ans2 != null) ans2 = ans2.trim();


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	conn = DBConnection.getConnection();

            // ===============================
            // STEP 1 : If answers not entered → Show questions
            // ===============================
            if (ans1 == null || ans2 == null || ans1.isEmpty() || ans2.isEmpty()) {

                if (userId == null || userId.isEmpty()) {
                    out.println("<script>alert('User ID is required');history.back();</script>");
                    return;
                }

                ps = conn.prepareStatement(
                        "SELECT QUESTION1, QUESTION2 FROM USERS_REG WHERE USER_ID=?");

                ps.setString(1, userId);
                rs = ps.executeQuery();

                if (rs.next()) {

                    out.println("<html><body style='font-family:Arial;'>");
                    out.println("<h2 align='center'>Forgot Password</h2>");

                    out.println("<form action='ForgotPasswordServlet' method='post' style='width:300px;margin:auto;'>");

                    out.println("User ID:<br>");
                    out.println("<input name='userId' value='" + userId + "' readonly><br><br>");

                    out.println("Question 1:<br>");
                    out.println("<input value='" + rs.getString("QUESTION1") + "' readonly><br>");
                    out.println("Answer 1:<br>");
                    out.println("<input name='a1' required><br><br>");

                    out.println("Question 2:<br>");
                    out.println("<input value='" + rs.getString("QUESTION2") + "' readonly><br>");
                    out.println("Answer 2:<br>");
                    out.println("<input name='a2' required><br><br>");

                    out.println("<button type='submit' style='background:black;color:white;width:100%;height:35px;'>SUBMIT</button>");

                    out.println("</form></body></html>");

                } else {
                    out.println("<script>alert('User not found');history.back();</script>");
                }
            }

            // ===============================
            // STEP 2 : Verify answers
            // ===============================
            else {

                ps = conn.prepareStatement(
                        "SELECT PASSWORD FROM USERS_REG WHERE USER_ID=? AND UPPER(ANSWER1)=? AND UPPER(ANSWER2)=?");

                ps.setInt(1, Integer.parseInt(userId));
                ps.setString(2, ans1.trim().toUpperCase());
                ps.setString(3, ans2.trim().toUpperCase());

                rs = ps.executeQuery();

                if (rs.next()) {

                    out.println("<script>");
                    out.println("alert('Answers verified. Please reset your password');");
                    out.println("location='login.jsp';");
                    out.println("</script>");

                } else {
                    out.println("<script>alert('Wrong answers');history.back();</script>");
                }
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

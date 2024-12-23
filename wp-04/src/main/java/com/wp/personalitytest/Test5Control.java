package com.wp.personalitytest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/test5")
public class Test5Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Test5Control() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/start.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        String q7Param = request.getParameter("q7");
        String q8Param = request.getParameter("q8");

        if (email == null || q7Param == null || q8Param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 입력 필드가 비어 있습니다.");
            return;
        }

        int q7 = Integer.parseInt(q7Param);
        int q8 = Integer.parseInt(q8Param);

        System.out.println("Test5Control - Email: " + email + ", Q7: " + q7 + ", Q8: " + q8);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            stmt = conn.prepareStatement("INSERT INTO test_results (email, question_id, answer) VALUES (?, ?, ?), (?, ?, ?)");
            stmt.setString(1, email);
            stmt.setInt(2, 7);
            stmt.setInt(3, q7);
            stmt.setString(4, email);
            stmt.setInt(5, 8);
            stmt.setInt(6, q8);
            stmt.executeUpdate();

            session.setAttribute("q7", q7);
            session.setAttribute("q8", q8);

            response.sendRedirect("test5.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

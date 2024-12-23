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

@WebServlet("/test2")
public class Test2Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Test2Control() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/start.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        String q1Param = request.getParameter("q1");
        String q2Param = request.getParameter("q2");

        if (email == null || q1Param == null || q2Param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 입력 필드가 비어 있습니다.");
            return;
        }

        int q1 = Integer.parseInt(q1Param);
        int q2 = Integer.parseInt(q2Param);

        System.out.println("Test2Control - Email: " + email + ", Q1: " + q1 + ", Q2: " + q2);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            stmt = conn.prepareStatement("INSERT INTO test_results (email, question_id, answer) VALUES (?, ?, ?), (?, ?, ?)");
            stmt.setString(1, email);
            stmt.setInt(2, 1);
            stmt.setInt(3, q1);
            stmt.setString(4, email);
            stmt.setInt(5, 2);
            stmt.setInt(6, q2);
            stmt.executeUpdate();

            session.setAttribute("q1", q1);
            session.setAttribute("q2", q2);

            response.sendRedirect("test2.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

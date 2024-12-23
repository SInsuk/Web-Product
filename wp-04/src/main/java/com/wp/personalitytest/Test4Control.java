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

@WebServlet("/test4")
public class Test4Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Test4Control() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/start.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        String q5Param = request.getParameter("q5");
        String q6Param = request.getParameter("q6");

        if (email == null || q5Param == null || q6Param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 입력 필드가 비어 있습니다.");
            return;
        }

        int q5 = Integer.parseInt(q5Param);
        int q6 = Integer.parseInt(q6Param);

        System.out.println("Test4Control - Email: " + email + ", Q5: " + q5 + ", Q6: " + q6);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            stmt = conn.prepareStatement("INSERT INTO test_results (email, question_id, answer) VALUES (?, ?, ?), (?, ?, ?)");
            stmt.setString(1, email);
            stmt.setInt(2, 5);
            stmt.setInt(3, q5);
            stmt.setString(4, email);
            stmt.setInt(5, 6);
            stmt.setInt(6, q6);
            stmt.executeUpdate();

            session.setAttribute("q5", q5);
            session.setAttribute("q6", q6);

            response.sendRedirect("test4.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

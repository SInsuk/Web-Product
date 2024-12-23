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

@WebServlet("/test3")
public class Test3Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Test3Control() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/start.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        String q3Param = request.getParameter("q3");
        String q4Param = request.getParameter("q4");

        if (email == null || q3Param == null || q4Param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 입력 필드가 비어 있습니다.");
            return;
        }

        int q3 = Integer.parseInt(q3Param);
        int q4 = Integer.parseInt(q4Param);

        System.out.println("Test3Control - Email: " + email + ", Q3: " + q3 + ", Q4: " + q4);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            stmt = conn.prepareStatement("INSERT INTO test_results (email, question_id, answer) VALUES (?, ?, ?), (?, ?, ?)");
            stmt.setString(1, email);
            stmt.setInt(2, 3);
            stmt.setInt(3, q3);
            stmt.setString(4, email);
            stmt.setInt(5, 4);
            stmt.setInt(6, q4);
            stmt.executeUpdate();

            session.setAttribute("q3", q3);
            session.setAttribute("q4", q4);

            response.sendRedirect("test3.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

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

@WebServlet("/delete")
public class DeleteControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteControl() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            // 테스트 결과 삭제
            stmt = conn.prepareStatement("DELETE FROM test_results WHERE email = ?");
            stmt.setString(1, email);
            stmt.executeUpdate();

            // 사용자 정보 삭제
            stmt = conn.prepareStatement("DELETE FROM users WHERE email = ?");
            stmt.setString(1, email);
            stmt.executeUpdate();

            response.sendRedirect("delete_result.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

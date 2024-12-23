package com.wp.personalitytest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/test1")
public class Test1Control extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Test1Control() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true); // 세션이 없으면 생성
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Test1Control - Name: " + name + ", Email: " + email);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            // 기존 사용자 확인
            stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // 기존 사용자
                String userName = rs.getString("name");
                session.setAttribute("name", userName);
                session.setAttribute("email", email);

                request.setAttribute("name", userName);

                RequestDispatcher dispatcher = request.getRequestDispatcher("result");
                dispatcher.forward(request, response);
            } else {
                // 새로운 사용자
                stmt = conn.prepareStatement("INSERT INTO users (email, name, password) VALUES (?, ?, ?)");
                stmt.setString(1, email);
                stmt.setString(2, name);
                stmt.setString(3, password);
                stmt.executeUpdate();

                session.setAttribute("name", name);
                session.setAttribute("email", email);

                response.sendRedirect("test1.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

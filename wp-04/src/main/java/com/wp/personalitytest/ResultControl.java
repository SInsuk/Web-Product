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

@WebServlet("/result")
public class ResultControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResultControl() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/start.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        String name = (String) session.getAttribute("name");

        int totalScore = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            stmt = conn.prepareStatement("SELECT answer FROM test_results WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalScore += rs.getInt("answer");
            }

            String result = "";
            if (totalScore < 20) {
                result = "출친구들이 보는 당신은 부끄럼을 많이 타고, 소심하며, 우유부단하고, 누군가가 곁에서 돌봐줘야만 하며, 본인의 일도 누군가가 대신해서 결정해 줘야만 하고 타인이나 어떤 일에 연루되는 것을 피하는 사람입니다.";
            } else if (totalScore < 30) {
                result = "당신의 친구들은 당신을 집요하며 깐깐한 사람이라고 생각할 수도 있습니다.";
            } else if (totalScore < 40) {
                result = "다른 사람들이 보는 당신은 현명하고, 신중하며, 조심스럽고, 현실적입니다.";
            } else if (totalScore < 50) {
                result = "주위 사람들은 당신을 상큼하고, 발랄하고, 매력적이고, 재미있고, 현실적이면서 늘 즐거운 사람이라고 생각합니다.";
            } else if (totalScore < 60) {
                result = "주위 사람들은 당신이 잘 흥분하고, 상당히 변덕스러우며, 충동적이라고 생각합니다.";
            } else {
                result = "주위 사람들은 당신을 “취급주의” 형 이라고 생각하고 있습니다.";
            }

            request.setAttribute("name", name);
            request.setAttribute("totalScore", totalScore);
            request.setAttribute("result", result);

            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}

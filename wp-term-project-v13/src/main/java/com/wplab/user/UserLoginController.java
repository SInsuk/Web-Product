package com.wplab.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.wplab.service.*;

public class UserLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);
        AdminDAO adminDao = new AdminDAOImplByDBCP(dbcpResourceName);
        
        try {
            UserDO user = dao.getUserByUsernameAndPassword(user_id, password);
            AdminDO admin = adminDao.getAdminByUsernameAndPassword(user_id, password);

            if (user != null || admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user_id);
                response.sendRedirect("postList");
            } else {
                response.sendRedirect("Login.jsp?error=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("Login.jsp?error=1");
        }
    }
}




package com.wplab.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.service.*;

public class UserDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserDeleteController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet 메소드가 필요하지 않다면 빈 상태로 둡니다.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String user_id = request.getParameter("user_id");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);

        UserDO user = new UserDO();
        user.setUser_id(user_id);

        dao.deleteUser(user);

        response.sendRedirect(request.getContextPath() + "/userList");
    }
}



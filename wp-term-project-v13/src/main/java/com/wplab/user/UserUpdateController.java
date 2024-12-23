package com.wplab.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.wplab.service.UserDAO;
import com.wplab.service.UserDAOImplByDBCP;
import com.wplab.service.UserDO;

public class UserUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserUpdateController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String user_id = request.getParameter("user_id");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);

        UserDO user = new UserDO();
        user.setUser_id(user_id);

        user = dao.getUser(user);

        request.setAttribute("user", user);

        RequestDispatcher view = request.getRequestDispatcher("/UserUpdate.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);

        // 이메일 중복 확인 (현재 사용자의 이메일 제외)
        if (dao.isEmailExistExceptUser(email, user_id)) {
            request.setAttribute("errorMessage", "이미 사용중인 이메일입니다.");
            doGet(request, response);
            return;
        }

        UserDO user = new UserDO();
        user.setUser_id(user_id);
        user.setPassword(password);
        user.setEmail(email);

        dao.updateUser(user);

        response.sendRedirect(request.getContextPath() + "/userList");
    }
}




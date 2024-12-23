package com.wplab.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.wplab.service.*;

public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);

        List<UserDO> userList = dao.ListUser();
        request.setAttribute("user_list", userList);

        RequestDispatcher view = request.getRequestDispatcher("/Management.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}



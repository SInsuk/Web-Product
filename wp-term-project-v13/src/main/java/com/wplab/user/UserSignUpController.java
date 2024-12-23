package com.wplab.user;

import java.io.IOException;
import com.wplab.service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserSignUpController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserSignUpController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        UserDAO dao = new UserDAOImplByDBCP(dbcpResourceName);

        // 관리자 아이디 확인
        if (dao.isAdminIdExist(user_id)) {
            request.setAttribute("errorMessage", "관리자용 아이디이므로 사용할 수 없습니다.");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            return;
        }

        // 아이디 중복 확인
        if (dao.isUserIdExist(user_id)) {
            request.setAttribute("errorMessage", "사용중인 아이디입니다.");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            return;
        }
        
        // 이메일 중복 확인
        if (dao.isEmailExist(email) || dao.isAdminEmailExist(email)) {
            request.setAttribute("errorMessage", "사용중인 이메일입니다.");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            return;
        }

        UserDO user = new UserDO();
        user.setUser_id(user_id);
        user.setPassword(password);
        user.setEmail(email);

        dao.addUser(user);

        // 회원가입 후 Login.jsp로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
    }
}


package com.wplab.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.service.*;

public class PostDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostDeleteController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to POST handler
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int post_id = Integer.parseInt(request.getParameter("post_id"));

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        PostDAO dao = new PostDAOImplByDBCP(dbcpResourceName);

        dao.deletePost(post_id);

        response.sendRedirect("postList");
    }
}






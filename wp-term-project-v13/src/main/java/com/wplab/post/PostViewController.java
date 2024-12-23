package com.wplab.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.wplab.service.*;

public class PostViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostViewController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("post_id");
        if (postId == null) {
            response.sendRedirect("postList");
            return;
        }

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        PostDAO postDAO = new PostDAOImplByDBCP(dbcpResourceName);
        PostDO post = postDAO.getPostById(Integer.parseInt(postId));
        List<AttachmentDO> attachments = postDAO.getAttachmentsByPostId(Integer.parseInt(postId));

        if (post == null) {
            response.sendRedirect("postList");
            return;
        }

        request.setAttribute("post", post);
        request.setAttribute("attachments", attachments);
        request.getRequestDispatcher("PostView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}



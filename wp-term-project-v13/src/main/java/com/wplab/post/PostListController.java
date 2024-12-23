package com.wplab.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.wplab.service.*;

public class PostListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 10;

    public PostListController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        PostDAO dao = new PostDAOImplByDBCP(dbcpResourceName);
        List<PostDO> postList;
        int totalPosts;
        int totalPages;

        if (keyword != null && !keyword.trim().isEmpty()) {
            postList = dao.searchPosts(keyword);
            totalPosts = dao.getTotalPostCountForKeyword(keyword);
        } else {
            postList = dao.getPosts(page, PAGE_SIZE);
            totalPosts = dao.getTotalPostCount();
        }

        totalPages = (int) Math.ceil((double) totalPosts / PAGE_SIZE);

        request.setAttribute("post_list", postList);
        request.setAttribute("current_page", page);
        request.setAttribute("total_pages", totalPages);

        RequestDispatcher view = request.getRequestDispatcher("/PostList.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}






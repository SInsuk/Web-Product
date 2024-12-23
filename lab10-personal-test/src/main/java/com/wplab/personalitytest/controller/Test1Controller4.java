package com.wplab.personalitytest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Test1Controller
 */
@WebServlet("/test4")
public class Test1Controller4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test1Controller4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		int q5 = Integer.parseInt(request.getParameter("q5"));
		int q6 = Integer.parseInt(request.getParameter("q6"));
		
		// step #2. data processing using service module
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/start.html");
			return;
		}
			String username = (String)session.getAttribute("username");
			Map<String, Integer> replyStore = (Map<String, Integer>)session.getAttribute("reply_store");
			replyStore.put("q5", q5);
			replyStore.put("q6", q6);
		
		// step #3. output results -> view navigation
		request.setAttribute("username", username);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/test4.jsp");
		view.forward(request, response);
	}

}

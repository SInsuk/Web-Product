package com.wplab.personalitytest.controller;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Test1Controller
 */
public class Test1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test1Controller() {
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
		
		String username = request.getParameter("username");
		
		// step #2. data processing using service module
		HttpSession session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("username", username);
			session.setAttribute("reply_store", new HashMap<String, Integer>());
		}
		
		// step #3. output results -> view navigation
		request.setAttribute("username", username);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/test1.jsp");
		view.forward(request, response);
	}

}

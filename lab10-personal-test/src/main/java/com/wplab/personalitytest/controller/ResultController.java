package com.wplab.personalitytest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wplab.personalitytest.service.*;

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
@WebServlet("/result")
public class ResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultController() {
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
		
		int q9 = Integer.parseInt(request.getParameter("q9"));
		int q10 = Integer.parseInt(request.getParameter("q10"));
		
		// step #2. data processing using service module
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/start.html");
			return;
		}
		String username = (String)session.getAttribute("username");
		Map<String, Integer> replyStore = (Map<String, Integer>)session.getAttribute("reply_store");
		replyStore.put("q9", q9);
		replyStore.put("q10", q10);
		
		//data analysis processing
		TestEvaluationService service = new TestEvaluationService();
		TestResultDO resultDO = service.evaluate(replyStore);
		
		// step #3. output results -> view navigation
		request.setAttribute("username", username);
		request.setAttribute("result", resultDO);
		
		session.invalidate();
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/result.jsp");
		view.forward(request, response);
	}

}

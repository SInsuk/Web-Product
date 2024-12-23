package com.wplab.leveltest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentQuestion = request.getParameter("currentQuestion");
        String answer = request.getParameter("answer");

        if (answer == null || answer.isEmpty()) {
            response.sendRedirect("test_0" + currentQuestion + ".jsp");
            return;
        }

        request.getSession().setAttribute("answer" + currentQuestion, answer);

        int nextQuestion = Integer.parseInt(currentQuestion) + 1;
        if (nextQuestion <= 5) {
            response.sendRedirect("test_0" + nextQuestion + ".jsp");
        } else {
            response.sendRedirect("result.do");
        }
	}

}

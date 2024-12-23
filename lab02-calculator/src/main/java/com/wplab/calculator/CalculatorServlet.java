package com.wplab.calculator;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step #1. get request parameters
		String num1Str = request.getParameter("num1");
		String num2Str = request.getParameter("num2");
		String opStr = request.getParameter("operator");

		Double num1 = 0.0;
		if(num1Str != null && !num1Str.isBlank()) {
			num1 = Double.parseDouble(num1Str);
		}
		
		Double num2 = 0.0;
		if(num2Str != null && !num2Str.isBlank()) {
			num2 = Double.parseDouble(num2Str);
		}
		
		//step #2. data processing
		Double result = 0.0;
		if (opStr.equals("+")) {
			result = num1 + num2;
		}
		else if (opStr.equals("-")) {
			result = num1 - num2;
		}
		else if (opStr.equals("*")) {
			result = num1 * num2;
		}
		
		else if (opStr.equals("/")) {
			result = num1 / num2;
		}
		
		//step 3#. output results
		response.setContentType("text/html; charset=UTF8");
//		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>사칙 연산 계산기</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>게산 결과</h1><hr>");
		out.println("<p>" + num1 + " " + opStr + " " + num2 + " = " + result + "</p>");
		out.println("<br><br>");
//		out.println("<div><a href='Calculator.html'><< 계산기 화면</a></div>");
//		out.println("<div><button onclick='window.history.back();'><< 계산기 화면</button></div>");
		out.println("<div><button onclick='window.location.href=\"Calculator.html\"'><< 계산기 화면</button></div>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.wplab.builtinobjects;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestApiTestServlet
 */
public class RequestApiTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestApiTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");		
		
		// step #2. data processing
		
		// step #3. output results
		response.setContentType("text/html;charset=UTF-8");
		
		response.addHeader("test", "header test");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>HttpServletRequest API Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1HttpServletRequest API Test</h1><hr>");
		out.println("<div style='font-size: 1.2rem;'>");
		out.println("1. 요청 URL: " + request.getRequestURL() + "<br>");
		out.println("2. 요청 Method: " + request.getMethod() + "<br>");
		out.println("3. 요청 쿼리 스트링: " + request.getQueryString() + "<br>");
		out.println("4. 요청 파라미터 목록: <br>");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			out.printf("&nbsp;&nbsp;&nbsp;%s = %s<br>\n", name, request.getParameter(name));
		}
		out.println("5. 요청 헤더 목록: <br>");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			out.printf("&nbsp;&nbsp;&nbsp;%s = %s<br>\n", name, request.getHeader(name));
		}
		out.println("6. 클라이언트 호스트 정보 <br>");
		out.printf("&nbsp;&nbsp;&nbsp;클라이언트 호스트 주소: %s<br>\n", request.getRemoteHost());
		out.printf("&nbsp;&nbsp;&nbsp;클라이언트 호스트 포트: %s<br>\n", request.getRemotePort());
		out.println("</div>");
		out.println("<p><button onclick='window.history.back();'>들어가기</button></p>");
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

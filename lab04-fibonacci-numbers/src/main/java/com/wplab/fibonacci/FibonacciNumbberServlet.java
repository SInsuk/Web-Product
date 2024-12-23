package com.wplab.fibonacci;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FibonacciNumbberServlet
 */
public class FibonacciNumbberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private BigInteger[] fa;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FibonacciNumbberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void init() throws ServletException {
    	super.init();
    	
    	ServletConfig  config = this.getServletConfig();
    	String initCountStr = config.getInitParameter("init_count");
    	int initCount = 100;
    	if (initCountStr != null) {
    		initCount = Integer.parseInt(initCountStr);
    	}
    	
		// TODO Auto-generated method stub
		fa = new BigInteger[100];
		fa[0]=new BigInteger("1");
		fa[1]=new BigInteger("1");
		for(int i=2; i<100; i++) {
			fa[i] = fa[i-2].add(fa[i-1]);
		}
		
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step #1. get request parameters
		//request.setCharacterEncoding("UTF-8);
		String countStr = request.getParameter("count");
		
		int count = 100;
		if(countStr != null && !countStr.isBlank()) {
			count = Integer.parseInt(countStr);
			if(count < 2) count=2;
		}
		
		//step #2. data processing
		
		//step #3. output results
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>피보나치 수열 생성기</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>피보나치 수열 생성 결과</h1><hr>");
		out.println("<p>피보나치 수열 원소 개수: " + count + "</p>");
		for(int i=0; i<count; i++) {
			out.println(i + " : " + fa[i] + "<br>");
		}
		out.println("<br><p><button onclick='window.history.back();'>들어가기</button>");
		out.println("</body>");
		out.println("</html>");
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.wplab.login;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		name="LoginServlet",
		urlPatterns = {"/login.do"},
		initParams = {
				@WebInitParam(
						name="log_file_prefix",
						value="C:\\Users\\Inseok\\Desktop\\WEB\\lab05-login-logging\\log\\login-log-"),
				@WebInitParam(
						name="log_file_suffix",
						value=".log")
			})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PrintWriter logFile = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void destroy() {
		if (logFile != null) {
			logFile.close();
		}
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String logFileName ="C:\\Users\\Inseok\\Desktop\\WEB\\lab05-login-logging\\log\\login-log-" + sdf.format(new Date()) + ".log"; 
		
		ServletConfig config = getServletConfig();
		String logFilePrefix = config.getInitParameter("log_file_prefix");
		String logFileSuffix = config.getInitParameter("log_file_suffix");
//		String logFileName = logFilePrefix + sdf.format(new Date() + logFileSuffix);
		
		String logFileName = (new StringBuilder()).append(logFilePrefix).append(sdf.format(new Date())).append(logFileSuffix).toString();
		
		try {
			logFile = new PrintWriter(new FileWriter(logFileName, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		//step #2. data processing
		//do member authentication
		String greeting = null;
		if(userid.equals("gdhong") && passwd.equals("1234")) {
			greeting = String.format("반갑습니다, %s님!", userid);
			
			if(logFile != null) {
				logFile.printf("%s - %s", (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()), userid);
			}
		}
		else {
			greeting = "사용자 아이디 또는 비밀번호가 맞지 않습니다!";
		}
		
		//step #3. output results
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>로그인 결과</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>로그인 결과</h1><hr>");
		out.println("<div style='font-size: 1.2rem;'>");
		out.println(greeting + "<br><br>");
		out.println("현재 서비스 준비 중입니다!...<br><br><br>");
		out.println("<p><button onclick='window.history.back();'>들어가기</button></p>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}

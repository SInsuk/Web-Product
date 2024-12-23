package com.wplab.memberjoin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberJoinServlet
 */

//@WebServlet
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
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
		//step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
						
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setName(request.getParameter("name"));
		memberInfo.setId(request.getParameter("id"));
		memberInfo.setPasswd(request.getParameter("passwd"));
		memberInfo.setGender(request.getParameter("gender"));
		memberInfo.setInotice(request.getParameter("inotice"));
		memberInfo.setCnotice(request.getParameter("cnotioce"));
		memberInfo.setDnotice(request.getParameter("dnotioce"));
		memberInfo.setJob(request.getParameter("job"));
		
		
		//step #2. data processing
		//data validation
		//create member account
		//save member account to DB
		
		//step #3. ouput results
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>회원 가입 결과 화면</title>");
		out.println("</head>");
		out.println("<body style='padding-left; 100px;'>");
		out.println("<h1>회원 가입 결과</h1><hr>");
		out.println("<p>이름: " + memberInfo.getName() + "</p>");
		out.println("<p>아이디: " + memberInfo.getId() + "</p>");
		out.println("<p>비밀번호: " + memberInfo.getPasswd() + "</p>");
		out.println("<p>성별 : " + (memberInfo.getGender().equals("male")? "남성" : "여성") + "</p>");
		out.println("<p>메일 수신 여부: ");
		out.println("&nbsp;&nbsp; 공지메일: " + getNoticeMsg(memberInfo.getInotice()) + "<br>");
		out.println("&nbsp;&nbsp; 광고메일: " + getNoticeMsg(memberInfo.getCnotice()) + "<br>");
		out.println("&nbsp;&nbsp; 배송메일: " + getNoticeMsg(memberInfo.getDnotice()) + "<br></p>");
		out.println("<p>직업: " + memberInfo.getJob() + "</p>");
		out.println("<br><p>상기의 정보로 회원 가입에 성공하였습니다!...</p>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		
	}
	
	private String getNoticeMsg(String notice) {
		if (notice != null && notice.equals("on")) {
			return "수신함";
		}else {
			return "수신하지 않음";
		}
	}

}

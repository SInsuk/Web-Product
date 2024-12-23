<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%!
	int count = 0;

	private int incrementCount(){
		return ++count;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello, JSP!</title>
</head>
<body>
	<h1>Hello, JSP!</h1>
	<p>처음 구현한 JSP Page</p>
	<p>현재 시간 : 
<%	//scriptlet
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd알 hh:mm:ss");
//	out.println(sdf.format(new Date()));
	
%>

<%= sdf.format(new Date()) %> <%-- expression --%>
	</p>	

	<p>페이지 실행 횟수 : <%= incrementCount() %></p>
</body>
</html>
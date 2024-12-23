<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 보기</title>
</head>
<body>
<%
	String code = request.getParameter("code");
	if (code == null) {
		code = "10001";
	}
	
/* 	DBConnectionInfo dbInfo = 
		(DBConnectionInfo)application.getAttribute("jdbc_info");

	GoodsinfoDAO dao = new GoodsinfoDAOImpl(dbInfo); */

	String dbcpResourceName = 
			application.getInitParameter("dbcp_resource_name");
		
	GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
	
	GoodsDO goods = new GoodsDO();
	goods.setCode(code);
	
	goods = dao.getGoods(goods);
	
	if (goods != null) {
%>
		<p>Code=<%= goods.getCode() %>, 
		Title=<%= goods.getTitle() %>, 
		Writer=<%= goods.getWriter() %>, 
		Price=<%= goods.getPrice() %></p>
<%
	}
	else {
%>
		<p>검색한 상품 정보가 없습니다!</p>
<%		
	}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
</head>
<body>
<%
	String dbcpResourceName = 
	application.getInitParameter("dbcp_resource_name");
	
	GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);

	GoodsDO goods = new GoodsDO();
	goods.setCode("10006");
	goods.setTitle("자바 웹을 다루는 기술");
	goods.setWriter("이병승");
	goods.setPrice(32000);
	
	if (dao.insertGoods(goods) == 1) {
%>		
		<p>상품 등록을 정상적으로 처리하였습니다.</p>
<%		
	}
	else {
%>		
		<p>상품 등록에 실패하였습니다.</p>
<%
	}
%>
</body>
</html>
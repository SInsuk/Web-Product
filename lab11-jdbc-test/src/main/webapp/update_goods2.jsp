<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 수정</title>
</head>
<body>
<%
	String dbcpResourceName = 
	application.getInitParameter("dbcp_resource_name");
	
	GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);

	GoodsDO goods = new GoodsDO();
	goods.setCode("10006");
	
	goods = dao.getGoods(goods);
	
	goods.setPrice(28000);
	
	if (dao.updateGoods(goods) == 1) {
%>		
		<p>상품 정보 수정을 정상적으로 처리하였습니다.</p>
<%		
	}
	else {
%>		
		<p>상품 정보 수정에 실패하였습니다.</p>
<%
	}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록 출력</title>
</head>
<body>
<%
/* 	DBConnectionInfo dbInfo = 
		(DBConnectionInfo)application.getAttribute("jdbc_info");

	GoodsinfoDAO dao = new GoodsinfoDAOImpl(dbInfo); */
	
	String dbcpResourceName = 
		application.getInitParameter("dbcp_resource_name");
	
	GoodsinfoDAO dao = new GoodsinfoDAOImplByDBCP(dbcpResourceName);
	
	List<GoodsDO> list = dao.listGoods();
	
	if (list != null) {
		for (GoodsDO goods : list) {
%>
			<p>Code=<%= goods.getCode() %>, 
			Title=<%= goods.getTitle() %>, 
			Writer=<%= goods.getWriter() %>, 
			Price=<%= goods.getPrice() %></p>
<%
		}
	}
%>
</body>
</html>
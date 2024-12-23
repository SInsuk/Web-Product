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
	DBConnectionInfo dbInfo = 
		(DBConnectionInfo)application.getAttribute("jdbc_info");

	// 1. load JDBC driver class
	Class.forName(dbInfo.getDriverClassName());
	
	// 2. create DB connection
	Connection conn = DriverManager.getConnection(
		dbInfo.getUrl(), 
		dbInfo.getUsername(), 
		dbInfo.getPassword());
	
	if (conn != null) {
		// 3. create statement object
		Statement stmt = conn.createStatement();
		
		// 4. execute sql statement
		String sql = "update GOODSINFO set PRICE=28000 where CODE='10006'";
		int result = stmt.executeUpdate(sql);
		
		// 5. process return values
		if (result == 1) {
			out.println(
				"<p>상품 정보 수정을 정상적으로 처리하였습니다.</p>");
		}
		else {
			out.println("<p>상품 정보 수정에 실패하였습니다.</p>");
		}
		
		// 6. disconnect from DB
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
%>
</body>
</html>
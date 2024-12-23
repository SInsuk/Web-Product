<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터베이스 연결 테스트</title>
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
		out.println("Connect to DB successfully...");
		out.println("Disconnect from DB...");
		conn.close();
	}
%>
</body>
</html>
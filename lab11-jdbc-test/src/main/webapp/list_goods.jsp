<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.jdbctest.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록 출력</title>
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
		String sql = "select * from GOODSINFO order by CODE asc";
		ResultSet rs = stmt.executeQuery(sql);
		
		// 5. process return values
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				out.println(
					"<p>code=" + rs.getString("CODE") +
					", title=" + rs.getString("TITLE") +
					", writer=" + rs.getString("WRITER") +
					", price=" + rs.getInt("PRICE") + "</p>");
			}
		}
		
		// 6. disconnect from DB
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
%>
</body>
</html>
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
//		String sql = "select * from GOODSINFO where CODE='10001'";
		String sql = String.format("select * from GOODSINFO where CODE='%s'", code);
		ResultSet rs = stmt.executeQuery(sql);
		
		// 5. process return values
		if (rs.next()) {
			out.println(
				"<p>code=" + rs.getString("CODE") +
				", title=" + rs.getString("TITLE") +
				", writer=" + rs.getString("WRITER") +
				", price=" + rs.getInt("PRICE") + "</p>");
		}
		else {
			out.println("<p>상품 검색 결과가 없습니다.</p>");
		}
		
		// 6. disconnect from DB
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
%>
</body>
</html>
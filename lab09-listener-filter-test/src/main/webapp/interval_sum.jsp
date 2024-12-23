<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	long intervalSum(int first, int second){
		long sum = 0;
		if (first <= second){
			for (int i=first; i<=second; i++){
				sum += i;
			}
		}
		return sum;
}
%>
<%
	String firstStr = request.getParameter("first");
	String secondStr = request.getParameter("second");

	int firstNum = 1;
	if(firstStr != null && !firstStr.isBlank()){
		firstNum = Integer.parseInt(firstStr);
	}
	
	int secondNum = 100;
	if(secondStr != null && !secondStr.isBlank()){
		secondNum = Integer.parseInt(secondStr);
	}
	
	long sum = intervalSum(firstNum, secondNum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구간 합 구하기</title>
</head>
<body>
	<h1>구간 합 구하기</h1><hr>

	<p><%= firstNum %>에서 <%= secondNum %>까지 구간 합 : <%= sum %></p>
</body>
</html>
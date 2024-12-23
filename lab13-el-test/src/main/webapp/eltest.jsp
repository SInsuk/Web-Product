<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="my_math_library" prefix="my" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL Test</h1>
	<% int resultValue = (int)request.getAttribute("result"); %>
	
	<p>1에서 100까지의 구간 합 = <%= resultValue %></p>
	<p>1에서 100까지의 구간 합 = ${result}</p>
	
	<p>${user_info.name}님의 나이는 ${user_info["age"]}입니다.</p>
	<p>${user_info.name}님의 성별은 ${user_info["gender"] = 'M' ? "남성" : "여성"}입니다.</p>
	
	<p>${uum1} + ${num2} = ${num1 + num2}</p>
	<p>${uum1} - ${num2} = ${num1 - num2}</p>
	<p>${num1}과 ${num2} 중에 큰 값은 ${num1 - num2 >= 0 ? num1 : num2}</p>
</body>
</html>

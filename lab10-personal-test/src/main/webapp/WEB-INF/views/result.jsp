<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@page import="com.wplab.personalitytest.service.TestResultDO"%>
<%
	String username = (String)request.getParameter("username");
	TestResultDO resultDO = (TestResultDO)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재미있는 2분 성격 테스트</title>
<style>
body {
	width: 600px;
	margin-top: 50px;
	margin-left: 50px;
}
header {
	height: 100px;
}
.question {
	padding-top: 25px;
}
.buttons {
	padding-top: 50px;
	text-align: right;
}
</style>
</head>
<body>
	<header>
		<h1>재미있는 2분 성격 테스트</h1>
		<div style="text-align: right;">
			<p>반갑습니다, <%= username %> 님!
		</div>
		<hr>
	</header>
	<article>
	<div>
		<h2>성격 분석 결과 :</h2>
	</div>
	<div>
		<p><b>1. 분석 점수</b> <%= resultDO.getScore() %> </p>
		<p><b>2. 분석 결과</b> </p>
		<div><%= resultDO.getResult() %></div>
	</div>
		<div class="buttons">
			<input type="button" value="시작 페이지 가기" onclick="window.location.href='start.html';"/>
		</div>
	</article>
</body>
</html>
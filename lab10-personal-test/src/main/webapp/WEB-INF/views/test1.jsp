<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%
	String username = (String)request.getParameter("username");
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
		<form action="test2" method="post">
		<div class="question">
 			<p>1. 하루중 제일 기분이 좋을 때는?</p>
			<div>
 				<input type="radio" name="q1" value="2" required>a. 아침<br>
 				<input type="radio" name="q1" value="4">b. 오후나 이른 저녁<br>
 				<input type="radio" name="q1" value="6">c. 늦은 밤<br> 		
			</div>
		</div>
		<div class="question">
 			<p>2. 나는 걸을 때, 보통</p>
			<div>
 				<input type="radio" name="q2" value="6" required>a. 보폴을 넓게, 빨리 걷는다.<br>
 				<input type="radio" name="q2" value="4">b. 보폭을 좁게, 빨리 걷는다.<br>
 				<input type="radio" name="q2" value="7">c. 머리를 들고, 세상을 정면으로 바라보며 덜 빠르게 걷는다.<br> 		
 				<input type="radio" name="q2" value="2">d. 바닥을 보며 덜 빠르게 걷는다.<br> 		
 				<input type="radio" name="q2" value="1">e. 아주 느리게 걷는다.<br> 		
			</div>
		</div>
		<div class="buttons">
			<input type="submit" value="다음 >>" />
		</div>
		</form>
	</article>
</body>
</html>
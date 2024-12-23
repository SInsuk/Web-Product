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
		<form action="result" method="post">
		<div class="question">
 			<p>9. 잠자리에 들어서 잠들기 바로 직전에 나는</p>
			<div>
 				<input type="radio" name="q9" value="7" required>a. 몸을 똑바로 펴고 누운 포즈이다<br>
 				<input type="radio" name="q9" value="6">b. 엎드린 채로 몸을 죽 편 포즈이다<br>
 				<input type="radio" name="q9" value="4">c. 약간 몸을 둥그린 채로 옆으로 누운포즈이다<br> 
 				<input type="radio" name="q9" value="2">d. 한팔을 베고 있다<br> 
 				<input type="radio" name="q9" value="1">e. 머리를 이불 밑에 넣고있다<br> 		
			</div>
		</div>
		<div class="question">
 			<p> 10. 나는 이런 꿈을 자주 꾼다</p>
			<div>
 				<input type="radio" name="q10" value="4" required>a. 싸우거나 애쓰는꿈<br>
 				<input type="radio" name="q10" value="2">b. 무엇이나 누군가를 찾는꿈<br>
 				<input type="radio" name="q10" value="3">c. 날아오르거나 떠오르는 꿈<br> 		
 				<input type="radio" name="q10" value="5">d. 꿈은 잘 꾸지 않는다<br> 		
 				<input type="radio" name="q10" value="1">e. 항상 좋은 느낌의 꿈이다<br>		
			</div>
		</div>
		<div class="buttons">
			<input type="button" value="<< 이전" onclick="window.history.back()" />
			<input type="submit" value="다음 >>" />
		</div>
		</form>
	</article>
</body>
</html>
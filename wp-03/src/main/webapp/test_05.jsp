<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영어 레벨 테스트</title>
</head>
<body>
	<%
		HttpSession sess = request.getSession();
    	String name = (String) session.getAttribute("name");
    	if (name == null) {
        	response.sendRedirect("start.html");
        	return;
    	}
	%>
	<h1 style="text-align: center;">영어 레벨 테스트</h1>
    <p align="right"> 반갑습니다. <%= name %>!...</p><hr><br>
    <p>5. 다음 빈 칸에 들어갈 말로 알맞은 것은?</p>
    <form action="next.do" method="post">
    <input type="hidden" name="currentQuestion" value="5">
        The first bus __ to the bus stop at 5 a.m every morning<br><br>
        <input type="radio" name="answer" value="1">① come<br>
        <input type="radio" name="answer" value="2">② comes<br>
        <input type="radio" name="answer" value="3">③ came<br>
        <input type="radio" name="answer" value="4">④ will come<br><br>
        <input type="submit" value="다음">
    </form>
    <footer>소속: 컴퓨터소프트웨어공학부	학번: 202095049	이름: 송인석</footer>
</body>
</html>
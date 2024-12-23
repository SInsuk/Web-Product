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
    <p>1. 다음 빈 칸에 들어갈 말로 알맞은 것은?</p>
    <form action="next.do" method="post">
    <input type="hidden" name="currentQuestion" value="1">
        Thomas __ out two hours ago.<br><br>
        <input type="radio" name="answer" value="1">① go<br>
        <input type="radio" name="answer" value="2">② goes<br>
        <input type="radio" name="answer" value="3">③ goed<br>
        <input type="radio" name="answer" value="4">④ went<br><br>
        <input type="submit" value="다음">
    </form>
    <footer>소속: 컴퓨터소프트웨어공학부	학번: 202095049	이름: 송인석</footer>
</body>
</html>
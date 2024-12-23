<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>예약 확인</title>
</head>
<body>
    <h1>예약 확인</h1>
    <form action="confirm" method="post">
        예약 번호: <input type="text" name="rsv_seq" required><br>
        비밀번호: <input type="password" name="passwd" required><br>
        <input type="submit" value="확인하기">
    </form>
	<a href="main.html">홈으로</a>
</body>
</html>
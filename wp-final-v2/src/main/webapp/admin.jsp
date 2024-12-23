<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 메인</title>
</head>
<body>
    <h1>관리자 메인</h1>
    <form action="admin" method="get">
        좌석 종류 선택: 
        <select name="seatType">
            <option value="R">R석</option>
            <option value="A">A석</option>
            <option value="B">B석</option>
        </select>
        <input type="submit" value="조회">
    </form>
    <a type="button" href="main.html">홈으로</a>
</body>
</html>
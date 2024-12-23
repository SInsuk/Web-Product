<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.wplab.service.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>좌석 예약</title>
</head>
<body>
    <h1>좌석 예약</h1>
    <form action="reserve" method="post">
        이름: <input type="text" name="name" required><br>
        비밀번호: <input type="password" name="passwd" required><br>
        전화번호: <input type="text" name="phone" required><br>
        좌석 번호: 
        <select name="seat_number" required>
            <c:forEach var="seat" items="${seats}">
                <c:if test="${seat.rsv_seq == null}">
                    <option value="${seat.seat_number}">${seat.seat_number} - ${seat.seat_type}</option>
                </c:if>
            </c:forEach>
        </select><br>
        <input type="submit" value="예약하기">
    </form>
    <form action="main.html" method="get">
        <input type="submit" value="메인 화면으로 돌아가기">
    </form>
</body>
</html>
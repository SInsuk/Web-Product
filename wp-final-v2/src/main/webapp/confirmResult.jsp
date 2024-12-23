<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>예약 확인 결과</title>
</head>
<body>
    <h1>예약 확인 결과</h1>
    <c:choose>
        <c:when test="${not empty reservation}">
            <p>예약 번호: ${reservation.rsv_seq}</p>
            <p>이름: ${reservation.name}</p>
            <p>전화번호: ${reservation.phone}</p>
            <p>좌석 번호: ${reservation.seat_number}</p>
            <p>예약 날짜: ${reservation.rsv_date}</p>
            <form action="cancel" method="post">
                <input type="hidden" name="rsv_seq" value="${reservation.rsv_seq}">
                <input type="hidden" name="passwd" value="${reservation.passwd}">
                <input type="submit" value="예약 취소">
            </form>
        </c:when>
        <c:otherwise>
            <p>예약 정보를 찾을 수 없습니다. 예약 번호와 비밀번호를 확인하세요.</p>
        </c:otherwise>
    </c:choose>
    <form action="main.html" method="get">
        <input type="submit" value="메인 화면으로 돌아가기">
    </form>
</body>
</html>
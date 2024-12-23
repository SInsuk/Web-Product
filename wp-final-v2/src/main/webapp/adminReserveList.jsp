<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.wplab.service.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>좌석 예약 목록</title>
</head>
<body>
    <h1>좌석 예약 목록</h1>
    <c:choose>
        <c:when test="${not empty reservations}">
            <table border="1">
                <tr>
                    <th>예약 번호</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>좌석 번호</th>
                    <th>예약 날짜</th>
                    <th>작업</th>
                </tr>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.rsv_seq}</td>
                        <td>${reservation.name}</td>
                        <td>${reservation.phone}</td>
                        <td>${reservation.seat_number}</td>
                        <td>${reservation.rsv_date}</td>
                        <td>
                            <form action="adminDelete" method="post" style="display:inline;">
                                <input type="hidden" name="rsv_seq" value="${reservation.rsv_seq}">
                                <input type="hidden" name="seat_number" value="${reservation.seat_number}">
                                <input type="hidden" name="seatType" value="${param.seatType}">
                                <input type="submit" value="삭제">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>선택한 좌석 종류에 대한 예약 정보가 없습니다.</p>
        </c:otherwise>
    </c:choose>
    <form action="admin.jsp" method="get">
        <input type="submit" value="뒤로가기">
    </form>
    <form action="main.html" method="get">
        <input type="submit" value="메인 화면으로 돌아가기">
    </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>영어 레벨 테스트 결과</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 50%;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        .button-container {
            margin-top: 20px;
        }
    </style>
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
    <h1>영어 레벨 테스트 결과</h1>
    <p align="right"> 반갑습니다. <%= name %>!...</p><hr><br>
    <table>
        <tr>
            <th>문항</th>
            <th>응답</th>
            <th>정답</th>
            <th>결과</th>
        </tr>
        <tr>
            <td>1</td>
            <td>${sessionScope.answer1}</td>
            <td>4</td>
            <td>${sessionScope.answer1 == '4' ? "맞음" : "틀림"}</td>
        </tr>
        <tr>
            <td>2</td>
            <td>${sessionScope.answer2}</td>
            <td>2</td>
            <td>${sessionScope.answer2 == '2' ? "맞음" : "틀림"}</td>
        </tr>
        <tr>
            <td>3</td>
            <td>${sessionScope.answer3}</td>
            <td>1</td>
            <td>${sessionScope.answer3 == '1' ? "맞음" : "틀림"}</td>
        </tr>
        <tr>
            <td>4</td>
            <td>${sessionScope.answer4}</td>
            <td>3</td>
            <td>${sessionScope.answer4 == '3' ? "맞음" : "틀림"}</td>
        </tr>
        <tr>
            <td>5</td>
            <td>${sessionScope.answer5}</td>
            <td>2</td>
            <td>${sessionScope.answer5 == '2' ? "맞음" : "틀림"}</td>
        </tr>
    </table>
    <div class="button-container">
        <form action="start.html" method="get">
            <input type="submit" value="다시하기"><hr>
        </form>
    </div>
    
	<footer>소속: 컴퓨터소프트웨어공학부	학번: 202095049	이름: 송인석</footer>

</body>
</html>
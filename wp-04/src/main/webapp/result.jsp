<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = (String) request.getAttribute("name");
    int totalScore = (Integer) request.getAttribute("totalScore");
    String result = (String) request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>성격 테스트 결과</title>
    <style>
        body {
            width: 600px;
            margin-top: 50px;
            margin-left: 50px;
        }
        header {
            height: 100px;
        }
        .result {
            padding-top: 25px;
        }
    </style>
</head>
<body>
    <header>
        <h1>성격 테스트 결과</h1>
        <div style="text-align: right;">
            <p>반갑습니다, <%= name %> 님!</p>
        </div>
        <hr>
    </header>
    <article>
        <div class="result">
            <p>총점: <%= totalScore %>점</p>
            <p><%= result %></p>
        </div>
        <div class="buttons" style="text-align: right;">
            <form action="delete" method="POST">
                <input type="submit" value="결과 삭제" />
            </form>
            <form action="start.html" method="GET">
                <input type="submit" value="메인 화면" />
            </form>
        </div>
    </article>
</body>
</html>

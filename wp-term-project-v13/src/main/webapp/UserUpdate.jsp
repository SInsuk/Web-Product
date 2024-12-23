<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.wplab.service.*"%>
<%
    UserDO user = (UserDO)request.getAttribute("user");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f2f5;
            margin: 0;
        }
        .update-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .update-box h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .update-box input {
            width: 80%;
            padding: 10px;
            margin: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .update-box button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .update-box button:hover {
            background-color: #218838;
        }
        .update-box a {
            display: block;
            margin: 10px 0;
            color: #007bff;
            text-decoration: none;
        }
        .update-box a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <form action='userUpdate' method='POST'>
        <div class="update-box">
            <h2>회원 정보 수정</h2>
            <% if (errorMessage != null) { %>
                <div class="error-message"><%= errorMessage %></div>
            <% } %>
            <label for="user_id">ID</label>
            <input type="text" id="user_id" name="user_id" value="<%= user.getUser_id() %>" readonly />
            <label for="password">PASSWORD</label>
            <input type="text" id="password" name="password" value="<%= user.getPassword() %>" required />
            <label for="email">e-mail</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required />
            <button type="submit">정보 수정</button>
            <a href='userList'>뒤로 가기</a>
        </div>
    </form>
</body>
</html>



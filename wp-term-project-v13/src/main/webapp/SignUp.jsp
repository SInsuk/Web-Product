<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f0f2f5;
        margin: 0;
        font-family: Arial, sans-serif;
    }
    .register-box {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        box-sizing: border-box;
    }
    .register-box h2 {
        margin-bottom: 20px;
        font-size: 24px;
        text-align: center;
    }
    .register-box input, .register-box button {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        font-size: 16px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .register-box input:focus {
        border-color: #007bff;
    }
    .register-box button {
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .register-box button:hover {
        background-color: #218838;
    }
    .register-box a {
        display: block;
        text-align: center;
        margin-top: 10px;
        color: #007bff;
        text-decoration: none;
    }
    .register-box a:hover {
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
    <div class="register-box">
        <h2>회원가입</h2>
        <form action="UserRegister" method="post">
            <input type="text" name="user_id" placeholder="ID를 입력하세요" required />
            <input type="password" name="password" placeholder="비밀번호를 입력하세요" required />
            <input type="email" name="email" placeholder="이메일을 입력하세요" required />
            <button type="submit">회원가입</button>
            <a href="${pageContext.request.contextPath}/Login.jsp">로그인</a>
        </form>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
    </div>
</body>
</html>







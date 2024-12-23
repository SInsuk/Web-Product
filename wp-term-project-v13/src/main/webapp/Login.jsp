<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f0f2f5;
        margin: 0;
    }
    .login-box {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }
    .login-box h2 {
        margin-bottom: 20px;
        font-size: 24px;
    }
    .login-box input {
        width: 80%;
        padding: 10px;
        margin: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .login-box button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        border: none;
        color: white;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }
    .login-box button:hover {
        background-color: #0056b3;
    }
    .login-box a {
        display: block;
        margin: 10px 0;
        color: #007bff;
        text-decoration: none;
    }
    .login-box a:hover {
        text-decoration: underline;
    }
    .error-message {
        color: red;
        font-size: 14px;
        margin-top: 10px;
    }
</style>
</head>
<body>
    <form id="Login" action="userLogin" method="post">
        <div class="login-box">
            <h2>로그인</h2>
            <input type="text" name="user_id" placeholder="ID를 입력하세요" required />
            <input type="password" name="password" placeholder="비밀번호를 입력하세요" required />
            <button type="submit">로그인</button>
            <a href="SignUp.jsp">회원가입</a>
            <% if (request.getParameter("error") != null) { %>
                <div class="error-message">로그인 실패: 아이디 또는 비밀번호를 확인하세요.</div>
            <% } %>
        </div>
    </form>
</body>
</html>



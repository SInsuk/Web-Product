<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.wplab.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
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
    .admin-box {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 80%;
        text-align: center;
    }
    .admin-box h2 {
        margin-bottom: 20px;
        font-size: 24px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #ccc;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f0f0f0;
    }
    .admin-box a, .admin-box button {
        padding: 5px 10px;
        background-color: #007bff;
        border: none;
        color: white;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        margin-right: 5px;
        text-decoration: none;
    }
    .admin-box a:hover, .admin-box button:hover {
        background-color: #0056b3;
    }
    .button-group {
        display: flex;
    }
    .back-button {
        margin-top: 20px;
    }
</style>
<script>
    function confirmAndDelete(user_id) {
        if (confirm('"' + user_id + '" 회원을 정말로 삭제하시겠습니까?')) {
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "userDelete");

            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", "user_id");
            hiddenField.setAttribute("value", user_id);

            form.appendChild(hiddenField);
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>
</head>
<body>
<%
    List<UserDO> userList = (List<UserDO>) request.getAttribute("user_list");
    if (userList != null) {
%>
    <div class="admin-box">
        <h2>회원 관리</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>비밀번호</th>
                <th>이메일</th>
                <th>관리</th>
            </tr>
            <tbody>
<%
        for (UserDO user : userList) {
%>
                <tr>
                    <td><%= user.getUser_id() %></td>
                    <td><%= user.getPassword() %></td>
                    <td><%= user.getEmail() %></td>
                    <td>
                        <div class="button-group">
                            <a href="userUpdate?user_id=<%= user.getUser_id() %>">수정</a>
                            <a href="#" onclick='confirmAndDelete("<%= user.getUser_id() %>");'>삭제</a>
                        </div>
                    </td>
                </tr>
<%
        }
%>
            </tbody>
        </table>
        <div class="back-button">
            <a href="postList">뒤로 가기</a>
        </div>
    </div>
<%
    } else {
%>
    <p>관리할 회원이 존재하지 않습니다.</p>
<%
    }
%>
</body>
</html>



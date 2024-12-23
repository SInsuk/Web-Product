<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wplab.service.*"%>
<%
    PostDO post = (PostDO) request.getAttribute("post");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
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
        .edit-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 900px;
        }
        .edit-box h2 {
            margin-bottom: 20px;
            font-size: 24px;
            text-align: center;
        }
        .edit-box form {
            display: flex;
            flex-direction: column;
        }
        .edit-box input, .edit-box textarea, .edit-box button {
            padding: 10px;
            margin: 5px 0;
            font-size: 16px;
        }
        .edit-box input[type="file"] {
            padding: 5px;
        }
        .edit-box button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .edit-box button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="edit-box">
        <h2>게시글 수정</h2>
        <form action="postUpdate" method="post" enctype="multipart/form-data">
            <input type="hidden" name="post_id" value="<%= post.getPost_id() %>" />
            <input type="text" name="title" placeholder="제목을 입력하세요" value="<%= post.getTitle() %>" required />
            <textarea name="content" placeholder="내용을 입력하세요" rows="10" required><%= post.getContent() %></textarea>
            <input type="file" name="file" multiple />
            <input type="file" name="file" multiple />
            <input type="file" name="file" multiple />
            <button type="submit">수정 완료</button>
            <button type="button" onclick="history.back();">이전 화면으로</button>
        </form>
    </div>
</body>
</html>

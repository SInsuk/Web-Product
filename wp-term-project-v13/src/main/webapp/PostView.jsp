<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.wplab.service.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@page import="jakarta.servlet.http.HttpSession"%>

<%
    PostDO post = (PostDO) request.getAttribute("post");
    List<AttachmentDO> attachments = (List<AttachmentDO>) request.getAttribute("attachments");
    String userId = (String) session.getAttribute("user_id");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 보기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .post-container {
            width: 80%;
            margin: 0 auto;
        }
        .post-header {
            background-color: #f2f2f2;
            padding: 10px;
            border: 1px solid #ddd;
        }
        .post-content {
            padding: 20px;
            border: 1px solid #ddd;
            border-top: none;
        }
        .attachments {
            margin-top: 20px;
        }
        .back-button, .edit-button, .delete-button {
            margin-top: 20px;
            padding: 5px 10px;
            background-color: #007bff;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .back-button:hover, .edit-button:hover, .delete-button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function confirmAndDelete(postId) {
            if (confirm("정말로 삭제하시겠습니까?")) {
                location.href = 'postDelete?post_id=' + postId;
            }
        }
    </script>
</head>
<body>
    <div class="post-container">
        <div class="post-header">
            <h2><%= post.getTitle() %></h2>
            <p>작성자: <%= post.getUser_id() %></p>
        </div>
        <div class="post-content">
            <p><%= post.getContent() %></p>
        </div>
        <div class="attachments">
            <h3>첨부파일:</h3>
            <ul>
                <% for (AttachmentDO attachment : attachments) { %>
                <li><a href="download?file=<%= URLEncoder.encode(attachment.getFileName(), "UTF-8") %>" download><%= attachment.getFileName() %></a></li>
                <% } %>
            </ul>
        </div>
        <div>
            <a class="back-button" href="postList">목록으로 돌아가기</a>
            <% if (userId != null && (userId.equals(post.getUser_id()) || userId.equals("admin"))) { %>
                <a class="edit-button" href="postUpdate?post_id=<%= post.getPost_id() %>">수정</a>
                <button class="delete-button" onclick="confirmAndDelete(<%= post.getPost_id() %>)">삭제</button>
            <% } %>
        </div>
    </div>
</body>
</html>






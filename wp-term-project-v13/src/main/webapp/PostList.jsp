<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.wplab.service.*"%>
<%@page import="jakarta.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
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
        width: 100%;
        text-align: center;
        overflow-y: auto; /* 스크롤바를 추가하여 콘텐츠가 넘칠 때 스크롤 */
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
        white-space: nowrap;
        text-decoration: none;
    }
    .admin-box a:hover, .admin-box button:hover {
        background-color: #0056b3;
    }
    .button-group {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
    }
    .search-bar {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }
    .search-bar input[type="text"] {
        padding: 5px;
        font-size: 14px;
    }
    .search-bar input[type="submit"], .search-bar a {
        padding: 5px 10px;
        background-color: #007bff;
        border: none;
        color: white;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        margin-left: 5px;
        text-decoration: none;
    }
    .search-bar input[type="submit"]:hover, .search-bar a:hover {
        background-color: #0056b3;
    }
    .logout-button {
        margin-top: 20px;
        background-color: #dc3545;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }
    .logout-button:hover {
        background-color: #c82333;
    }
    .title {
        display: inline-block;
        max-width: 150px; /* 적절한 너비로 설정 */
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .pagination {
        list-style: none;
        padding: 0;
        display: flex;
        justify-content: center;
    }
    .pagination li {
        margin: 0 5px;
    }
    .pagination a {
        display: block;
        padding: 8px 16px;
        text-decoration: none;
        background-color: #007bff;
        color: white;
        border-radius: 5px;
    }
    .pagination a.active {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<%
    HttpSession httpSession = request.getSession();
    String userId = (String) httpSession.getAttribute("user_id");
    List<PostDO> postList = (List<PostDO>)request.getAttribute("post_list");
    int currentPage = (int) request.getAttribute("current_page");
    int totalPages = (int) request.getAttribute("total_pages");
%>
<div class="admin-box">
    <h2>게시글 관리</h2>
    <div class="button-group">
        <% if (!"admin".equals(userId)) { %>
        <a href="postCreate">게시글 작성</a>
        <% } %>
        <% if ("admin".equals(userId)) { %>
        <a href="userList">회원 관리</a>
        <% } %>
    </div>
    <div class="search-bar">
        <form action="postList" method="get">
            <input type="text" name="keyword" placeholder="검색어를 입력하세요">
            <input type="submit" value="검색">
        </form>
        <a href="postList">전체 보기</a>
    </div>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th></th>
        </tr>
        <tbody>
        <% if (postList != null) { 
            int rowNum = 1; // 번호를 1부터 시작
            for (PostDO post : postList) { %>
            <tr>
                <td><%= rowNum++ %></td> <!-- 1부터 시작하는 순차적인 번호 -->
                <td><span class="title" title="<%= post.getTitle() %>"><%= post.getTitle().length() > 100 ? post.getTitle().substring(0, 100) + "..." : post.getTitle() %></span></td>
                <td><%= post.getUser_id() %></td>
                <td><a href="postView?post_id=<%= post.getPost_id() %>">게시글 보기</a></td>
            </tr>
        <% } 
        } else { %>
            <tr>
                <td colspan="4">게시글 목록이 존재하지 않습니다.</td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <ul class="pagination">
        <% for (int i = 1; i <= totalPages; i++) { %>
            <li><a href="postList?page=<%= i %>" class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a></li>
        <% } %>
    </ul>
    <button class="logout-button" onclick="location.href='logout'">로그아웃</button>
</div>
</body>
</html>


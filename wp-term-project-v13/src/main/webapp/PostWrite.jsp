<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f2f5;
            margin: 0;
        }
        .post-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            text-align: center;
        }
        .post-box h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .post-box input, .post-box textarea {
            width: 80%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .post-box button {
            width: 45%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .post-box button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form action="postCreate" method="post" enctype="multipart/form-data">
        <div class="post-box">
            <h2>게시글 작성</h2>
            <input type="text" name="title" placeholder="제목" required>
            <textarea name="content" placeholder="내용" required></textarea>
            <input type="file" name="file" multiple>
            <input type="file" name="file" multiple>
            <input type="file" name="file" multiple>
            <div>
                <button type="submit">작성</button>
                <button type="button" onclick="window.history.back()">취소</button>
            </div>
        </div>
    </form>
</body>
</html>


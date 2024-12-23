<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 - 상품 등록</title>
<style>
	body {
		text-align: center;
	}
	table {
		border-collapse: collapse;
	}
	td {
		padding: 5px;
	}
</style>
</head>
<body>
	<h1>상품 관리</h1>
	<h3>상품 등록</h3><hr>
	<br><br>
	<form action='register' method='POST'>
		<table align="center">
			<tbody>
				<tr><td>코 드 :</td><td><input type="text" name="code" required /></td></tr> 
				<tr><td>제 목 :</td><td><input type="text" name="title" required /></td></tr> 
				<tr><td>저 자 :</td><td><input type="text" name="writer" required /></td></tr> 
				<tr><td>가 격 :</td><td><input type="number" name="price" required /></td></tr> 
			</tbody>
		</table>
		<p><input type="button" value="돌아가기" onclick="window.history.go(-1);" />
		   <input type="submit" value="상품 등록" /></p>
	</form>
</body>
</html>
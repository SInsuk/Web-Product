<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wplab.scoreprocessing.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 처리 결과</title>
</head>
	<body style='padding-left; 100px'>
		<h1>성적 처리 결과</h1><hr>
		<div style='font-size: 1.2rem;'>1. 현재 입력 성적: <br>
		int[] scores = scoreDO.getScores();
		for(int i=0; i<scores.length; i++) {
			out.printf("&nbsp;&nbsp&nbsp;#%d : %d<br>\n", (i+1), scores[i]);
		}
		</div>
	</body>
</html>
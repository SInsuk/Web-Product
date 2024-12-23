<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String[] menuList = {"비빔밥", "설렁탕", "짜장면"};
	request.setAttribute("menu_List", menuList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Tags Test</title>
</head>
<body>
	<h1>JSTL Tags Test</h1><hr>
	<c:set var="userProfile" value="GildongHong" scope="session" />
	<c:set var="num1" value="100" />
	<c:set var="num2" value="155" />
	
	<p>${num1} + ${num2} = ${num1+num2}</p>
	<p>사용자 프로필 : ${userProfile}</p>
	
	<c:remove var="userProfile" scope="session" />
	
	<p><c:out value="'<b></b>' 태그는 글씨체를 두껍게 출력합니다." /></p>
	<p>&lt;b&gt;&lt;/b&gt;태그는 글씨체를 두껍게 출력합니다.</p>


	<p>${num1}과 ${num2} 중에 큰 값은
		<c:choose>
			<c:when test="${num1 - num2 >= 0}">${num1}"</c:when>
			<c:otherwise>${num2}</c:otherwise>
		</c:choose>
		<c:if test="${num1 - num2 >= 0}">${num1}</c:if>
		<c:if test="${num1 - num2 < 0}">${num2}</c:if>
	 </p>		
	 
	 <h3></h3>
	 <c:forEach var="size" begin="1" end="5"> 
	 	<font size="${size}">야 호 ~~~<br></font>
	 </c:forEach>
	 
	 <h3>오늘의 점심 메뉴: </h3>
	 <ul>
	 	<c:forEach var="idx" begin="0" end="2"> 
	 	<li>${menu_List[idx]}</li>
	 	</c:forEach>
	 </ul>
	 
	 <ul>
	 	<c:forEach var="menu" items="${menu_List}" varStatus="status"> 
	 	<li>${status.count}: ${menu}</li>
	 	</c:forEach>
	 </ui>

</body>
</html>
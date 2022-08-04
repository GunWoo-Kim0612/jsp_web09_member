<%@page import="com.sun.xml.internal.bind.v2.runtime.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원탈퇴</h2>
<form action="withdrwal.do" method="get">
	<input type="text" name="userid" value="${loginUser.userid }">
</form>

<% 
	response.sendRedirect("../withdrwal.do");
%>
</body>
</html>
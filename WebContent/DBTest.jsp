<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.web09.dao.MemberDAO" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   MemberDAO memDao = MemberDAO.getInstance();
   Connection conn = memDao.getConnection();
   out.println("DBCP 연결 성공");
%>
</body>
</html>
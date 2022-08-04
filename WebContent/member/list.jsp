<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.beans.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.web09.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "SELECT * FROM MEMBER";
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<h2>회원 목록</h2>
	<table border="1" id="members">
		<tr>
			<td>name</td>
			<td>id</td>
			<td>password</td>
			<td>email</td>
			<td>phone</td>
			<td>grade</td>
			<td align="center">수정/삭제</td>
		</tr>
		<%
		
			try {
				MemberDAO mDao = MemberDAO.getInstance();
				conn = mDao.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					out.println("<tr>");
					out.println("<td>" + rs.getString("NAME") + "</td>");
					out.println("<td>" + rs.getString("USERID") + "</td>");
					out.println("<td>" + rs.getString("PWD") + "</td>");
					out.println("<td>" + rs.getString("EMAIL") + "</td>");
					out.println("<td>" + rs.getString("PHONE") + "</td>");
					out.println("<td align='center'>" + rs.getString("ADMIN") + "</td>");
					out.println("<td><input type='button' value='수정' onclick='check_sel()'> <input type='button' value='삭제' onclick='check_sel()'></<td>");
					
					out.println("</tr>");

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		%>
	</table>
</body>
</html>
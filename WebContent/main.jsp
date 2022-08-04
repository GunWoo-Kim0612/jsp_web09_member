<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty loginUser }">
	<jsp:forward page="login.do"></jsp:forward>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="script/member.js"></script>
<body>


	<c:if test="${loginUser.admin == 1 }">
		<h2>관리자 전용 페이지</h2>
		<form action="logout.do">
					<!-- 세션을 통해 가져온 정보 -->
					<!-- 속성이름  : loginUser,  값 : mVo객체.. -->
					<!-- 세션에 저장된 mVo.name, mVo.userid 불러온거 ㅇㅇ -->
					안녕하세요. ${loginUser.name}(${loginUser.userid }) 님
			<table>
				<tr>
					<td><input type="button" value="회원목록" onclick="location.href='memberList.do?userid=${loginUser.userid}'"></td>
					<td><input type="button" value="회원정보 변경" onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'"></td>
					<td><input type="submit"value="로그아웃"> </td>
				</tr>
			</table>
		</form>
	</c:if>


	<c:if test="${loginUser.admin == 0 }">

		<h2>회원 전용 페이지</h2>
		<form action="logout.do">
			<table>
				<tr>
					<!-- 세션을 통해 가져온 정보 -->
					<!-- 속성이름  : loginUser,  값 : mVo객체.. -->
					<!-- 세션에 저장된 mVo.name, mVo.userid 불러온거 ㅇㅇ -->
					<td>안녕하세요. ${loginUser.name}(${loginUser.userid }) 님</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="로그아웃"> &nbsp;&nbsp; <input type="button"
						value="회원정보 변경"
						onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'">
						&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>
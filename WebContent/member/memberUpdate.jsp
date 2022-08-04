<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="script/member.js"></script>
<body>
	<h2>회원 수정</h2>
	<form action="memberUpdate.do" method="post" name="frm">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20"
					value="${ mVo.name}" readonly="readonly"> <!-- 영역객체에 저장한정보사용 -->
				</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${ mVo.userid }"
					readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" value="${ mVo.pwd }">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" size="20"
					value="${mVo.email }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="tel" name="phone" size="20"
					value="${mVo.phone }"></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${mVo.admin == 1 }">
						<td>등급</td>
						<td><input type="radio" name="admin" value="0"
							checked="checked"> 일반회원 <input type="radio" name="admin"
							value=""> 관리자</td>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="admin" value="0" checked="checked"> 일반회원
					</c:otherwise>
				</c:choose>
			</tr>


			<tr>
				<td colspan="2" align="right"></td>
			</tr>
			<tr>
				<!-- 영역객체에서 가져오는 message -->
				<td colspan="2">${ message }</td>
			</tr>
		</table>
		<input type="submit" value="확인" onclick="return joinCheck()">
		<input type="reset" value="취소">
		<input type="button" value="탈퇴"
			onclick="location.href='withdrwal.do'">
	</form>

</body>
</html>
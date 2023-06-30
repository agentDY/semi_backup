<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.UserVO.*" %>
<%@ page import="action.UserLoginAction.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 메인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style>
	#userInfoArea{
		width : 400px;
		margin : auto;
		border : 1px solid gray;
	}
	table{
		width : 380px;
		margin : auto;
		text-align: center;
	}
</style>
</head>
<body>

<!-- <section id = "userInfoArea"> -->
<form name="loginmain">
<table>

	<tr>
		<td>${id}</td>
		<td>님 반갑습니다.</td>
	</tr><br>
	<tr>
		<td>대출도서: </td> <!-- 대출도서 페이지연결 수정 -->
		<td></td> 
	</tr>

	<tr>
		<td colspan=2>
			<a href="MyPageAction.lo">마이페이지</a> <!-- 마이페이지연결 수정 -->
<!-- 			<a href="userLogin.lo">로그아웃</a> -->
			<a href="user/loginForm.jsp">로그아웃</a>
		</td>
	</tr>
</table>
<!-- </section> -->
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		@import url("/css/user_myP_upD_del.css");
	</style>

</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
	<%@ include file="../header.jsp" %>

<%if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>
<script>
location.href='/user/userLogin.lo';
</script>
<%}else{ %>
	<form action="/user/userDelete.lo" method="post">
	
		<div class="userDel">
			<br><br>
			<h3 style="color: #474747; margin-bottom: 30px; text-align: left;">회원탈퇴</h3>
				<div class="spanDiv">
					<span>탈퇴하시면 본인의 회원정보가 영구적으로 삭제되며, 본인이 게시한 게시물 정보 및 대출이력 정보도 확인할 수 없게 됩니다.</span>
				</div>
				<table style="border-collapse: inherit;">
					<tr>
						<th>비밀번호 입력</th>
						<td>
							<span class="info">*회원탈퇴시 필요한 본인확인을 위해 비밀번호를 입력해주세요.</span><br>
							<input type="password" name="delPW" id="delPW" class="insertPass">
						</td>
					</tr>
				</table>
			<div class="subtn">
				<button type="submit" style="border-radius: 5px; padding: 2px 7px;">회원탈퇴</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn_hover" style="border-radius: 3px; margin:auto;" type="button" value="돌아가기" onClick="javascript:history.go(-1)">돌아가기</button>
			</div>
		</div>
	</form>
	<%} %>
	</section>
</body>
<%@include file="../footer.jsp"%>
</html>
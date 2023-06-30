<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.RentInfoVO, vo.UserVO" %>
<%@ include file="../header.jsp" %>
    <%
    ArrayList<RentInfoVO> rentInfo = (ArrayList<RentInfoVO>)request.getAttribute("rent");
//     ArrayList<UserVO> userVo = (ArrayList<UserVO>)request.getAttribute("userList");
    %>
    
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<title>Insert title here</title>
<style>
	@import url("/css/user_myP_upD_del.css");	
</style>


</head>
<body>

<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
<%if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>
<!-- <script>alert('로그인이 필요합니다'); -->
<!-- location.href='index.jsp';</script> -->
<%}else{ %>
	<form action="/user/updateForm.lo" method="post">
	<br><br>
		
		<div>
			<div class="user">
				<div class="welcome">
					<span style="font-weight: bolder;">${name }</span> 님 안녕하세요.
					<button type="button" class="btn" onclick="location.href='/user/deleteForm.jsp'">회원탈퇴</button>
					<button type="submit" class="btn">정보수정</button>
				</div>
				<div id="user_info">
					<table style="border-collapse: inherit;  text-align: center;">
						<tr>
							<th>아이디</th>
							<th>전화번호</th>
							<th>이메일</th> 
						</tr>
						<tr>
							<td>${id }</td>
							<td>${tel }</td>
							<td>${email }</td> 
						</tr>
					</table>
				</div>
			</div>
			
			<div id="rentInfo">
				<h3 style="text-align: left; color: #474747;">대출현황</h3>
					<table style="border-collapse: inherit; text-align: center;">
					
						<tr>
							<th>대출번호</th>
							<th>책 번호</th>
							<th>책 제목</th>
							<th>저자</th>
							<th>대출일자</th>
							<th>반납기한</th>
							<th>반납여부</th>
						</tr>
					<%
						for(RentInfoVO rent : rentInfo) {
					%>
						<tr>
							<td><%= rent.getRENT_ID_SEQ() %></td>
							<td><%= rent.getRENT_BOOK() %></td>
							<td><%= rent.getISBN_TITLE() %></td>
							<td><%= rent.getISBN_AUTHOR() %></td>
							<td><%= rent.getRENT_DATE() %></td>
							<td><%= rent.getRENT_RETURN() %></td>
<%-- 							<td><%= rent.getRENT_RETURN_DATE() %></td> --%>
							<td><% if(rent.getRENT_RETURN_DATE() == null) %> 미반납 <% else { %> 반납완료 <%} %></td>
						</tr>
					
					<% 
						}
					%>
					</table>
			</div>
			
			
		</div>
	</form>
<%} %>
</section>
</body>
	<%@ include file="../footer.jsp" %>
</html>
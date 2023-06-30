<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>

<%
	String notice_num=(String)request.getAttribute("notice_num");
  	int noticeNum = Integer.parseInt(notice_num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<style>


	
	@import url("/css/user_myP_upD_del.css");
	
		.chkbtn:hover,chkbtn:focus{
		background-color:#037F8C;
	}

</style>
</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">

<form name="modifyAdminForm" action="/notice/noticeModifyForm.lo?notice_num=<%=noticeNum%>" method="post">

		<div class="userDel">
			<br><br>
			<h3 style="color: #474747; margin-bottom: 30px; text-align: left;">관리자 비밀번호 확인</h3>
				<div class="spanDiv">
				</div>
				<table style="border-collapse: inherit;">
					<tr>
						<th>비밀번호 입력</th>
						<td>
							<span class="info">* 관리자 확인용 비밀번호를 입력해주세요.</span><br>
							<input type="password" name="MODIFY_CONFIRM" id="delPW" class="insertPass">
						</td>
					</tr>
				</table>
			<div class="subtn">
				<button type="submit" class="chkbtn" style="border-radius: 5px; padding: 2px 7px; width: 80px;">확인</button>
				&nbsp;&nbsp;
				<button type = "button" class="chkbtn" style="border-radius: 5px; padding: 2px 7px; " onClick ="javascript:history.go(-1)">돌아가기</button>
			</div>
		</div>
	</form>
</section>

</body>
<%@ include file="../footer.jsp" %>
</html>
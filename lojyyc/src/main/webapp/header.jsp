<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/main.css">

<title>lojyyc library</title>

<script>
$(document).ready(function() {
	$("#login").click(function() { 
		$.ajax({
			url : "/user/userLoginAction.lo",
			method : "POST",
			data : {
				USER_ID : $("#USER_ID").val(),
				USER_PW : $("#USER_PW").val()
			},
			success : function(response) {
				if (response.username === "admin"){
					$("#loginTop").hide();
					$("#signupTop").hide();
					$("#loginTable").hide();
					$("#logout").show();
					$("#loginSuccess").show();
// 					$("#admin_login").show();
					$("li#welcome").text("안녕하세요, " + response.username + "님");
					$("h3#welcome").text("안녕하세요, " + response.username + "님");
//					$("#welcome_btn_admin").show();
					console.log(response.username);
					location.reload();

				} else if (response.username != null) {
						$("#loginTop").hide();
						$("#signupTop").hide();
						$("#loginTable").hide();
						$("#logout").show();
						$("#loginSuccess").show();
						$("li#welcome").text("안녕하세요, " + response.username + "님");
						$("h3#welcome").text("안녕하세요, " + response.username + "님");
//							$("#welcome_btn_users").show();
						console.log(response.username);
						location.reload();
				} 
				else {
					console.log(response.username);
					alert("로그인 실패");
					console.log(response.username);
					location.reload();
				}
			},

			error : function() {
				alert("회원정보를 확인해주세요.");
				console.log("Error: " + error);
			}
		});
	});
	
	  $("#logout, .logout").click(function(){
	    $.ajax({
	      url: "/user/userLogoutAction.lo",
	      success: function() {
	    	  location.href = "/index.jsp";
	      }
	    });
	  });
	
	
});
</script>
	</head>
	<body>
	
	<!---------------------------------- 상단로고, 로그인, 회원가입, 사이트맵 ---------------------------------->
	<header>
		<nav class="navbar navbar-expand-sm navbar-light bg-light">
			<div class="container-fluid">
				<a id="logo" href="/index.jsp">L<img src="../topimgs/cubelogo2.png" style="padding-bottom:7px; width:25px; height: 35px;">JYYC Library</a>

				<ul class="navbar-nav">
					<!-- Button trigger modal -->
				<li id="welcome"></li>
				<% System.out.println("session id : " + (String)session.getAttribute("USER_ID"));
					if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>
						<li class="nav-item" id="loginTop" ><a class="nav-item" data-toggle="modal"	data-target="#loginModal" style="text-decoration:none;">&nbsp;&nbsp;로그인&nbsp;&nbsp;</a></li>
						<li id="signupTop"><a class="nav-item" href="/user/userJoin.lo" style="text-decoration:none;">&nbsp;&nbsp;회원가입&nbsp;&nbsp;</a></li>
							
					<%} else {%>
					
						<% if ((String)session.getAttribute("USER_ID") != null && (String)session.getAttribute("username") != null){
							out.print("안녕하세요, " + (String)session.getAttribute("username") + "님 &nbsp;&nbsp;&nbsp;");
							}%>
						<li class="nav-item" id="loginTop" style="display:none" ><a class="nav-item" data-toggle="modal" data-target="#loginModal">로그인</a></li>
						<li id="logout"><a class="nav-item" style="text-decoration:none;">&nbsp;&nbsp;로그아웃&nbsp;&nbsp;</a></li>
						
					<%} %>
					<li id="logout" style="display:none"><a class="nav-item" style="text-decoration:none;">&nbsp;&nbsp;로그아웃&nbsp;&nbsp;</a></li>
					<li class="nav-item"><a class="nav-item" href="/siteMap.jsp" style="text-decoration:none;">&nbsp;&nbsp;사이트맵</a></li>
				
				</ul>
			</div>
		</nav>
	
	
	<!-------------------------------------------------- 메인메뉴 -------------------------------------------------->
	<nav class="navbar navbar-expand-lg">
		<div id="top2">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar"></button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a id="mainmenu" class="nav-link" href="/notice/noticeList.lo">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공지사항&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<li class="nav-item"><a id="mainmenu" class="nav-link" href="/book/bookSearch.lo">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도서검색&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					
					
					<%if (String.valueOf(session.getAttribute("username")).equals("null")){%>
					<%} else{%>
					<li class="nav-item"><a id="mainmenu" class="nav-link" href="/user/MyPageAction.lo">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;마이페이지&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<%} %>
					
					
					<%if (String.valueOf(session.getAttribute("username")).equals("admin")){%>
					<li class="nav-item"><a id="mainmenu" class="nav-link" href="/book/bookWriteForm.lo">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도서등록&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					
					<li class="nav-item"><a id="mainmenu" class="nav-link" href="/user/userList.lo">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;회원관리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
					<% } %> 
				</ul>
			</div>
		</div>
	</nav>

		</header>
		
		<!------------------------------------ Modal ------------------------------------>
				<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
						
							<div class="modal-header">
								<h3 class="modal-title" id="exampleModalCenterTitle" style="color:#037F8C; text-align:center;">&nbsp;&nbsp;로그인</h3>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<br>
							<div class="modal-body">
							<table class="loginModal">
									<tr>
										<td style="text-align: right;"><label for="USER_ID">아&nbsp;&nbsp;이&nbsp;&nbsp;디</label></td>
									<td colspan="2">
										<td style="text-align: left;"><input type="text" name="USER_ID" id="USER_ID" /></td>
									</tr>
									<tr>
										<td style="text-align: right;"><label for="USER_PW">비밀번호 </label></td>
									<td colspan="2">
										<td style="text-align: left;"><input type="password" name="USER_PW" id="USER_PW" /></td>
									</tr>
								</table>
							</div>
							<br>
							<div class="modal-footer">
								<a type="button" class="btn" id="login" data-dismiss="modal" style="color:#037F8C; ">로그인</a>&nbsp;&nbsp; 
								<a type="button" class="btn" href="/user/userJoin.lo" style="color:#037F8C;">회원가입</a>
							</div>
						</div>
					</div>
				</div>
	</body>
</html>
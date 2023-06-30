<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.ISBNVO, vo.PageInfo, vo.NoticeVO, svc.NoticeListService" %>
<%
	NoticeListService noticeListService = new NoticeListService();
	noticeListService.getNoticeListForMain(1,5);
	
	ArrayList<NoticeVO> noticeList = (ArrayList<NoticeVO>) noticeListService.getNoticeListForMain(1,5);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">

<title>LOJYYC LIBRARY</title>
<script>
$(document).ready(function(){
	$.ajax({
		url: "/user/callProc.lo",
		success : function(response) {
			console.log('프로시저 실행완료');
		},
		errer : function(){
			console.log('프로시저 실행오류');
		}
	});
});


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
						console.log(response.username);
						location.reload();

					} else if (response.username != null) {
							$("#loginTop").hide();
							$("#signupTop").hide();
							$("#loginTable").hide();
							$("#logout").show();
							$("#loginSuccess").show();
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
});
$(document).ready(function() {
	  $("#logout, .logout").click(function(){
	    $.ajax({
	      url: "/user/userLogoutAction.lo",
	      success: function() {
	        location.reload();
	      }
	    });
	  });
	});
	
</script>


</head>

<body>
 <section id="body_css">
	<header>
<!---------------------------------로고 ---------------------------------->
		<nav class="navbar navbar-expand-sm navbar-light bg-light">
			<div class="container-fluid">
				<a id="logo" href="/index.jsp">L<img src="topimgs/cubelogo2.png" style="padding-bottom:7px; width:25px; height: 35px;">JYYC Library</a>
<!-- 				<a id="logo" href="/index.jsp"><img src="topimgs/cubelogo.png" style="padding-bottom:5px; width:30px; height: 35px;">LOJYYC Library</a> -->
<!-- 				<a id="logo" href="/index.jsp">LOJYYC Library<img src="topimgs/cubelogo.png" style="padding-bottom:5px; width:30px; height: 35px;"></a> -->
				<ul class="navbar-nav">
<!---------------------------------상단바 ---------------------------------->
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
<!--------------------- -----메인 메뉴바	 ------------------------------------>
	<nav class="navbar navbar-expand-sm">
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
	
<!-- ----------------------Modal------------------------------- -->
	
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
										<td style="text-align: center;"><input type="text" name="USER_ID" id="USER_ID" /></td>
									</tr>
									<tr>
										<td style="text-align: right;"><label for="USER_PW">비밀번호 </label></td>
									<td colspan="2">
										<td style="text-align: center;"><input type="password" name="USER_PW" id="USER_PW" /></td>
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
	
<!---------------------- 메인이미지 ------------------ -->
	<img src="topimgs/mainimg.png" style="width:100%; height:50%; margin-bottom: 0;">
	
	<div class="container">
	
<!----------------------- 검색창 ----------------------->
	<div class="SearchTab text-center">

		<form action="/book/bookSearch.lo" method="post">
			<br>
			<div class="row" style="position: relative; height:50px;">
				<img src="imgs/bookicon.png" style="width:40px; height:40px; position: absolute; margin: 5px 0 0 10px;">
				<input class="col-7" type="text" placeholder="키워드를 입력해주세요" name="searchKeyword" 
				style="border: 0; border-bottom: 2px solid #ddd; margin:0 15px; width: 100%; background-color: transparent; padding-left: 50px;"> 
				<select style="border:2px solid #ddd; border-radius: 3px; color: #037e8c; margin:0 5px;" class="keyword_type col-2" name="keyword_type">
					<option value="key_title">제목</option>
					<option value="key_author">저자</option>
					<option value="key_company">출판사</option>
				</select> 
				<input class="form-control col-2" type="submit" value="검색" style="margin:0 15px; height:50px;">
			</div>
		</form>
	</div>
	<br>
	<br>
	
<!-- 	<!----------------------- 공지사항 ----------------------->
	<div class="container" style="margin-top: 30px; border-spacing : 2px;">
		<div class="row">
			<div class="col-sm-7">
				<h3>공지사항</h3>
				
				<table class="noticeTable" style="margin-bottom : 20px;">
			<tr class="tr_line">
				<td >제목</td>
				<td >날짜</td>
			</tr>
	
		
		<%for(int i=0;i<noticeList.size();i++){	%>
			<tr id="tr_notices">
			
				<td class="data">
					<a href="/notice/noticeDetail.lo?notice_num=<%=noticeList.get(i).getNOTICE_NUM()%>&page=1" style="color: black;">
					<%=noticeList.get(i).getNOTICE_TITLE()%> 
				</a>
				</td>
				<td class="data"> 
				<%=noticeList.get(i).getNOTICE_DATE() %>
				</td>
				
			</tr>
			<%} %>
		</table>
	
			</div>
			<div class="col-sm-1">
			
			</div>
		
		<div class="col-sm-4">
				<section id="loginformArea">
					<%
					System.out.println("session id 2 : " + (String) session.getAttribute("USER_ID"));
					if ((String) session.getAttribute("USER_ID") == null && (String) session.getAttribute("username") == null) {
 					%>
					<h3>회원 로그인</h3>
					<table id="loginTable" class="loginModal" style="width:100%;">
						<tr class="tr_line">
							<td>
								<a type="button" style="margin-top:20px;" class="btn nav-item" data-toggle="modal" data-target="#loginModal">로그인</a>&nbsp;&nbsp;
								<a class="btn" style="margin-top:20px;" href="/user/userJoin.lo">회원가입</a>
							</td>
						</tr>
						<tr><td><hr></td></tr>
						<tr>	
							<td>
								<div class="carousel slide text-center" data-ride="carousel">
									<div class="carousel-inner">
										<div class="carousel-item active">
											<p class="d-block w-100" alt="First slide">
												<b>신간도서</b><br>
											<img class="d-inline w-28 carouselImg"  src="imgs/history01.jpg" alt="First slide">
											<img class="d-inline w-28 carouselImg"  src="imgs/history02.jpg" alt="First slide">
											</p>
										</div>
										<div class="carousel-item">
											<p class="d-block w-100" alt="Second slide">
												<b>베스트셀러</b><br>
												<img class="d-inline w-30 carouselImg"  src="imgs/최소한의선의.jpg" alt="Second slide">
												<img class="d-inline w-30 carouselImg"  src="imgs/문학5.jpg" alt="Second slide">
											</p>
										</div>
										<div class="carousel-item">
											<p class="d-block w-100" alt="Third slide">
												<b>오늘의 도서</b><br>
													<img class="d-inline w-30 carouselImg"  src="imgs/문학4.jpg" alt="Third slide">					
													<img class="d-inline w-30 carouselImg"  src="imgs/그림은 사랑이다.jpg" alt="Third slide">					
											</p>
										</div>
									</div>
								</div>
							</td>
							<td></td>
						</tr>
					</table>
					<%
					}
 					%>
					<%
					System.out.println("session id 3 : " + (String) session.getAttribute("USER_ID"));
					if ((String) session.getAttribute("USER_ID") != null && (String) session.getAttribute("username") != null) {
					%>
					<h3 style="color: #037e8c;"><%out.print("안녕하세요, " + (String) session.getAttribute("username") + "님 ");%></h3>
					<table id="loginTable" class="loginModal" style="width:100%;">
						<tr class="tr_line">
							<td>
<!-- 								<a type="button" class="btn nav-item text-left" onclick="location.href='user/MyPageAction.lo'">마이페이지</a>			 -->
							<br>
							</td>
						</tr>
						<tr>	
							<td>
								<div class="carousel slide text-center" data-ride="carousel">
									<div class="carousel-inner">
										<div class="carousel-item active">
											<p class="d-block w-100" alt="First slide">
												<b>신간도서</b><br>
											<img class="d-inline w-28 carouselImg"  src="imgs/history01.jpg" alt="First slide">
											<img class="d-inline w-28 carouselImg"  src="imgs/history02.jpg" alt="First slide">
											</p>
										</div>
										<div class="carousel-item">
											<p class="d-block w-100" alt="Second slide">
												<b>베스트셀러</b><br>
												<img class="d-inline w-30 carouselImg"  src="imgs/최소한의선의.jpg" alt="Second slide">
												<img class="d-inline w-30 carouselImg"  src="imgs/문학5.jpg" alt="Second slide">
											</p>
										</div>
										<div class="carousel-item">
											<p class="d-block w-100" alt="Third slide">
												<b>오늘의 도서</b><br>
													<img class="d-inline w-30 carouselImg"  src="imgs/문학4.jpg" alt="Third slide">					
													<img class="d-inline w-30 carouselImg"  src="imgs/그림은 사랑이다.jpg" alt="Third slide">					
											</p>
										</div>
									</div>
								</div>
							</td>
							<td></td>
						</tr>
					</table>
					
					
					<%
					}
 					%>
				</section>
			</div>
		</div>
	</div>
			
		<h3>도서관 이벤트</h3>	
			<hr style="background-color: #03A6A6; height: 3px;">
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
				  <ol class="carousel-indicators">
				    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				  </ol>
				  <div class="carousel-inner">
				    <div class="carousel-item active">
								<img class="d-block h-50 w-100"  src="imgs/event1.jpg" alt="First slide">
				    </div>
				    <div class="carousel-item">
								<img class="d-block h-50 w-100"  src="imgs/event2.jpg" alt="Second slide">
				    </div>
				    <div class="carousel-item">
								<img class="d-block h-50 w-100"  src="imgs/event3.jpg" alt="Third slide">
				    </div>
				  </div>
				  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
		</div>

<br>
		<h3>오시는길</h3>
					<table id="loginTable" class="loginModal" style="width:100%;">
						<tr class="tr_line"><td><br></td></tr>
						<tr>
						<td>
							<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3169.55648185546!2d126.91906191565967!3d37.40031977982937!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b60d1b8dde3f9%3A0xddc103e417d54cdc!2z7ZWY7J2066-465SU7Ja07Lu07ZOo7YSw7ZWZ7JuQIOyViOyWkey6oO2NvOyKpA!5e0!3m2!1sen!2skr!4v1680510633074!5m2!1sen!2skr" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
						</td>
						<td>
							<h4>지하철</h4>안양역 1번출구 도보 5분거리 지하상가 13번 출구 바로 앞<br><br>
							<h4>버스</h4>안양1번가 정류장 도보 2분거리 관창빌딩 3층<br><br>
							<h4>주차</h4>인근 공영주차장 이용 (*유료)
						</td>
						</tr>
					</table>
		
<br><br>
</div>
</section>
</body>
	<%@ include file="./footer.jsp" %>
</html>

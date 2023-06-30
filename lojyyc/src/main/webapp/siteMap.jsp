<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>siteMap</title>
<!-- 	<meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.js"></script> -->
<!-- 	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script> -->
	
<style>
	body {margin:0; padding:0;}
	
	div#siteMap {width:80%; margin:0 auto; text-align:center;}
	#siteMap h3 { margin:20px 0; text-align: center; color: #474747;}

	table#siteMapTable {width:100%; border-collapse:separate; margin:0 auto; border-spacing:5px;}
/* 	tr#top { background-color:rgba(4, 191, 191, 0.5); border-top:2px solid #ddd;} */
	#top th {
	/* 	border-collapse: separate; */
		padding: 10px 0;
		border-radius: 5px;
	  	border-spacing: 0 10px;  
	 	background: #F5F5F5; 
		text-align: center;
		border-color: #ddd;
	 	border-width: 1px; 
		border-right: none;
	 	border-left-style: solid; 
		border-top: solid 3px #03A6A6;
	 	font-weight: bold;
	 	color:#474747;
	}
	
/* 	tr#list { border:none; font-size:14px; text-decoration : none; color: #474747;} */
	tr#list { border:none; font-size:14px; text-decoration : none; color: #474747; font-weight: bold; text-align:center;}
/* 	tr#list a :hover { color: #04BFBF; } */
	
	td { padding: 5px 0; border:none; color: #474747;}
/* 	tr#top { background-color:rgba(4, 191, 191, 0.3); border-top:2px solid #04BFBF; border-bottom:1px solid #04bfbf;} */
/* 	#pageList {margin:auto; margin-top:15px; width:500px; text-align:center;} */
	
	.sitemap_a:hover{
		color:#037F8C;
		text-decoration-line:none;
	}
	
	.sitemap_a{
		text-decoration: none; color:black; 
	}
</style>

</head>
<body>

	<section id="body_css">
	<img style="height: 163px;"src="topimgs/mainimg1.png" id="topimgs">
	
	<div id="siteMap">
	<br><br>
		<h3>SITE MAP</h3>
		<br><br>

		<table id="siteMapTable">
		
			<tr id="top">
				<th>회원정보</th>
				<th>이용안내</th>
				<th>마이페이지</th>
				<th>도서정보</th>
			</tr>
				
				
			   <tr id="list">
				<td>
				<%if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>
				<a class="sitemap_a" data-toggle="modal" data-target="#loginModal">로그인</a>
				<%}else{%>
				<a class="sitemap_a" href="/index.jsp" onclick="alert('이미 로그인 상태입니다.');">로그인</a>
				<%} %>
				</td>
				<td><a class="sitemap_a" href="/notice/noticeList.lo">공지사항</a></td>
				<td><a class="sitemap_a" href="/user/MyPageAction.lo">나의정보</a></td>
				<td><a class="sitemap_a" href="/book/bookSearch.lo">도서검색</a></td>
			</tr>
				
			<tr id="list">
				<td><a class="sitemap_a" href="/user/userJoin.lo"
				<%if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>>회원가입</a><%}
				else{ %>onclick="alert('이미 로그인 상태입니다.');">회원가입</a><%} %></td>
				<td></td>
				<td><a class="sitemap_a" href="/user/updateForm.lo">정보수정</a></td>
				<td><a class="sitemap_a" href="/book/bookSearch.lo">카테고리검색</a></td>
			</tr>
			<tr id="list">
				<td></td>
				<td></td>
				<td><a class="sitemap_a" href="/user/deleteForm.jsp">회원탈퇴</a></td>
				<td><a class="sitemap_a" href="/book/bookSearch.lo">도서대출</a></td>
			</tr>
				
			<tr id="list">
				<td></td>
				<td></td>
				<td><a class="sitemap_a" href="/user/MyPageAction.lo">대출이력</a></td>
				<td><a class="sitemap_a" href="/book/bookSearch.lo">도서상세정보</a></td>
			</tr>
		</table>
	</div>
	<br><br><br><br><br><br><br><br>

	</section>

</body>
	<%@ include file="./footer.jsp" %>
</html>
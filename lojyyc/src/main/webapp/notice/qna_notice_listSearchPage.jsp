<%@page import="vo.PageInfo"%>
<%@page import="vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
	<%@ include file="../header.jsp" %>

<%
	ArrayList<NoticeVO> noticeList=(ArrayList<NoticeVO>)request.getAttribute("noticeList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int listCount = 1;
	int nowPage = 1;
	int maxPage = 1;
	int startPage = 1;
	int endPage = 1;
	
	if(pageInfo!=null){
		listCount = pageInfo.getListCount();
		nowPage = pageInfo.getPage();
		maxPage = pageInfo.getMaxPage();
		startPage = pageInfo.getStartPage();
		endPage = pageInfo.getEndPage();
	}
	
	String sessionId = (String) session.getAttribute("USER_ID");
	String searchWord = request.getParameter("searchWord");
	String searchField = request.getParameter("searchField");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>MVC 게시판</title>
<style type="text/css">

* {
	margin : 0;
	padding : 0;
}

#top1 {
	font-size: 35px;
	color: #037F8C;
}

#top {
	font-size: 20px;
}

.navbar {
	background-color: #03A6A6;
}

#top2 {
	margin: 0 auto;
}

.nav-link {
	color: #fff;
	font-size: 20px;
}

#topimgs {
	width: 100%;
	height: 50%;
	margin-bottom: 0;
}

.container-fluid {
	margin : 0 auto;
}

h3 {
	text-align: center;
	vertical-align : middle;
	height : 80px;
	padding-top : 70px;
}


.table_top{
	margin : 13px auto;
	width : 70%;
	text-align : right;	
}

table {
	margin : 0 auto;
	width : 70%
}

.titulos {
	background-color : #F0F0F0;
	border-top : 3px solid #03A6A6;
	border-radius : 5px;
	border-spacing : 0 10px;
	text-align : center;
	height : auto;
	table-layout : fixed;
	height: 48px;
}

#tr_title {
	width : 80%;
	margin : 0 auto;
}

.data {
	height: 48px;
	border-bottom: 1px solid lightgray;
}

#pageList {
	margin: auto;
	width: 13%;
	text-align: center;
	margin-top : 15px;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}

.header {
	height: 150px;
	margin: 0 auto;
}

.tr_notices{
	border-bottom: 1px solid black;
}

.table_top button{
	width : 80px;
	height: 40px;
	background-color : #03A6A6;
	border-style: none;
	color : white;
	font-size : 16px;
	border-radius: 3px;
}

.table_top button:hover,.table_top button:focus{
	background-color:#037F8C;
}

.searchbox{
	margin : 20px auto;
	width : 960px;
	height : 100px;
	text-align: center;
}

#searchButton {
	background-color:#03A6A6; 
	color:white; 
	border-radius:3px; 
	width:70px; 
	height:30px; 
	border:none;
}

#searchButton:hover,#searchButton:focus{
	background-color:#037F8C;
}

.searchSelect {
	height: 30px;
	border: 1px solid black;
	border-radius: 3px;
	margin-top : 30px;
}

#searchSelect {
	width: 70px;
}

#searchBox {
	width: 250px;
	margin : 0 40px;
}



@media screen and (max-width: 768px) {		/*==============반응형==================*/
    .table {
        margin: 0 auto;
        width: 850px;
    }
    
    .container-fluid {
    margin: 0 auto;
    }
    
    table {
	margin : 0 auto;
	height: 550px;
	width : 100%
	
}

.titulos {
	background-color : #F0F0F0;
	border-top : 3px solid #03A6A6;
	border-radius : 5px;
	border-spacing : 0 10px;
	text-align : center;
	height : 20px;
}


#tr_title {
	width : 100%;
	margin : 0 auto;
}
 
 
 .searchbox{
	margin : 0 auto;
	width : 100%;
	height : 50px;
	text-align: center;
}

#searchButton {
	background-color:#03A6A6; 
	color:white; 
	border-radius:3px; 
	width:15%; 
	height:30px; 
	border:none;
	margin: 8px 13px;
}

.searchSelect {
	height: 30px;
	border: 1px solid black;
	border-radius: 3px;
}
    
#searchSelect {
	width: 15%;
}

#searchBox {
	width: 40%;
	margin : 20px 40px;
	height : 80px;
	padding-top : 30px;
}

form input {
	margin : 30px 0;
}

#pageList {
	margin: auto;
	width: 40%;
	text-align: center;
}

.table_top{
 	width: 100%;
}

.table_top button:hover,.table_top button:focus{
	background-color:#037F8C;
}

#searchButton:hover,#searchButton:focus{
	background-color:#037F8C;
}

.table_top button{
	width : 12%;
	height: 40px;
	background-color : #03A6A6;
	border-style: none;
	color : white;
	font-size : 16px;
	text-align : center;
	margin-right: 15px;
	border-radius: 3px;
}

</style>
</head>

<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
	<!-- 게시판 리스트 -->
	<div class="header">
		<h3>공지사항</h3>
	</div>


	<% if(sessionId == null){ %>
	<% } else if(sessionId.equals("admin")) { %>
	<div class=table_top>
		<button type="button" onclick="location.href='/notice/noticeWriteAdmin.lo'";>등록</button>
	</div>
		
	<% } else{}%>
	
	
	<div id="listForm">
			<%
			if(noticeList != null && listCount > 0){
			%>
		<table style="border-collapse: inherit; text-align: center;">
			<tr id="tr_title">
				<td class="titulos" >번호</td>
				<td class="titulos" >제목</td>
				<td class="titulos" >첨부파일</td>
				<td class="titulos" >날짜</td>
				<td class="titulos" >조회수</td>
			</tr>
			<%for(int i=0;i<noticeList.size();i++){	%>
			<tr class="tr_notices" >
			
				<td class="data" 
				<%if(noticeList.get(i).getNOTICE_PIN() ==1){ %>
				style="background-color:#F7F7F7; font-weight:bold; border-bottom: 1px solid lightgray; border-spacing : 0;">
				<%} else { %> > <%  } %>
				
				
				<%if(noticeList.get(i).getNOTICE_PIN() ==0){ %>
				<%=noticeList.get(i).getNOTICE_NUM()%> <% }else if(noticeList.get(i).getNOTICE_PIN() ==1){%>
				고정<%}else{%>▼<%} %>
				
				</td>

				<td class="data"
				<%if(noticeList.get(i).getNOTICE_PIN() ==1){ %>
				style="background-color:#F7F7F7; color:black; font-weight:bold; border-bottom: 1px solid lightgray;">
				<%} else { %> style=" color:black; border-bottom: 1px solid lightgray;">  <%  } %>
			
					<a href="/notice/noticeDetail.lo?notice_num=<%=noticeList.get(i).getNOTICE_NUM()%>&page=<%=nowPage%>" style="color: black;">
						<%=noticeList.get(i).getNOTICE_TITLE()%> 
				</a>
				</td>

				<td class="data" <%if(noticeList.get(i).getNOTICE_PIN() ==1){ %> 
				style="background-color:#F7F7F7; font-weight:bold; border-bottom: 1px solid lightgray; border-spacing : 0;">
				<%} else { %> style=" color:black; border-bottom: 1px solid lightgray;">  <%  } %>
				
				
				<%if(noticeList.get(i).getNOTICE_FILE()==null){}else{ %>
					<img src="clip.png" alt="file">
				<%}%></td>
				
				<td class="data"<%if(noticeList.get(i).getNOTICE_PIN() ==1){ %>
				style="background-color:#F7F7F7; font-weight:bold; border-bottom: 1px solid lightgray; border-spacing : 0;">
				<%} else { %> style=" color:black; border-bottom: 1px solid lightgray;">  <%  } %>
				
				<%=noticeList.get(i).getNOTICE_DATE() %></td>
				
				
				<td class="data" <%if(noticeList.get(i).getNOTICE_PIN() ==1){ %>
				style="background-color:#F7F7F7; font-weight:bold; border-bottom: 1px solid lightgray; border-spacing : 0;">
				<%} else { %> style=" color:black; border-bottom: 1px solid lightgray;">  <%  } %>
				<%=noticeList.get(i).getNOTICE_READCOUNT() %></td>
			</tr>
			<%} %>
		</table>
<!-- 	</section> -->

	<section id="pageList">
		<%if(nowPage<=1){ %>
		[이전]&nbsp;
		<%}else{ %>
		<a href="/notice/noticeSearchResult.lo?page=<%=nowPage-1%>&searchWord=<%=searchWord %>&searchField=<%=searchField %>" style="color: black;">[이전]</a>&nbsp;
		<%} %>

		<%
		for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
		[<%=a %>]
		<%}else{ %>
		<a href="/notice/noticeSearchResult.lo?page=<%=a%>&searchWord=<%=searchWord %>&searchField=<%=searchField %>" style="color: black;">[<%=a %>]
		</a>&nbsp;
		<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %>
		[다음]
		<%}else{ %>
		<a href="/notice/noticeSearchResult.lo?page=<%=nowPage+1%>&searchWord=<%=searchWord %>&searchField=<%=searchField %>" style="color: black;">[다음]</a>
		<%} %>
		
		<!-- --------- -->
		</section>
	</div>
	<%
    }else{
	%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
	}
	%>

	<div class="searchbox">
	
		<form action="/notice/noticeSearchResult.lo?page=<%=nowPage%>" method="post" >
		<select name="searchField" class="searchSelect" id="searchSelect">
		<option value="title">제목</option>
		<option value="content">내용</option>
		</select>
		<input style="padding-left:5px;" type="text" name="searchWord" class="searchSelect" id="searchBox" placeholder="검색어를 입력하세요." maxlength="40">
		<button type="submit" id="searchButton">검색</button>
		</form>
	</div>
</section>	
</body>
<%@ include file="../footer.jsp" %>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="action.SearchUserAction" %>
<%@ page import="vo.UserVO" %>
<%@ page import="vo.PageInfo"%>
<%@ page import="vo.RentInfoVO" %>
<%@ page import="java.util.*" %>
<%@ include file="../header.jsp" %>
<% 
	ArrayList<UserVO> userList = (ArrayList<UserVO>)request.getAttribute("userList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = 1;
	int nowPage = 1;
	int maxPage = 1;
	int startPage = 1;
	int endPage = 1;
	String search_value = request.getParameter("search_value");
	String radio_name = request.getParameter("user_type");
	
	if(pageInfo!=null){
		listCount = pageInfo.getListCount();
		nowPage = pageInfo.getPage();
		maxPage = pageInfo.getMaxPage();
		startPage = pageInfo.getStartPage();
		endPage = pageInfo.getEndPage();
	}
	
	ArrayList<RentInfoVO> rentInfo = (ArrayList<RentInfoVO>)request.getAttribute("bookRentInfo");
	
	if(radio_name==null){
		radio_name = request.getParameter("search_type");
	}else{
	}
%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<link rel="stylesheet" type="text/css" href="../css/useList.css">
<title>userList</title>
</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
	<!-- 회원검색창 -->	
	<div class="SearchTab text-center" id="userSearchBox">
	<h3>회원 조회</h3> <br>
		<form id="searchBox" action="/user/searchUser.lo?page=${page}" method="post">
			<br>
			<div class="row" style="height:50px;">
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioInline1" name="user_type" class="custom-control-input " value="id" checked>
					<label class="custom-control-label" for="customRadioInline1">아이디</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioInline2" name="user_type" class="custom-control-input" value="name">
					<label class="custom-control-label" for="customRadioInline2">이름</label>
				</div>
				
				<img src="../imgs/searchicon.png" style="width:37px; height:37px; position: absolute; margin: 5px 0 0 180px;">
				<input class="col-7" type="text" placeholder="키워드를 입력해주세요" name="search_value"  style="border: 0; border-bottom: 2px solid #ddd; margin:0 15px; width: 100%; background-color: transparent; padding-left: 50px;"> 
				<input class="form-control col-2" type="submit" value="검색" style="margin:0 15px; height:50px;">
			</div>
		</form>
	</div>
	<br>
	<!-- 회원리스트 -->
	<div id="user_listForm">
		<form action="/user/getUserRentInfo.lo" method="post">
		<table id="user_listTable">
		<% if(userList != null && listCount > 0){ %>
			<tbody><tr id="top">
				<th>아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>연락처</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>대출상태</th></tr>
			<% for(int i=0; i<userList.size();i++){ %>
			<tr id="listCont">
				<td> <%= userList.get(i).getUSER_ID() %></td>
				<td> <%= userList.get(i).getUSER_NAME() %></td>
				<td> <%= userList.get(i).getUSER_ADDR() %></td>
				<td> <%= userList.get(i).getUSER_TEL() %></td>
				<td> <%= userList.get(i).getUSER_EMAIL() %></td>
				<td> <%= userList.get(i).getUSER_DATE() %></td>
				<td> 
					
					<button type="submit" name="rentBookUserId" value="<%= userList.get(i).getUSER_ID() %>" style="background-color:#03A6A6; color:white; border-radius:3px; width:70px; height:40px; border:none;">대출현황 </button>
				</td>
			</tr>
			<% } %> 
			</tbody>
		</table>
		</form>
	</div>
	
	<div id="pageList">
		<%if(nowPage<=1){ %> [이전]&nbsp;
		<%}else{ %>
			<a href="searchUser.lo?page=<%=nowPage-1 %>&search_value=<%= search_value %>&user_type=<%=radio_name%>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
			if(a==nowPage){%> 
				[<%=a %>]
			<% } else{ %>
				<a href="searchUser.lo?page=<%=a %>&search_value=<%= search_value %>&user_type=<%=radio_name%>">[<%=a %>]
				</a>&nbsp;
			<% } %>
		<% } %>

		<%if(nowPage>=maxPage){ %> 
			[다음]
		<% } else{ %>
			<a href="searchUser.lo?page=<%=nowPage+1 %>&search_value=<%= search_value %>&user_type=<%=radio_name%>">[다음]</a>
		<% } %>
	</div>
 	<% 
    }
		else
	{
	%>
	<div id="emptyArea"><br><br><br>등록된 회원이 없습니다.<br><br><br></div>
	<%
	}
	%>
	</section>
</body>
</html>
	<%@ include file="../footer.jsp" %>
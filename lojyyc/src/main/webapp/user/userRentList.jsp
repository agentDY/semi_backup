<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.RentInfoVO, vo.RentVO, vo.UserVO, vo.PageInfo" %>
<%@ page import="java.util.*" %>
<%@ include file="../header.jsp" %>
<% 
	ArrayList<UserVO> userList = (ArrayList<UserVO>)request.getAttribute("userList");
	ArrayList<RentInfoVO> rentList = (ArrayList<RentInfoVO>)request.getAttribute("rent");
	
	String userId = (String)userList.get(0).getUSER_ID();
	
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
	
	if ( userId == null){
		userId = request.getParameter("id");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/useList.css">
</head>
<script>
$(document).ready(function(){
	$(".btn-book-return").click(function(){
		$.ajax({
			url: "/user/returnBook.lo",
			method: "post",
			data: {
				rent_id: $(this).data("rent_id"),
				u_id : $("#rentBookUserId").val(),
			},
			success : function(response){
				location.reload();
			},
			error : function(){
				location.reload();
			}
		});
	});
});
</script>	
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
<!-- 회원리스트 -->
	<h3 class="h3">회원대출목록조회</h3> <br>
	<div id="listForm">
		<form action="/user/returnBook.lo" method="post">
		<table id="user_listTable">
		<% if(userList != null && listCount > 0){ %>
<%-- 		<% if(userList != null){ %> --%>
			<tbody>
			<tr id="top">
				<th>아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>연락처</th>
				<th>이메일</th>
				<th>가입일</th>
			</tr>
			<% for(int i=0; i<userList.size();i++) { %>
			<tr id="listCont1">
				<td> <%= userList.get(i).getUSER_ID() %></td>
				<td> <%= userList.get(i).getUSER_NAME() %></td>
				<td> <%= userList.get(i).getUSER_ADDR() %></td>
				<td> <%= userList.get(i).getUSER_TEL() %></td>
				<td> <%= userList.get(i).getUSER_EMAIL() %></td>
				<td> <%= userList.get(i).getUSER_DATE() %></td>
				<input type="hidden" id="rentBookUserId" name="rentBookUserId" value=<%= userList.get(i).getUSER_ID() %>>
			</tr>
			<% } %> 
			</tbody>
		</table>
		<br>
		<table id="user_rentTable">
		<tbody>
				<tr id="none_top">
					<th>대출번호</th>
					<th>책 제목</th>
					<th>저자</th>
					<th>대출일자</th>
					<th>반납기일</th>
					<th>반납일자</th>
					<th>반납처리</th>
				</tr>
				<% 
				if (rentList != null) { 
						for(int i=0; i<rentList.size(); i++)  {
					
							String rent_book = rentList.get(i).getRENT_ID_SEQ();%>
							
							<tr id="none_bottom">
								<td><%= rentList.get(i).getRENT_ID_SEQ() %></td>
								<td><%= rentList.get(i).getISBN_TITLE() %></td>
								<td><%= rentList.get(i).getISBN_AUTHOR() %><br></td>
								<td><%= rentList.get(i).getRENT_DATE() %></td>
								<td><%= rentList.get(i).getRENT_RETURN() %></td>
								<td>
									<%if("null".equals(String.valueOf(rentList.get(i).getRENT_RETURN_DATE()))){%>
										미반납
									<%}else{%> 
										<%=rentList.get(i).getRENT_RETURN_DATE()%>
									<%}%>
								</td>
								<td>
									<button class="btn-book-return" data-rent_id="<%=rent_book%>">
									<% 
									System.out.println("도대체 뭐야" + rent_book);
									if(String.valueOf(rentList.get(i).getRENT_RETURN_DATE()).equals("null")){%>
										반납하기
									<% }else{ %> 
										반납완료 
									<% } %>
									</button>
								</td>
							</tr>
					<% } %>
				<% } %>
				</tbody>
			</table>
		</form>
		<br>
	</div>

	<div id="pageList">
		<%if(nowPage<=1){ %> [이전]&nbsp;
		<%}else{ %>
			<a href="getUserRentInfo.lo?page=<%=nowPage-1 %>&rentBookUserId=<%= userId %>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
			if(a==nowPage){%> [<%= a %>]
			<%}else{ %>
				<a href="getUserRentInfo.lo?page=<%=a %>&rentBookUserId=<%= userId %>">[<%=a %>]
				</a>&nbsp;
			<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %> &nbsp;[다음]
		<%}else{ %>
			<a href="getUserRentInfo.lo?page=<%=nowPage+1 %>&rentBookUserId=<%= userId %>">&nbsp;[다음]</a>
		<%} %>
	</div>
 	<% 
    }
	else
	{
	%>
	<div id="emptyArea">대출내역이 없습니다.</div>
	<%
	}
%>
</section>
</body>
	<%@ include file="../footer.jsp" %>
</html>
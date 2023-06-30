<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.ISBNVO, vo.PageInfo, vo.RentVO"%>
<%@ include file="/header.jsp" %>
<%String isbn = request.getParameter("isbn_book"); %> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/book.css">
<title>LOJYYC LIBRARY</title>

</head>

<body>
 <section id="body_css">
	<img style="height: 163px;"src="/topimgs/mainimg1.png" id="topimgs">
	<div class="SearchTab text-center">
	    <div class="SearchTab text-center">

		<form action="bookSearch.lo?" method="post">
			<br>
			<div class="row" style="position: relative; height:50px;">
				<img src="../imgs/bookicon.png" style="width:40px; height:40px; position: absolute; margin: 5px 0 0 10px;">
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
	    <form action="bookCategorySearch.lo" method="post">
	    <div class="row" style="border-collapse: inherit">
	         <button class="form-control col" type="submit" name="keyword" value="전체">전체목록</button>
	        <button class="form-control col" type="submit" name="keyword" value="철학">철학</button>
	        <button class="form-control col" type="submit" name="keyword" value="사회과학">사회과학</button>
	        <button class="form-control col" type="submit" name="keyword" value="예술">예술</button>
	        <button class="form-control col" type="submit" name="keyword" value="문학">문학</button>
	        <button class="form-control col" type="submit" name="keyword" value="역사">역사</button>
	        <button class="form-control col" type="submit" name="keyword" value="총류">총류</button>
	        <%if((String.valueOf(session.getAttribute("USER_ID")).equals("admin"))){ %><button class="form-control col" type="submit" name="keyword" value="미보유">미보유</button><%} %>
	   
	    </div>
	    </form>
	<br>
	
		<%
		List<ISBNVO> books = (List<ISBNVO>) request.getAttribute("bookIndex");
		if (books != null && !books.isEmpty()) {
	
			for (ISBNVO book : books) {
				session.setAttribute("bookName", book.getISBN_TITLE());
		%>
		
		<table class="tableInfo1" style="border-collapse: inherit">
			<tr>
				<td class="td_left">도서명</td>
				<td class="td_right" colspan="2"><%=book.getISBN_TITLE()%></td>
			</tr>
			<tr>
				<td class="td_left">ISBN</td>
				<td class="td_right" colspan="2"><%=book.getISBN_ID()%>
			</tr>
			<tr>
				<td class="td_left">도서 코드</td>
				<td class="td_right" colspan="2"><%=book.getISBN_BOOK()%>
			</tr>
			<tr>
				<td class="td_left">저자</td>
				<td class="td_right" colspan="2"><%=book.getISBN_AUTHOR()%></td>
			</tr>
			<tr>
				<td class="td_left">옮긴이</td>
				<td class="td_right" colspan="2"><%if(null==book.getISBN_TRANS()){%>-<%}else{%><%=book.getISBN_TRANS()%><%}%></td>
			</tr>
			<tr>
				<td class="td_left">발행년도</td>
				<td class="td_right" colspan="2"><%=book.getISBN_YEAR()%></td>
			</tr>
			<tr>
				<td class="td_left">출판사</td>
				<td class="td_right" colspan="2"><%=book.getISBN_COM()%></td>
			</tr>
			<tr>
				<td class="td_left">분류</td>
				<td class="td_right" colspan="2"><%=book.getISBN_CG()%></td>
			</tr>
			
			
			<!-- 	//**	여기서부터 아래주석까지 추가됨 -->
		<%if(String.valueOf(session.getAttribute("USER_ID")).equals("admin")){ %>
		<tr>
			<td class="td_left">보유현황</td>
			<td class="td_right" colspan="2"><%if("Y".equals(book.getISBN_DEL())){%>미보유<%}else{%>보유<%}%></td>
		</tr>
		<%} %>
<!-- 	여기까지 -->
			
			<tr>
				<td class="td_left">도서 소개</td>
				<td class="td_right text-left colspan-3"><%=book.getISBN_INFO()%></td>
				<td class="td_right"><img class="imgPosition"
					src="../imgs/<%=book.getISBN_IMG()%>"></td>
			</tr>
			<tr>
				<td class="td_left">대출가능여부</td>
				<td class="td_right" colspan="2"><%if("Y".equals(book.getISBN_STATUS())||"Y".equals(book.getISBN_DEL())){%>불가<%}else{%>가능<%}%></td>
			</tr>
		</table>
	
		<%
		}
		%>
		<%
		}
		%>
		<br>
		<hr>
	
	<%
	System.out.println("bookDetailPage user_id 값 : " + (String) session.getAttribute("USER_ID"));
	System.out.println("bookDetailPage username 값 : " +(String) session.getAttribute("username"));
	if (!(String.valueOf(session.getAttribute("USER_ID")).equals("null")) && !(String.valueOf(session.getAttribute("USER_ID")).equals(null))) {
	%>
			<form style="display: inline;" action="bookRent.lo" method="post">
				<button class="btn_hover" style="width: 150px" name="keyword" value="<%=request.getParameter("isbn_book")%>">도서대출</button>
			</form>
		<%
		 if(String.valueOf(session.getAttribute("USER_ID")).equals("admin")){
			System.out.println("어드민받아야됨 :"+(String) session.getAttribute("USER_ID"));
		%>
			<a style="display:hidden;" href="bookModifyForm.lo?isbn_book=<%=isbn%>"><button class="btn_hover" style="width: 150px" type="button" >수정하기</button></a>
			<a style="display:hidden;" href="bookDeleteForm.lo?isbn_book=<%=isbn%>"><button class="btn_hover" style="width: 150px" type="button" >삭제하기</button></a>
			<button class="btn_hover" style="width: 150px" type="button" onclick="javascript:history.go(-1)">뒤로가기</button>
		<% }
	}else{%>
		<h5>로그인 후 대출이용이 가능합니다</h5>
		<a class="btn nav-item" id="dlogin" data-toggle="modal" data-target="#loginModal">로그인</a>
		<a class="btn nav-item" id="dlogin" href="/user/userJoin.lo">회원가입</a>
	<%}%>
	
<!-- 	여기까지 -->
		<br>
		<a class="btn nav-item" id="dlogin" href="index.jsp">Home으로</a>
		<hr>
	</div>
</section>
</BODY>
	<%@ include file="/footer.jsp" %>
</HTML>

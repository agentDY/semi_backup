<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import = "vo.ISBNVO, vo.PageInfo, vo.RentVO" %>
<%@ include file="/header.jsp" %>
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
    <!----------------------- 검색창 ----------------------->
	<div class="SearchTab text-center">

		<form action="bookSearch.lo?" method="post">
			<br>
			<div class="row" style="position: relative; height:50px;">
				<img src="/imgs/bookicon.png" style="width:40px; height:40px; position: absolute; margin: 5px 0 0 10px;">
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
<br><br><br>
	<%
	List<RentVO> rent = (List<RentVO>) request.getAttribute("bookRentInfo");
// 	RentVO rentVO = rent.get(0).getString("ISBN_STATUS");
// 	String no = rent.get(0).getRENT_STATUS();
	
	if (rent != null) {
	
			for (RentVO bookRent : rent) {
		%>
				<h3 style="color: #037F8C">대출이 완료되었습니다!</h3>
				<br>
				<div>대출번호 : <%=bookRent.getRENT_ID_SEQ()%></div>
				<div>도서명 : <%=(String)session.getAttribute("bookName")%></div>
				<div>도서 코드 : <%=bookRent.getRENT_BOOK()%></div>
				<div>회원ID : <%=bookRent.getRENT_USER()%></div>
				<div>대출날짜 : <%=bookRent.getRENT_DATE()%></div>
				<div>반납(예정)날짜 : <%=bookRent.getRENT_RETURN()%></div>
				<br>
				<h5>방문해주셔서 감사합니다</h5>
			<%} %>
	<%} %>
		
	<% 
	try {	
	if ((rent.get(0).getRENT_STATUS())==null) { %>	
		<h2>대출이 불가능한 도서입니다</h2>
	<%} %>
	<% } catch (Exception e) {
		e.printStackTrace(); %>
		<h2>대출불가</h2>
		<br><br>
	<%} %>	
	
	<a class="rent_a" style="text-decoration: none; color:#848484;" href="/index.jsp">Home으로</a><br>
	<a class="rent_a" style="text-decoration: none; color:#848484;" href="javascript:history.go(-1)">뒤로가기</a>
	<hr>
</div>
</section>
</body>
<%@ include file="/footer.jsp" %>
</html>
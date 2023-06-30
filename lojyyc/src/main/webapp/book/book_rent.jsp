<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.RentVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출페이지</title>
</head>
<body>
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
				<div>도서ID : <%=bookRent.getRENT_BOOK()%></div>
				<div>회원ID : <%=bookRent.getRENT_USER()%></div>
				<div>대출날짜 : <%=bookRent.getRENT_DATE()%></div>
				<div>반납(예정)날짜 : <%=bookRent.getRENT_RETURN()%></div>
				<br>
				<h5>방문해주셔서 감사합니다</h5>
			<%} %>
	<%} %>
		
	<% if ((rent.get(0).getRENT_STATUS())==null) { %>	
		<h2>대출안댐ㅋ</h2>
	<% } %>	
	
	<h1>대출오나료</h1>
	
	<a href="index.jsp">Home으로</a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/header.jsp"%>	
<%
	String isbn = request.getParameter("isbn_book"); 
// 	ISBNVO isbn = (ISBNVO)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>lojyyc library - 도서 정보 삭제 (admin)</title>
<style>

	#passForm{
	text-align: center;
	padding-bottom :7%; 
/*  	padding-top: 20%;  */
	}
	
	h3 {
	padding-top:7%;
	text-align: center;
	color: #474747;
}

.sec_btn{
	background-color:#03A6A6; color:white;  width:90px; 
	height:40px; border: none;
	border-radius: 3px;
	text-align: center;
	margin:auto;
}

button:hover,button:focus{
border: 2px solid #037F8C;  
      background-color:#037F8C; 
}

</style>
</head>
<body>
<section id="body_css">
	<img src="/topimgs/mainimg1.png" id="topimgs">
<section id = "passForm">
<form name="deleteForm" action="bookDeletePro.lo?isbn_book=<%=isbn%>"  method="post">
<input type = "hidden" name = "page" value = "<%=nowPage %>"/>
<h3>정말 삭제하시겠습니까?</h3><br>
		<button class="sec_btn" type="submit">삭제하기</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="sec_btn" type="button" onClick="javascript:history.go(-1)">돌아가기</button>
</form>
</section>
</section>
</body>
	<%@include file="/footer.jsp"%>
</html>
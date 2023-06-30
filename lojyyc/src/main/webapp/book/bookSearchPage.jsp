<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.ISBNVO, vo.PageInfo" %>
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

		<form action="bookSearch.lo" class="bookSearch" method="post">
			<br>
			<div class="row" style="position: relative; height:50px;">
				<img src="../imgs/bookicon.png" style="width:40px; height:40px; position: absolute; margin: 5px 0 0 10px;">
				<input class="col-7" type="text" placeholder="키워드를 입력해주세요" name="searchKeyword" style="border: 0; border-bottom: 2px solid #ddd; margin:0 15px; width: 100%; background-color: transparent; padding-left: 50px;"> 
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
	
	String searchKeyword = request.getParameter("searchKeyword");
	String key_type = request.getParameter("keyword_type");
	String keyword1 = request.getParameter("keyword");
	
	if (books != null && !books.isEmpty()) {%>
	
<%-- 	<%= searchKeyword %> --%>
<%-- 	<%= keyword1!=null?keyword1:request.getParameter("keyword") %> --%>

<% for (ISBNVO book : books) { %>
<hr>
<div id="bookResult" class="row">
    <div class="col-6 bookimg">
      <a href="bookDetail.lo?isbn_book=<%=book.getISBN_BOOK()%>">
      <img src="../imgs/<%= book.getISBN_IMG()%>">
      </a>
    </div>
    <div class="col-6 text-left">
      <ul>
          <li><h5><a href="bookDetail.lo?isbn_book=<%=book.getISBN_BOOK()%>"><%=book.getISBN_TITLE()%></a></h5></li>
          <li><b>ISBN: </b><span><%=book.getISBN_ID()%></span></li>
          <li><b>도서 코드: </b><span><%=book.getISBN_BOOK()%></span></li>
          <li><b>저자: </b><span><%=book.getISBN_AUTHOR()%></span></li>
          <li><b>옮긴이: </b><span><%if(null==book.getISBN_TRANS()){%>-<%}else{%><%=book.getISBN_TRANS()%><%}%></span></li>
          <li><b>발행년도: </b><span><%=book.getISBN_YEAR()%></span></li>
          <li><b>출판사: </b><span><%=book.getISBN_COM()%></span></li>
          <li><b>카테고리: </b><span><%=book.getISBN_CG()%></span></li>
          <li><b>대출가능여부: </b><span><%if("Y".equals(book.getISBN_STATUS())||"Y".equals(book.getISBN_DEL())){%>불가<%}else{%>가능<%}%></span></li>
          
          <%if(String.valueOf(session.getAttribute("USER_ID")).equals("admin")){ %> 
          	<li><b>보유현황: </b><span><%if("Y".equals(book.getISBN_DEL())){%>미보유<%}else{%>보유<%}%></span></li><%} %>
          
        </ul>
    </div>
</div>
 <%}%>
	<hr>
	<% if(searchKeyword!=null){ %>
	<div id="BSearchTextList" class="pageList">
		<%if(nowPage<=1){ %> [이전]&nbsp;
		<%}else{ %>
			<a href="bookSearch.lo?page=<%=nowPage-1 %>&searchKeyword=<%= searchKeyword %>&keyword_type=<%=key_type%>">[이전]</a>&nbsp;
		<%} %>

		<%for(int startP = startPage; startP<=endPage; startP++){
			if(startP==nowPage){%> 
				[<%= startP %>]
			<%}else{ %>
				<a href="bookSearch.lo?page=<%=startP %>&searchKeyword=<%= searchKeyword %>&keyword_type=<%=key_type%>">[<%=startP %>]
				</a>&nbsp;
			<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %> [다음]
		<%}else{ %>
			<a href="bookSearch.lo?page=<%=nowPage+1 %>&searchKeyword=<%= searchKeyword %>&keyword_type=<%=key_type%>">[다음]</a>
		<%} %>
	</div>
	<% } else { %>
	
	<div class="pageList" id="BSearchCategoryList">
	<%if(nowPage<=1){ %> [이전]&nbsp;
		<%}else{ %>
			<a href="bookCategorySearch.lo?page=<%=nowPage-1 %>&keyword=<%= keyword1 %>">[이전]</a>&nbsp;
		<%} %>

		<%for(int startP = startPage; startP<=endPage; startP++){
			if(startP==nowPage){%> 
				[<%= startP %>]
			<%}else{ %>
				<a href="bookCategorySearch.lo?page=<%=startP %>&keyword=<%= keyword1 %>">[<%=startP %>]
				</a>&nbsp;
			<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %> [다음]
		<%}else{ %>
			<a href="bookCategorySearch.lo?page=<%=nowPage+1 %>&keyword=<%= keyword1 %>">[다음]</a>
		<%} %>
	</div>
	<% } %>
	
<%} %>
</div>
<hr>	
	<br><br>
	</section>
</body>
<%@ include file="/footer.jsp" %>
</html>

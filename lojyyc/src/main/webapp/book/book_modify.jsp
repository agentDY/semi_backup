<%@ page import="vo.ISBNVO,vo.UserVO, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/header.jsp"%>
<%
	List<ISBNVO> isbn = (List<ISBNVO>)request.getAttribute("isbn_book");
	System.out.println("jsp페이지book: "+isbn.get(0).getISBN_BOOK());
	String id=(String) session.getAttribute("USER_ID");
	System.out.println("jsp수정: "+id);
// 	System.out.println("모디파이JSP"+isbn.get(0).getISBN_IMG());
// 	System.out.println("모디파이JSP"+isbn.get(0).getISBN_BOOK());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
	<title>lojyyc library - 도서 정보 수정 (admin)</title>
	<script type="text/javascript">
	function modifybook(){
		modifyform.submit();
	}
	</script>
	

<style type="text/css">

h3 {
	padding-top:40px;
	text-align: center;
	color: #474747;
}

table {
	margin: auto;
	width: 750px;

}

.td_left {
	color: #474747;
	border-radius: 5px;
  	border-spacing: 0 10px;  
	width: 80px;
  	background: #F5F5F5; 
	text-align: center;
 	border-width: 1px; 
 	border-top-style: solid; 
	border-right-style: none;
  	border-color: #ddd;
	border-left: solid 3px #03A6A6;
 	font-weight: bold;
}

.td_right {	
	padding-left:20px;
	width: 300px;
	height: 50px;
	background: white;
 	border-width: 1px; 
 	border-top-style: solid;
	border-right-style:none;
	border-left-style:none;
 	border-color: #ddd;
}

input, textarea {
	outline: none;
  	border-style: none;  
	width:100%;
}

button{
	background-color:#03A6A6; color:white;  width:90px; 
	height:40px; border: none;
}

#commandCell {
	text-align: center;
	padding-bottom :50px;
	padding-top: 20px;
}

#ISBN_IMG{
  	display:none;  
}

.btn-select {
    width: 30%;
    line-height: 14px;
    background-color: white;
    box-sizing: border-box;
    border-radius: 3px;
    cursor: pointer;
    color: #474747;
    text-align: left;
    font-weight: bold;
}

  .btn-select:hover,  
  .btn-select:focus {  
      border: 2px solid #037F8C;  
  }  

 .btn_hover:hover, 
 .btn_hover:focus{ 
      border: 2px solid #037F8C;   
      background-color:#037F8C;  
 } 

.list-member {
    display: none;
    position: absolute;
    top: 49px;
    left: 0;
    border: 1px solid #C4C4C4;
    box-sizing: border-box;
    box-shadow: 4px 4px 14px rgba(0, 0, 0, 0.15);
    border-radius: 3px;
}

.btn-select.on+.list-member {
    display: block;
}

.list-member li {
    height: 40px;
    box-sizing: border-box;
    list-style: none; 
}

.list-member li button {
	position: absolute;
    width: 100%;
    border: none;
    background-color: white;
    border-radius: 3px;
    cursor: pointer;
    text-align: left; 
}

 .list-member li button:hover, 
 .list-member li button:focus { 
     border: 2px solid #037F8C;  
 } 

#year::-webkit-scrollbar {
  		display: none; 
	  }
	  
/* #writeForm{ */
/* 	padding-top:20%; */
/* } */

</style>


</head>
<body>
<section id="body_css">
	<img src="/topimgs/mainimg1.png" id="topimgs">
	
<section id = "writeForm">
<h3>도서 정보 수정</h3><br>
<form action="bookModifyPro.lo" method="post" name = "modifyform" enctype="multipart/form-data">
<input type = "hidden" name = "ISBN_ID" value = "<%=isbn.get(0).getISBN_ID()%>"/>
<table style="border-collapse: inherit;">
	<tr>
		<td class="td_left">
			<label for ="ISBN_BOOK" style="display: block; margin:0px;">도서 코드</label>
		</td>
		<td class="td_right">
			<input required="required" type = "text" name="ISBN_BOOK" id ="ISBN_BOOK" value = "<%=isbn.get(0).getISBN_BOOK()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_TITLE" style="display: block; margin:0px;">도서명</label>
		</td>
		<td class="td_right">
			<input required="required" name="ISBN_TITLE" type="text" id = "ISBN_TITLE" value = "<%=isbn.get(0).getISBN_TITLE()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_AUTHOR" style="display: block; margin:0px;">저자</label>
		</td>
		<td class="td_right">
			<input required="required" name="ISBN_AUTHOR" type="text" id = "ISBN_AUTHOR" value = "<%=isbn.get(0).getISBN_AUTHOR()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_TRANS" style="display: block; margin:0px;">옮긴이</label>
		</td>
		<td class="td_right">
			<input name="ISBN_TRANS" type="text" id = "ISBN_TRANS" value = "<%if(isbn.get(0).getISBN_TRANS()==null){ %>-<%}else{ %><%=isbn.get(0).getISBN_TRANS()%><%}%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_YEAR">발행년도</label></td>
		<td class="td_right">
<!-- 					<input name="ISBN_YEAR" type="text" id="ISBN_YEAR" required="required" /> -->
<!-- 					<select id="year" name="ISBN_YEAR" required="required"></select> -->
		    <article style="position: relative;" class="cont-select">
		        <button class="btn-select" style="width:250px; text-align: left;" id="ISBN_YEAR" type="button"><%=isbn.get(0).getISBN_YEAR()%> 년</button>
		        <input style="display: none;"  type="text" name="ISBN_YEAR" id="year_val" value = "<%=isbn.get(0).getISBN_YEAR()%>">
<!--     					        <input style="width:0; height: 0;" type="text" name="ISBN_YEAR" id="year_val" required="required"> -->
		        <ul class="list-member" style="overflow-y:scroll; overflow-z:hidden; width:250px; padding-left:0px; margin-left:0px; height:300px;" id="year">
		        </ul>
		    </article>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_COM" style="display: block; margin:0px;">출판사</label>
		</td>
		<td class="td_right">
			<input required="required" name="ISBN_COM" type="text" id = "ISBN_COM" value = "<%=isbn.get(0).getISBN_COM()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_CG" style="display: block; margin:0px;">분류</label>
		</td>
		<td class="td_right">
			<input required="required" name="ISBN_CG" type="text" id = "ISBN_CG" value = "<%=isbn.get(0).getISBN_CG()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ISBN_INFO" style="display: block; margin:0px;">도서 소개</label>
		</td>
		<td class="td_right">
			<textarea required="required" id = "ISBN_INFO" style="resize:none;" name="ISBN_INFO" cols="40" rows="15"><%=isbn.get(0).getISBN_INFO().replace("<br>","\r\n")%></textarea>
		</td>
	</tr>
	<tr>
		<td style="height:80px;" class="td_left">
			<label style="display: block; margin:0px;" for = "ISBN_IMG">도서 표지<br>(이미지 첨부)</label>
		</td>
		<td style="height:80px;" class="td_right">
			<input type = "file" name="ISBN_IMG" id = "ISBN_IMG" accept=".jpg,.png,.jpeg,.bmp" onchange="changeValue(this)"/>
<!-- 			<input type="file" id="ISBN_IMG" accept=".jpg,.png,.jpeg,.bmp" name="ISBN_IMG" onchange="changeValue(this)"/> -->
			<button style="float:left; border-radius: 3px; margin-top:8px;" class="btn_hover" type="button" id="btn-upload">표지 변경</button>
<%-- 			<input style="outline:none; font-weight:bold; color:#474747; width:80%; padding-left:20px; padding-top:10px; " readonly="readonly" type="text" class="upload-name" value="<%if(null!=isbn.get(0).getISBN_IMG()){%><%="현재 표지 : "+isbn.get(0).getISBN_IMG()%><%}%>"/> --%>
			<textarea style="resize:none; outline:none; font-weight:bold; color:#474747; width:80%; padding-top:6px; padding-left:20px; vertical-align: sub;" readonly="readonly" class="upload-name"><%if(null!=isbn.get(0).getISBN_IMG()){%><%="현재 표지 : "+isbn.get(0).getISBN_IMG()%><%}%></textarea>
		</td>
	</tr>
	<tr>
		<td style="border-bottom-style: solid;" class="td_left">
			<label for = "ISBN_DEL" style="margin: 0px;">보유현황</label>
		</td>
		<td style="border-bottom-style: solid; font-weight:bold; color:#474747;" class="td_right">
		
			<div class="custom-control custom-radio custom-control-inline">
				<input type="radio" id="customRadioInline1" name="ISBN_DEL" class="custom-control-input " value="Y" <%if("Y".equals(isbn.get(0).getISBN_DEL())){%>Checked="Checked"<%}%>/>
				<label class="custom-control-label" for="customRadioInline1">미보유</label>
			</div>
			<div class="custom-control custom-radio custom-control-inline">
				<input type="radio" id="customRadioInline2" name="ISBN_DEL" class="custom-control-input" value="N" <%if("N".equals(isbn.get(0).getISBN_DEL())){%>Checked="Checked"<%} %>/>
				<label class="custom-control-label" for="customRadioInline2">보유</label>
			</div>
		</td>
	</tr>
</table>

 			<script type="text/javascript"
 			> 
//  			파일업로드 버튼 대체 
				$(function () {
				$('#btn-upload').click(function (e) {
				e.preventDefault();
				$('#ISBN_IMG').click();
				});
				
				});
	
		            function changeValue(obj){
		            	var fileName = $("#ISBN_IMG").val();
		            	  $(".upload-name").val("변경할 표지 : "+fileName);
		            }
		            
		            
		            //년도 버튼 변수(상수)선언
		            const btn = document.querySelector('.btn-select');
		            const list = document.querySelector('.list-member');
			            
			           // 버튼 토글 열기닫기(버튼 또는 아무영역 클릭하여 닫기)
					   $('.btn-select').click(function(event){
						    event.stopPropagation();
						    $('.list-member').toggle();
						});
						 
						$(document).click(function(){
						    $('.list-member').hide();
						});
			            
						
						
			            //년도 버튼 대체처리
			            list.addEventListener('click', (event) => {
			            	 
			                if (event.target.nodeName === "BUTTON") {
			                    btn.innerText = event.target.innerText;
			                    btn.classList.remove('on');
			                    $("#year_val").val(event.target.innerText);
			                  
			            		$('.cont-select button').css('background-color','white');
			                    $('.cont-select button').css('text-align','left');
			       	        	$('.cont-select button').css('color','#474747');
			       	        	$('.cont-select button').css('font-weight','bold');
			            		$('.cont-select button:hover').css('background-color','#037F8C');
			                }
			            });
			            
			          
			            // 년도 생성 함수
			            $(document).ready(function(){
			            	setDateSelectBox();
			            	
			            });    

			            function chk_sub(){
			            	let chk = $('#year_val').val();
			            	if(chk===''||chk===null||chk==='undefined'){
				            	alert('발행년도를 선택해주세요.');
				            	$('.list-member').toggle();
				            	return false;
		           			}
			            }
			            
			            
			            function setDateSelectBox(){
			            	var now = new Date();
			            	var now_year = now.getFullYear();
			            	
			            	
			            	// 1990년 부터 올해까지
			            	for(var i = now_year; i >= 1990; i--){
							$("#year").append("<li><button id='"+i+"' class="+'"btn-select"'+" type="+'"button"'+" value='"+i+"' name="+'"ISBN_YEAR"'+">"+i+" 년"+"</button></li>");
			            	}
			            }    
		            
 			</script> 




	<section id = "commandCell">
		<button style="border-radius: 3px; margin:auto;" class="btn_hover" type="submit">수정하기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button style="border-radius: 3px; margin:auto;" class="btn_hover" type="button" value="돌아가기" onClick="javascript:history.go(-1)">돌아가기</button>
<!-- 			<button type="submit">수정하기</button>&nbsp;&nbsp; -->
	</section>
</form>
</section>
</section>
</body>
<%@include file="/footer.jsp"%>
</html>
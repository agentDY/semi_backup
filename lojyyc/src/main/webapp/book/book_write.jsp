<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.ISBNVO, vo.PageInfo" %>
<%@include file="/header.jsp"%>	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"> -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.js"></script>
<!-- <script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script> -->

<title>lojyyc library - 도서 등록 (admin) </title>

</head>

<style type="text/css">


h3 {
	margin:auto;
	width:750px;
	padding-top:40px;
	text-align: left;
	color: #474747;
}

table {
	margin: auto;
	width: 750px;

}

.td_left {
	color: #474747;
	border-radius: 5px;
	width: 80px;
  	background: #F5F5F5; 
	text-align: center;
 	border-width: 1px; 
 	border-top-style: solid; 
	border-right-style: none;
  	border-color: #ddd;
	border-left: solid 3px #03A6A6;
 	font-weight: bold;
 	margin-bottom:2px;
}

.td_right {	
	margin-bottom:2px;
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
    background-color: #03A6A6;
    box-sizing: border-box;
    border-radius: 3px;
    cursor: pointer;
}

  .btn-select:hover,  
  .btn-select:focus {  
      border: 2px solid #037F8C;  
      background-color:#037F8C; 
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
    background-color: #03A6A6;
    border-radius: 3px;
    cursor: pointer;
    text-align: center; 
}

 .list-member li button:hover, 
 .list-member li button:focus { 
     background-color:#037F8C; 
 } 

#year::-webkit-scrollbar {
  		display: none; 
	  }
	  
/* #body_css{ */
/*  padding-top:126px;  */
/* }	  */
	 
</style>

	<body>
<section id="body_css">
	<img src="/topimgs/mainimg1.png" id="topimgs">
	<section id="writeForm">
		<h3>도서 등록</h3><br>
		<form onsubmit="return chk_sub()" name="mForm" id="mForm" action="bookWritePro.lo" method="post" enctype="multipart/form-data">
			<table style="border-collapse: inherit;">
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px;" for="ISBN_ID">ISBN</label></td>
					<td class="td_right"><input name="ISBN_ID" type="text"
						id="ISBN_ID" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px;" for="ISBN_BOOK">도서 코드</label></td>
					<td class="td_right"><input type="text" name="ISBN_BOOK"
						id="ISBN_BOOK" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_TITLE">도서명</label></td>
					<td class="td_right"><input name="ISBN_TITLE" type="text"
						id="ISBN_TITLE" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_AUTHOR">저자</label></td>
					<td class="td_right"><input name="ISBN_AUTHOR" type="text"
						id="ISBN_AUTHOR" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_TRANS">옮긴이</label></td>
					<td class="td_right"><input name="ISBN_TRANS" type="text"
						id="ISBN_TRANS"/></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_YEAR">발행년도</label></td>
					<td class="td_right">
<!-- 					<input name="ISBN_YEAR" type="text" id="ISBN_YEAR" required="required" /> -->
<!-- 					<select id="year" name="ISBN_YEAR" required="required"></select> -->
					    <article style="position: relative;" class="cont-select">
					        <button class="btn-select" style="width:250px; text-align: center;" id="ISBN_YEAR" type="button">선택하여 입력해주세요.</button>
					        <input style="display: none;"  type="text" name="ISBN_YEAR" id="year_val">
<!--     					        <input style="width:0; height: 0;" type="text" name="ISBN_YEAR" id="year_val" required="required"> -->
					        <ul class="list-member" style="overflow-y:scroll; overflow-z:hidden; width:250px; padding-left:0px; margin-left:0px; height:300px;" id="year">
					        </ul>
					    </article>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_COM">출판사</label></td>
					<td class="td_right"><input name="ISBN_COM" type="text" required="required"
						id="ISBN_COM" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_CG">분류</label></td>
					<td class="td_right"><input name="ISBN_CG" type="text" required="required"
						id="ISBN_CG" /></td>
				</tr>
				<tr>
					<td class="td_left"><label style="display: block; margin: 0px" for="ISBN_INFO">도서 소개</label></td>
					<td class="td_right"> <textarea id="ISBN_INFO" name="ISBN_INFO" required="required"
							cols="40" rows="15" style="resize:none;" required="required"></textarea></td>
				</tr>
				<tr>
					<td style="border-bottom-style: solid; height:80px;" class="td_left"><label style="display: block; margin: 0px" for="ISBN_IMG">도서 표지<br>(이미지 첨부)</label></td>
<!-- 					<td style="border-bottom-style: solid; height:80px;" class="td_right"><input accept=".jpg,.png,.jpeg,.bmp" name="ISBN_IMG" type="file" id="ISBN_IMG" /> </td> -->

<!-- 					<td style="border-bottom-style: solid; height:80px;" class="td_right"><input type="file" id="ISBN_IMG" name="ISBN_IMG" onchange="changeValue(this)" accept=".jpg,.png,.jpeg,.bmp;"/> -->

					<td style="border-bottom-style: solid; height:80px;" class="td_right">
					<input type="file" id="ISBN_IMG" accept=".jpg,.png,.jpeg,.bmp" name="ISBN_IMG" onchange="changeValue(this)"/>
					<button class="btn_hover" style="float:left; border-radius: 3px; margin-top:8px;" type="button" id="btn-upload">표지 등록</button>
					<textarea  style="resize:none; outline:none; font-weight:bold; color:#474747; width:80%; padding-top:10px; padding-left:20px; vertical-align: sub;" readonly="readonly" class="upload-name"></textarea>
					</td>
				</tr>
			</table>
			
		
			
 			<script type="text/javascript"> 
// 			파일업로드 버튼 대체 처리 함수
				$(function () {
				$('#btn-upload').click(function (e) {
				e.preventDefault();
				$('#ISBN_IMG').click();
				});
				
				});
	
	            function changeValue(obj){
	            	var fileName = $("#ISBN_IMG").val();
	            	  $(".upload-name").val("현재 첨부된 표지 : "+fileName);
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

			<section id="commandCell">
				<button class="btn_hover" style="border-radius: 3px; margin:auto;" type="reset" value="다시쓰기">다시쓰기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn_hover" style="border-radius: 3px; margin:auto;" type="submit">등록하기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn_hover" style="border-radius: 3px; margin:auto;" type="button" value="돌아가기" onClick="javascript:history.go(-1)">돌아가기</button>
			</section>
		</form>
	</section>
	</section>
</body>
	<%@include file="/footer.jsp"%>
</html>
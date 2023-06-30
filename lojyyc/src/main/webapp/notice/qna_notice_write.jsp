<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
<title>MVC 게시판</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous"> -->
<style type="text/css">
* {
	padding : 0;
	margin : 0;
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

.headtitle {
	height: 150px;
	margin: 0 auto;
}
h3 {
	text-align: center;
	vertical-align : middle;
	height : 80px;
	padding-top : 70px;
}


.td_left{
	border-radius: 5px;
	border-spacing: 0 10px;
	background: #F5F5F5 !important; 
	text-align: center;
	border-width: 1px; 
	border-top-style: solid; 
	border-right-style: none;
	border-color: #ddd;
	border-left: solid 3px #03A6A6;
	font-weight: bold;
    width: 30%;
    
    
}

.td_right {
	padding-left:20px;
	background: white;
 	border-width: 1px; 
 	border-top-style: solid;
	border-right-style:none;
	border-left-style:none;
 	border-color: #ddd;
    width: 70%;
    vertical-align: middle;
}

input {
	border-style : none;
	font-size: 16px;
	width : 400px;
}

#NOTICE_PIN {
	width : 14px;
	height : 14px;
}

table textarea{
	margin-left : 10px;
	height : 500px;
	width : 260px;
	border : 1px solid #F0F0F0;
	border-style: none;
	resize : none;
	padding-left:10px;
}



#td_file input {
	top : 30%;
	border-style : none;
	height : 30px;
}

button{
	background-color:#03A6A6; 
	color:white;  
	width:90px; 
	height:40px; 
	border: none;
	margin-left : 50px;
}

#commandCell{
	margin-top : 30px;
	margin-bottom : 30px;
}

.btn-container {
    display: flex;
    justify-content: center;
}

.btn-container button {
    --bs-btn-padding-x: 0rem;
}

.btn-container button:hover, .btn-container button:focus {
	background-color: #037F8C!important;
}

.btn_hover:hover, .btn_hover:focus {
	background-color: #037F8C!important;
}

@media screen and (min-width: 768px) {
    .table {
        margin: 0 auto;
        width: 850px;
    }
    
    .btn_hover:hover, .btn_hover:focus {
	background-color: #037F8C!important;
	}
    
    .container-fluid {
    margin: 0 auto;
    }
    
    #container-fluid .td_left {
        padding: 13px !important;
        width: 30% ;
    }
    
    #maintable .td_left{
	border-radius: 5px;
	border-spacing: 0 10px;
	background: #F5F5F5 !important; 
	text-align: center;
	border-width: 1px; 
	border-top-style: solid; 
	border-right-style: none;
	border-color: #ddd;
	border-left: solid 3px #03A6A6;
	font-weight: bold;
    width: 200px;
    
    
}

.td_right {
	padding-left:20px;
	background: white;
 	border-width: 1px; 
 	border-top-style: solid;
	border-right-style:none;
	border-left-style:none;
 	border-color: #ddd;
    width: 70%;
    vertical-align: middle;
}
    
    #container-fluid .td_right {
        width: 70% ;
    }
    
    #writeForm {
    	width : 80%;
    	margin: 0 auto;
    }
    
    table textarea {
        margin-left: 10px;
        height: 500px;
        width: 680px;
        border: 1px solid #F0F0F0;
        border-style: none;
        resize: none;
        padding-left:10px;
    }
  
    .btn-container {
        margin: 0 auto;
        width: 850px;
    }
    
    label {
    	width:100px;
    }
    
    .btn-container button:hover, .btn-container button:focus {
	background-color: #037F8C!important;
}
	
</style>
<script>

function toList(){
	location.href="noticeList.lo";
}
</script>

</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
	<!-- 게시판 등록 -->
	<div class="container-fluid">
			<div class="headtitle">
			<h3>공지사항 등록</h3>
			</div>
		<section id="writeForm">
			<form action="/notice/noticeWritePro.lo" style="width:100%;" method="post" enctype="multipart/form-data" name="noticeform">
                       
				<table class="table" id="maintable" style="width:50%; border-collapse : inherit;">
					<tr>
						<td class="td_left"><label style="margin:0px;" for="NOTICE_ADMIN">글쓴이</label></td>
						<td class="td_right">관리자</td>
					</tr>
					<tr>
						<td class="td_left"><label style="margin:0px;" for="NOTICE_TITLE">제 목</label></td>
						<td class="td_right"><input name="NOTICE_TITLE" type="text" id="NOTICE_TITLE" maxlength="50" required="required" /></td>
					</tr>
					<tr>
						<td class="td_left"><label style="margin:0px;" for="NOTICE_PIN">상단고정</label></td>
						<td class="td_right">
							<div class="custom-control custom-checkbox">
								<input style="margin-top:4px;" type="checkbox" class="custom-control-input" id="NOTICE_PIN"  name="NOTICE_PIN" value="1">
								<label class="custom-control-label" for="NOTICE_PIN"></label>
							</div>
						</td>
					</tr>
					
<!-- 					<tr> -->
<!-- 						<td class="td_left"><label style="margin:0px;" for="NOTICE_PIN">상단고정</label></td> -->
<!-- 						<td class="td_right"><input style="margin-top:4px;" name="NOTICE_PIN" type="checkbox" id="NOTICE_PIN" value="1"/></td> -->
<!-- 					</tr> -->
					
					<tr>
						<td class="td_left" style="vertical-align: middle;"><label style="margin:0px;" for="NOTICE_CONT">내 용</label></td>
						<td class="td_right"><textarea id="NOTICE_CONT" name="NOTICE_CONT" required="required" style="padding-top:10px;"></textarea></td>
					</tr>
<!-- 						<td class="td_right" id="td_file" ><input name="NOTICE_FILE" type="file" id="NOTICE_FILE" style="margin-top:3px; font-size: 13px;"/></td> -->
					<tr style="border-bottom: 1px solid #ddd;">
						<td class="td_left" style="vertical-align: middle;"><label style="margin:3px auto;" for="NOTICE_FILE"> 파일 첨부 </label></td>
						<td style="border-bottom-style: solid;" class="td_right" id="td_file">
							<input style="display:none;" type="file" id="NOTICE_FILE" name="NOTICE_FILE" onchange="changeValue(this)"/>
							<button class="btn_hover" style="float:left; border-radius: 3px; margin-top:4px;" type="button" id="btn-upload">파일 첨부</button>
							<textarea style="resize:none; outline:none; font-weight:bold; color:#474747; height:50px; width:80%; padding-top:15px; padding-left:10px; vertical-align: sub;" readonly="readonly" class="upload-name"></textarea>
						</td>
					</tr>
				</table>
				
		<script type="text/javascript"> 
// 			파일업로드 버튼 대체 처리 함수
				$(function () {
				$('#btn-upload').click(function (e) {
					e.preventDefault();
					$('#NOTICE_FILE').click();
					});
				});
	
	            function changeValue(obj){
	            	var fileName = $("#NOTICE_FILE").val();
	            	  $(".upload-name").val("현재 첨부된 파일 : "+fileName);
	            }
	       </script>
	       
				<div class="btn-container" id="commandCell">
					<button id="btn_bot" class="btn" type="reset" style="background-color:#03A6A6; color:white; border-radius:3px; margin-right:30px; ">다시쓰기</button>
					<button id="btn_bot" class="btn" type="submit" style="background-color:#03A6A6; color:white; border-radius:3px; margin-right:30px;">등록</button>
					<button id="btn_bot" class="btn" type="button" onclick="toList();" style="background-color:#03A6A6; color:white; border-radius:3px; margin-right:30px;">목록으로</button>
				</div>
			</form>
		</section>
	</div>
		
</section>
</body>
		<%@ include file="../footer.jsp" %>
</html>
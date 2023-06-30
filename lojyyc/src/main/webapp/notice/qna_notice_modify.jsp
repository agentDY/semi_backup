<%@page import="vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	<%@ include file="../header.jsp" %>
<%
NoticeVO notice = (NoticeVO) request.getAttribute("notice");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>MVC 게시판</title>
<script type="text/javascript">
	function modifyboard() {
		modifyform.submit();
	}
</script>
<style type="text/css">
body {
	margin : 0; padding : 0;
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
#modifyForm{
	width: 100%;

}
.headtitle {
	height: 150px;
	margin: 0 auto;
	text-align: center;
}

h3 {
	text-align: center;
	vertical-align : middle;
	height : 80px;
	}

input {
	border-style : none;
	font-size: 16px;
	width : 420px;
}

table textarea {
	margin-top : 10px;
	margin-left : 10px;
	height : 500px;
	width : 680px;
	border : 1px solid #F0F0F0;
	border-style: none;
	resize : none;
	padding-left:10px;
}

p {
	margin : 2px;
}

.headtitle{
	width : 100%;
	height: 150px;
	margin: 0 auto;
	padding-top : 70px;
	}

.td_left {
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
    width: 40%;
}

.td_right {
	padding-left:20px;
	background: white;
 	border-width: 1px; 
 	border-top-style: solid;
	border-right-style:none;
	border-left-style:none;
 	border-color: #ddd;
    width: 60%;
    vertical-align: middle;
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

#NOTICE_FILE input {
	top : 30%;
	border-style : none;
	height : 30px;
}

#NOTICE_PIN{
		width: 14px;
		height: 14px;
	}

#commandCell {
	margin: 30px auto;
	text-align: center;
}

.btn-container {
    display: flex;
    justify-content: center;
}

.btn-container button {
    --bs-btn-padding-x: 0rem;
}

#commandCell button:hover, #commandCell button:focus {
	background-color: #037F8C!important;
}

.btn_hover{
	background-color:#03A6A6; color:white;  width:90px; 
	height:40px; border: none;
}

.btn_hover:hover, .btn_hover:focus {
	background-color: #037F8C!important;
}


@media screen and (min-width: 768px) {
    .table {
        margin: 0 auto;
        width: 850px;
    }
    
    .container-fluid {
    margin: 0 auto;
    }
    
    .td_left {
        padding: 13px !important;
        width: 25%;
    }
    
    .td_right {
        width: 75%;
    }
    
	#NOTICE_PIN{
		width: 14px;
		height: 14px;
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

	.headtitle {
	width: 100%;
	height: 150px;
	margin: 0 auto;
	text-align: center;
}	

	#modifyForm{
	width: 50%;
	margin : 0 auto;

}

label {
    	width:100px;
    }
    
#commandCell button:hover, #commandCell button:focus {
	background-color: #037F8C!important;
}

.btn_hover{
	background-color:#03A6A6; color:white;  width:90px; 
	height:40px; border: none;
}

.btn_hover:hover, .btn_hover:focus {
	background-color: #037F8C!important;
}

</style>
<script>
	function goBack() {
		history.back(-1);
	}
</script>
</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
		
	<div class="container-fluid">

	<div id="modifyForm">
		<div class="headtitle">
		<h3>공지사항 수정</h3>
		</div>	

			<form action="/notice/noticeModifyPro.lo" method="post"
				enctype="multipart/form-data" name="modifyform">

				<input type="hidden" name="NOTICE_NUM"
					value="<%=notice.getNOTICE_NUM()%>" />

				<table 	style="border-collapse : inherit;">
					<tr>
						<td class="td_left"><label style="margin:0px;" for="NOTICE_TITLE">제 목</label></td>
						<td class="td_right"><input name="NOTICE_TITLE" type="text"
							id="NOTICE_TITLE" value="<%=notice.getNOTICE_TITLE()%>" /></td>
					</tr>
					<tr>
						<td class="td_left"><label  style="margin:0px;" for="NOTICE_PIN">상단고정</label></td>
						
						<td class="td_right">
							<div class="custom-control custom-checkbox">
								<input style="margin-top:4px;" type="checkbox" class="custom-control-input" id="NOTICE_PIN"  name="NOTICE_PIN" value="1"
							<%if (notice.getNOTICE_PIN() != 1) {
				} else {%>
							checked="checked" <%}%>>
								<label class="custom-control-label" for="NOTICE_PIN"></label>
							</div>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td class="td_left" style="height: 25px;"><label style="margin:0px;" for="NOTICE_FILE">첨부파일</label></td>
						
						<td class="td_right" style="height: 80px;">
							<input style="display:none;" type="file" id="NOTICE_FILE" name="NOTICE_FILE" onchange="changeValue(this)"/>
							<p style="margin:10px 0px 10px 0px" id="filename">
								<%
								if (notice.getNOTICE_REALFILE() == null) {
								%>
								<%
								} else {
								%><%="현재 첨부된 파일 : "+notice.getNOTICE_FILE()%>
								<%
								}
								%>
							</p>
							<button class="btn_hover" style="float:left; border-radius: 3px; margin-top:2px;" type="button" id="btn-upload">파일 첨부</button>
							<textarea style="resize:none; outline:none; font-weight:bold; color:#474747; height:50px; width:80%; margin-top:0px; padding-top:2px; padding-left:8px; vertical-align: sub;" readonly="readonly" class="upload-name"></textarea>
<!-- 							<input type="file" id="NOTICE_FILE" name="NOTICE_FILE" cols="40" rows="15" style="margin-top:15px; font-size: 13px;"> -->
						</td>
					</tr>
					<tr>
						<td class="td_left"><label style="margin:0px;" for="NOTICE_CONT">내 용</label></td>
						<td class="td_right"><textarea id="NOTICE_CONT" name="NOTICE_CONT" cols="40"
								rows="15"><%=notice.getNOTICE_CONT().replace("<br>","\r\n")%></textarea></td>
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
	            	  $(".upload-name").val("새로 첨부할 파일 : "+fileName);
	            }
		</script>

				<div id="commandCell">
					<button class="btn" type="submit" style="background-color:#03A6A6; color:white; border-radius:3px; width:100px; height:40px; margin: 10px 15px; text-align:center; border-style:none;">수정</button>
					<button class="btn" type="button" onclick="goBack();" style="background-color:#03A6A6; color:white; border-radius:3px; width:100px; height:40px; margin: 10px 15px; text-align:center; border-style:none;">뒤로가기</button>
				</div>
			</form>
			</div>
		
		</div>

	</section>
</body>
	<%@ include file="../footer.jsp" %>
</html>
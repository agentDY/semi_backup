<%@page import="vo.NoticeVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>

<%
	NoticeVO noticeVO = (NoticeVO)request.getAttribute("noticeVO"); 
	String sessionId = (String) session.getAttribute("USER_ID");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC 게시판</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous"> -->
<style type="text/css">
#noticeForm {
	margin: auto;
}

#headtitle{
	
	margin: 20px auto;
}

h3 {
	text-align: center;
	vertical-align : middle;
	padding-top : 30px;
}

#basicInfoArea {
	margin : 0 auto;
}

table {
	margin: 0 auto;
	width: 850px;
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
}

.table td {
    vertical-align: middle;
  }
#cont{
	text-align : justify;

}

#noticeForm{
	width :  100%;
}

#noticeContentArea {
	margin: 0 auto;
	height: 350px;
	overflow: auto;
	width : 90%;
}

#commandList {
	margin : 0 auto;
	height : 70px;
	text-align: center;
	margin : 10px auto;
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

@media screen and (min-width: 768px) {
  
  	#noticeForm{
	width :  50%;
	}
  
  
    .table {
        margin: 0 auto;
        width: 850px;
       
    }
    
    #maintable td {
    vertical-align: middle;
  }
    
    .container-fluid {
    margin: 0 auto;
    }
    
  .td_left {
        padding: 13px !important;
        width: 16%;
        vertical-align: middle;
    }
    
    .td_right {
        width: 75%;
              
    }
    
    table textarea {
        margin-left: 10px;
        height: 500px;
        width: 680px;
        border: 1px solid #F0F0F0;
        border-style: none;
        resize: none;
    }
    .btn-container {
        margin: 0 auto;
/*         width: 850px;  */
    }

	hr {
	    margin-top: 1rem;
	    margin-bottom: 1rem;
	    border: 0;
	    border-top: 1px solid rgba(0,0,0,.1);
    }
    
    #commandList {
	margin : 0 auto;
/* 	height : 70px; */
	text-align: center;
	margin : 10px auto;
}

.btn-container button:hover, .btn-container button:focus {
	background-color: #037F8C!important;
}
</style>

<script>
function toList(){
	location.href="/notice/noticeList.lo";
}

function toModify(){
	location.href="noticeModifyAdmin.lo?notice_num=<%=noticeVO.getNOTICE_NUM() %>";
}

function toDelete(){
	location.href="noticeDeleteAdmin.lo?notice_num=<%=noticeVO.getNOTICE_NUM()%>&notice_realfile=<%=noticeVO.getNOTICE_REALFILE()%>";
}
</script>

</head>

<body>

<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
	<!-- 게시판 수정 -->
<div class="container-fluid">
	<div style="width:80%" id="noticeForm">
		<div id="headtitle">
		<h3>글 내용 상세보기</h3>
		</div>
        <div style="width:75%" id="basicInfoArea">
            <table class="table" id="maintable" style="border-collapse:inherit;">
                <tr>
                    <td class="td_left">
                        제 목
                    </td>
                    <td class="td_right">
                     <%=noticeVO.getNOTICE_TITLE()%> 
                    </td>
               		 </tr>
                	<tr>
                	 <tr>
                    <td class="td_left">
                        날 짜
                    </td>
                    <td class="td_right">
                     <%=noticeVO.getNOTICE_DATE()%> 
                    </td>
               		 </tr>
                	<tr>
                    <td class="td_left">
                        첨부파일
                    </td>
                    <td>
                    <!-- 이거되나? -->
                        <%if((noticeVO.getNOTICE_FILE()!=null)){ %>
                        <a href="/notice/filedown.lo?NOTICE_FILE=<%=noticeVO.getNOTICE_REALFILE()%>" style="color : black;"><%=noticeVO.getNOTICE_FILE()%> </a>
                        <%} else {%><%} %> 
                    </td>
                </tr>
                
                <tr>
                    <td class="td_left">
                        내용
                    </td>
                    <td>
                    <textarea style="outline:none; width: 100%;" readonly="readonly" id="cont"><%=noticeVO.getNOTICE_CONT().replace("<br>","\r\n") %></textarea>
                     </td>
                </tr>
               </table>
               </div>
                <hr>
            
	   <!--  </div>	
        <div id="noticeContentArea">
        </div>
        </table> --> 
       
        <div class="btn-container" id="commandList">
           <% if(sessionId == null){ %>
	<% } else if(sessionId.equals("admin")) { %>
		 <button type="button" onclick="toModify();"style="background-color:#03A6A6; color:white; border-radius:3px; width:100px; height:40px; margin: 10px 15px; text-align:center; border-style:none">수정</button>
         <button type="button" onclick="toDelete();" style="background-color:#03A6A6; color:white; border-radius:3px; width:100px; height:40px; margin: 10px 15px; text-align:center; border-style:none">삭제</button>	
		
	<% } else{}%>
           
          <button type="button" onclick="toList();" style="background-color:#03A6A6; color:white; border-radius:3px; width:100px; height:40px; margin: 10px 15px; text-align:center; border-style:none">목록</button>	
        </div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
</section>
</body>
	<%@ include file="../footer.jsp" %>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
$(function(){
$("#idcheck").click(function(){
// 	let a = $("#uid").val();
// 	alert("uid: "+a);
	$.ajax({
		url : "/user/userDoubleCheckAction.lo",
		type : "post",
// 		data : {id: document.frm.id.value},
		data : {USER_ID: $("#uid").val()},
		cache : false, //cache 저장 안함
		success : function(data){
			console.log('success');
			alert(data.map.id+''+data.map.str);
		},
		errer : function(){
			alert('errer');
		}
	});
});
});
</script>


<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
<title>Insert title here</title>

<style type="text/css">
/* #writeForm { */
/* 	width: 500px; */
/* 	height: 610px; */
/* 	margin: auto auto 100px auto; */
/* } */

h2 {
/* 	text-align: center; */
	color: #474747;
}

table {
	margin: auto;
	width: 600px;

}

.td_left {
/* 	color: #037F8C; */
	color: #474747;
/* 	border-collapse: separate; */
	border-radius: 5px;
  	border-spacing: 0 10px;  
	width: 150px;
/*  	background: rgb(4,191,191); */
/*   	background: rgb(246,246,246);  */
/*  	border-bottom-style: solid;  */
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
/*  	border-bottom-style: solid; */
	border-right-style:none;
	border-left-style:none;
 	border-color: #ddd;
}

button{
	background-color:#03A6A6; color:white;  width:130px; 
	height:35px; border: none;
}

#commandCell {
	text-align: center;
/* 	margin:auto; */
	padding-bottom :50px;
	padding-top: 20px;
	height:35px;
}

#commandCell button{
	width:85px;
	height: 40px;
/* 	    padding: 3px 5px; */
}

button:hover{
	background-color: #037F8C;
}

input, textarea {
	outline:none;
/* 	border:1px solid #ddd; */
	border-style: none;
  	width:260px;
  	height:35px;
}


#sample6_postcode {width:253px; border-radius: 3px; margin:3px; border:none; border-bottom:solid 1px #ddd;}
#sample6_address {width:390px;  border-radius: 3px; margin:3px; border:none; border-bottom:solid 1px #ddd;}
#sample6_detailAddress {width:390px; border-radius: 3px; margin:3px; border:none; border-bottom:solid 1px #ddd;}
#sample6_extraAddress {width:390px; border-radius: 3px; margin:3px; border:none; border-bottom:solid 1px #ddd;}


#commandCell {
	text-align: center;
}

</style>

</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">
<br><br><br>

<%if ((String)session.getAttribute("USER_ID") == null && (String)session.getAttribute("username") == null) {%>
	<section id="writeForm">

		   <form name="joinform" action="/user/userJoinAction.lo" method="post"  class="needs-validation" novalidate >
		
		   <table style="border-collapse: inherit;">
		
		   <tr>
		   	<td colspan="2" ><h3 style="text-align:left;">회원가입</h3><br></td>
		   </tr>   
		   <tr>
			<td class="td_left"><label style="margin: 0px;" for="uid">아이디</label></td>
			<td class="td_right">
				<input type="text" name="uid" id="uid" placeholder="숫자, 영어 대 소문자 5~16자리" required>
				<button type="button" id="idcheck" style="border-radius: 3px; margin:auto;">아이디 중복체크</button>
				<div class="valid-feedback">멋진 아이디네요!</div>
		    	<div class="invalid-feedback">필수 정보입니다.</div>
		    </td>
			</tr>
			
		    <tr>
				<td class="td_left"><label style="margin: 0px" for="upw">비밀번호</label></td>
				<td class="td_right">
					<input name="upw" type="password" id="upw" placeholder="숫자, 영어 대 소문자 5~16자리" required>
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>	
			</tr>
		
		    <tr>
				<td class="td_left"><label style="margin: 0px" for="upw2">비밀번호 확인</label></td>
				<td class="td_right">
					<input name="upw2" type="password" id="upw2" placeholder="숫자, 영어 대 소문자 5~16자리" required>
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>
			</tr>
			
		    <tr>
				<td class="td_left"><label style="margin: 0px" for="USER_NAME">이름</label></td>
				<td class="td_right">
					<input name="USER_NAME" type="text" id="USER_NAME" placeholder="한글, 영문 대 소문자 2~16자리" required>
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>
			</tr>
		
		    <tr>
				<td class="td_left"><label style="margin: 0px" for="USER_ADDR">주소</label></td>
				<td class="td_right">
					<input type="text" id="sample6_postcode" name="USER_ADDR1" placeholder="우편번호" required>
					<button type="button" style="border-radius: 3px; margin:auto;" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">우편번호 찾기</button>
					<input type="text" id="sample6_address" name="USER_ADDR2" placeholder="주소" required><br>
					<input type="text" id="sample6_detailAddress" name="USER_ADDR3" placeholder="상세주소">
					<input type="text" id="sample6_extraAddress" name="USER_ADDR4" placeholder="참고항목">
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>
			</tr>
		
			<tr>
				<td class="td_left"><label style="margin: 0px" for="USER_TEL">전화번호</label></td>
				<td class="td_right">
					<input name="USER_TEL" type="text" id="USER_TEL" placeholder="숫자만 입력해주세요" required>
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>
			</tr>
			
			<tr>
				<td class="td_left"><label style="margin: 0px" for="USER_EMAIL">이메일</label></td>
				<td class="td_right">
					<input name="USER_EMAIL" type="text" id="USER_EMAIL" placeholder="(@)포함 주소" required>
					<div class="invalid-feedback">필수 정보입니다.</div>
				</td>
			</tr>
	    </table>
	    <br><br>
		
		<section id="commandCell">
			<button style="border-radius: 3px; margin:auto;" type="reset" value="다시쓰기">다시쓰기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button style="border-radius: 3px; margin:auto;" type="submit" value="회원가입">회원가입</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button style="border-radius: 3px; margin:auto;" type="button" onclick="javascript:history.go(-1)">취소</button>
			
		</section>
	
	</form>
</section>
		
<%}else{%>
<script>
		location.href='/index.jsp';
</script><%} %>


<script>
(function() {
  'use strict';
  window.addEventListener('load', function() {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
        valChk(event);
      }, false);
    });
  }, false);
})();

function valChk(e){
	  e.preventDefault();
	  var
	  emailRegex = /^[0-9A-Z_a-z{|}~]+@[A-Za-z{|}~]+.[A-Za-z]+$/,
	  idRegex = /^[0-9A-Za-z]+$/,
	  telRegex = /^010\d{4}\d{4}$/,
	  passwordRegex = /^[0-9A-Za-z]{5,16}$/,
	  id = $( "#uid" ),
	  name = $( "#USER_NAME" ),
	  email = $( "#USER_EMAIL" ),
	  password = $( "#upw" ),
	  passwordre = $('#upw2'),
	  tel = $( "#USER_TEL" );
	  var valid = true;
	  valid = valid && checkRegexp( id, idRegex,"아이디 입력 형식이 잘못되었습니다." );
	  valid = valid && checkRegexp( password, passwordRegex,"비번 입력 형식이 잘못되었습니다." );
	  valid = valid && checkRegexp( email, emailRegex,"이메일 입력 형식이 잘못되었습니다." );
	  valid = valid && checkRegexp( tel, telRegex,"전화번호 입력 형식이 잘못되었습니다." );
	  valid = valid && checkLength( id, "아이디", 4, 16 );
	  valid = valid && checkLength( name, "사용자이름", 3, 16 );
	//   valid = valid && checkLength( password, "비밀번호", 5, 16 );
	  valid = valid && checkMatch(password.val(), passwordre.val());
	  
  
//비밀번호 매칭
function checkMatch(p, pre){
  let pval = p;
  console.log(pval);
  let pvalre = pre;
  console.log(pvalre);
  let res = true;
  if(pval != pvalre){
    alert("비밀번호가 일치하지 않습니다.");
    res = false;
  }
  return res;
}
//정규식 일치 여부 확인
  function checkRegexp( o, regexp, n ) {
    let cnt = $("#"+o.attr('id')+" ~ div.invalid-feedback").length;
    console.log('o: ',o);
    console.log('cnt: ',cnt);
    if ( !( regexp.test( o.val() ) ) ) {
//       if(cnt == 0) o.closest("div.row").nextAll("div.invalid-feedback").eq(0).text(n).show();
//       else{
         o.nextAll("div.invalid-feedback").eq(0).text(n).show();
         o.nextAll("div.valid-feedback").eq(0).hide();
//       }
      return false;
    } else {
//       if(cnt == 0) o.closest("div.row").nextAll("div.invalid-feedback").eq(0).text(n).hide();
//       else
    	  o.nextAll("div.invalid-feedback").eq(0).text(n).hide();
      return true;
    }
  }
  function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
    	 
        return false;
      } else {
        return true;
      }
  }
  if(valid) e.target.submit();
}
</script>
</section>
</body>
	
<%@include file="../footer.jsp"%>
</html>
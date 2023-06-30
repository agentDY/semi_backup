<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.UserVO" %>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<% UserVO user = (UserVO)request.getAttribute("userInfo"); %>

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

<style>
	@import url("/css/user_myP_upD_del.css");
</style>

<title>Insert title here</title>

</head>
<body>
<section id="body_css">
<img style="height: 163px;"src="../topimgs/mainimg1.png" id="topimgs">

	<% request.setAttribute("USER_ID", (String)session.getAttribute("id")); %>
	
	
 <div class="container" style="width: 700px; margin: 0 auto;"><br>
	<div><h3 class="updateH3" style="margin: 30px 0; text-align: left;">개인정보 수정</h3></div>
 
 
<form name="joinform" action="/user/EditInfo.lo" method="post"  class="needs-validation" novalidate >

  	 <div class="form-group">
  	 	<table>
  	 		<tr>
		        <th>아이디</th>
		        <td>
		        	<input style="padding-left:5px;" type="text" name="uid" id="uid" value=" ${id }" readonly>
		        </td>
			</tr>
        </table>
      </div>
  	<div class="form-group">
  		<table>
  			<tr>
		        <th>이름</th>
		        <td>
		        	<input type="text" id="USER_NAME" value="${name }" readonly>
		        </td>
			</tr>
        </table>
      </div>
 	<div class="form-group">
 		<table>
	 		<tr>
	        	<th>비밀번호</th>
	        	<td><input type="password" class="form-control" id="upw" name="upw" placeholder="숫자, 영어 대 소문자 5~16자리"  required>
	        	<div class="invalid-feedback">필수 정보입니다.</div></td>
	        </tr>
        </table>
      </div>
 	<div class="form-group">
 		<table>
        	<tr>
	        	<th>비밀번호 확인</th>
		        <td><input type="password" class="form-control" id="upw2" name="upw2" placeholder="숫자, 영어 대 소문자 5~16자리" required>
		        <div class="invalid-feedback">필수 정보입니다.</div></td>
			</tr>
        </table>
      </div>
	<div class="group">
		<table class="addrTable">
			<tr>
				<th>주소</th>
				<td style="padding: 0 6px 10px 10px;"><span style="font-size: 14px; color: #037F8C;">선택된 주소지 : ${addr }</span><br>
					<input type="text" id="sample6_postcode" name="USER_ADDR1" placeholder="우편번호">
					<button class="addrBtn" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">우편번호 찾기</button><br>
					<input type="text" id="sample6_address" name="USER_ADDR2" placeholder="주소"><br>
					<input type="text" id="sample6_detailAddress" name="USER_ADDR3" placeholder="상세주소"><br>
					<input type="text" id="sample6_extraAddress" name="USER_ADDR4" placeholder="참고항목">
				</td>
			</tr>
		</table>
	</div>
    <div class="group">
    	<table>
    		<tr>
		        <th>전화번호</th>
		        <td><input type="tel" class="form-control" id="USER_TEL" placeholder="(-)제외 번호" name="USER_TEL" value="${tel }" required>
		        	<div class="invalid-feedback">필수 정보입니다.</div>
		        </td>
			</tr> 
		</table>
    </div>
    <div class="group">
    	<table>
    		<tr>
	        	<th>이메일</th>
		        <td><input type="email" class="form-control" id="USER_EMAIL" placeholder="(@)포함 주소" name="USER_EMAIL" value="${email }" required>
		        	<div class="invalid-feedback">필수 정보입니다.</div>
		        </td>
			</tr>
        </table>
    </div><br><br>
     <div class="group" id="btnDiv">
     	<button class="updateBtn" type="reset">다시쓰기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="updateBtn" type="submit">수정하기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	<button class="updateBtn" onclick="location.href='/user/MyPageAction.lo';">돌아가기</button>

    	</div>
	</form>
	
</div>
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
  emailRegex = /^[-!#$%\s'*+./0-9=?A-Z^_a-z{|}~]+@[A-Za-z{|}~]+.[A-Za-z]+$/,
  idRegex = /^[0-9A-Za-z]+$/,
  telRegex = /^010\d{4}\d{4}$/,
  passwordRegex = /^[0-9A-Za-z]+$/,
  id = $( "#uid" ),
  name = $( "#USER_NAME" ),
  email = $( "#USER_EMAIL" ),
  password = $( "#upw" ),
  passwordre = $( "#upw2" ),
  tel = $( "#USER_TEL" );
  var valid = true;
  valid = valid && checkRegexp( email, emailRegex,"이메일 입력 형식이 잘못되었습니다." );
  valid = valid && checkRegexp( tel, telRegex,"전화번호 입력 형식이 잘못되었습니다." );
  valid = valid && checkMatch( password.val(), passwordre.val(), passwordre);
  valid = valid && checkRegexp( password, passwordRegex,"비밀번호 입력 형식이 잘못되었습니다." );
  valid = valid && checkLength( password, "비밀번호", 5, 16 );


  //비밀번호 매칭
function checkMatch(p, pre, passre){
	let pval = p;
	console.log(pval);
	let pvalre = pre;
	console.log(pvalre);
	let res = true;
	if(pval != pvalre){
		passre.nextAll("div.invalid-feedback").text("비밀번호가 일치하지 않습니다.").show();
		passre.nextAll("div.valid-feedback").hide();
	  	res = false;
  } else {
	  passre.nextAll("div.valid-feedback").text("비밀번호가 일치합니다.").show();
	  passre.nextAll("div.invalid-feedback").hide();
  }
  return res;
}
//정규식 일치 여부 확인
  function checkRegexp( o, regexp, n ) {
    let cnt = $("#"+o.attr('id')+" ~ div.invalid-feedback").length;
    console.log('o: ',o);
    console.log('cnt: ',cnt);
    console.log('o.val(): ',o.val());
    console.log('regexp.test( o.val() ): ',regexp.test( o.val() ));
    if ( !( regexp.test( o.val() ) ) ) {
      if(cnt == 0){
    	  o.closest("div.row").nextAll("div.invalid-feedback").eq(0).text(n).show();
      }
      else{
         o.nextAll("div.invalid-feedback").eq(0).text(n).show();
         o.nextAll("div.valid-feedback").eq(0).hide();
      }
      return false;
    } else {
      if(cnt == 0){
    	  o.closest("div.row").nextAll("div.invalid-feedback").eq(0).text(n).hide();
      }
      else{
    	  o.nextAll("div.invalid-feedback").eq(0).text(n).hide();
      }
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
<%@ include file="../footer.jsp" %>
</html>
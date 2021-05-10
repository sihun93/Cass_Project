<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<script type="text/javascript" src="/Cass/js/members_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link type="text/css" rel="stylesheet" href="../css/management.css">
<style>
@font-face{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');
    }
.Title{
font-family:'InkLipquid';
margin-top: 3%;
}
</style>
<script>
function postcode(){
	   new daum.Postcode({
		    oncomplete: function(data) {
		    	 document.getElementById('addrCode').value = data.zonecode;
		    	 document.getElementById("memberAddr1").value = data.roadAddress;
		         document.getElementById("memberAddr2").focus();
		    }
		}).open();	
}

function idCheck(){
	window.open("IdCheckForm.jsp", "IdCheckForm", "width=480, height=300");
}
</script>
</head>
<body>
<div id="center">
<form action="${CONTEXT_PATH}/member/frontController?action=memberInput" method="post">
<div class="Title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 회원 가입</a></div><br>
	<div class="inputForm">
		<h4 class="TitleText">아이디</h4> <input type="text" class="input" name="memberId" id="memberId" autofocus="autofocus" required="required" placeholder="아이디를 6자 이상 입력해주세요.">
		<input type="button" class="find" onclick="idCheck()" value="아이디 중복확인"><br>
		<h4 class="TitleText">비밀번호</h4><input type="password" class="input" name="memberPw" id="memberPw" required="required" placeholder="비밀번호를 6자 이상 입력해주세요."><br>
		<span id="memberPwMessage"></span>
		<h4 class="TitleText">비밀번호 확인</h4><input type="password" class="input" name="memberPwConfirm" id="memberPwConfirm" required="required" onblur="checkMemberPw()" placeholder="비밀번호 재확인">
			<input type="checkbox" name="memberPwShow" id="memberPwShow" 
				onclick="showMemberPw()">비밀번호 보이기
			<div id="memberPwConfirmMessage"></div><br>
		<h4 class="TitleText">우편번호</h4><input type="text" class="input" name="addrCode" size="7" id="addrCode" readonly="readonly" required="required" placeholder="우편번호">
		<input type="button" class="find" value="우편번호찾기" onclick="postcode()"><br>
	
		<h4 class="TitleText">주소</h4><input type="text" class="input" name="memberAddr1" id="memberAddr1" size="70" required="required" readonly="readonly" placeholder="도로명주소" ><br>
		<input type="text" class="input" name="memberAddr2" id="memberAddr2" size="70" placeholder="상세주소" required="required"><br>
		
		<h4 class="TitleText">이메일</h4><input type="text" class="input" name="email1" id="email1"  required="required" placeholder="이메일 아이디"> 
			<input type="text" class="input" name="email2" id="email2" required="required" readonly="readonly" placeholder="이메일 주소"> 
			<select name="emailDomain" class="find" id="emailDomain" onchange="changeEmailDomain()">
				<option value="none"> ==선택==</option>
				<option value="@naver.com">네이버</option>
				<option value="@gmail.com">구글</option>
				<option value="@nate.com">네이트</option>
				<option value="input">직접입력</option>
			</select>
			<br>
		
		<h4 class="TitleText">휴대폰</h4><input type="text" class="input" pattern="\d{4}-\d{4}" name="memberMobile" id="memberMobile" required="required" placeholder="ex)휴대폰 : 1111-1111 형식 입력">
		<br>
		
		<h4 class="TitleText">생년월일</h4><input type="text" class="input" pattern="\d{4}-\d{2}-\d{2}" name="memberBirth" id="memberBirth" required="required" placeholder="ex)yyyy-mm-dd 형식 입력">
		<br>
		<h4 class="TitleText">성별</h4> 
			남  <input type="radio" name="sex" id="sex" value="M" checked>
			여  <input type="radio" name="sex" id="sex" value="F" >
	</div>
	<div id="loginDiv">
			<input id="login" type="submit" value="회원가입" style="visibility: hidden;"><br>
	</div>
</form>
</div>
</body>
</html>
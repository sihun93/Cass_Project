<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 등록 페이지</title>
<script type="text/javascript" src="/Cass/js/business_input.js"></script>
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
}
</style>
<script>
function postcode(){
	   new daum.Postcode({
		    oncomplete: function(data) {
		    	 document.getElementById('addrCode').value = data.zonecode;
		    	 document.getElementById("businessAddr1").value = data.roadAddress;
		         document.getElementById("businessAddr2").focus();
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
<form action="${CONTEXT_PATH}/business/frontController?action=businessInput" method="post">
	<div class="Title"><a href="${CONTEXT_PATH}/welcome.jsp">Cass 사업자 등록</a></div><br>
	<div class="inputForm">
		<h4 class="TitleText">아이디</h4> <input type="text" class="input" name="memberId" id="memberId" autofocus="autofocus" required="required" placeholder="아이디를 영문 6자 이상 입력해주세요.">
		<input type="button" class="find" onclick="idCheck()" value="아이디 중복확인"><br>
		<h4 class="TitleText">비밀번호</h4><input type="password" class="input" name="businessPw" id="businessPw" required="required" placeholder="비밀번호를 6자 이상 입력해주세요."><br>
		<span id="businessPwMessage"></span>
		
		<h4 class="TitleText">비밀번호 확인</h4>
		<input type="password" class="input" name="businessPw2" id="businessPw2" required="required" placeholder="비밀번호 재확인입니다" onblur="bsPwCheck()">
		<input type="checkbox" name="businessPwShow" id="businessPwShow" 
				onclick="showBusinessPw()">비밀번호 보이기
		<div id="businessPwConfirmMessage"></div><br>
	
		<h4 class="TitleText">사업자번호</h4><input type="text" class="input" pattern="\d{3}-\d{2}-\d{5}" name="businessNum" id="businessNum" required="required" placeholder="ex) 000-00-00000"><br>
	
		<h4 class="TitleText">상호명</h4><input type="text" class="input" name="businessTitle" id="businessTitle" required="required" placeholder="상호명을 입력해주세요."><br>		
	
		<h4 class="TitleText">우편번호</h4><input type="text" class="input" name="addrCode" size="7" id="addrCode" readonly="readonly" required="required" placeholder="우편번호">
		<input type="button" class="find" value="우편번호찾기" onclick="postcode()"><br>
		
		<h4 class="TitleText">사업자 주소</h4>
		<input type="text" class="input" name="businessAddr1" id="businessAddr1" size="70" readonly="readonly" placeholder="도로명주소" ><br>
		<input type="text" class="input" name="businessAddr2" id="businessAddr2" size="70" placeholder="상세주소를 입력해주세요">
		
		<h4 class="TitleText">전화번호</h4>
		<input type="text" class="input" pattern="\d{3}-\d{4}-\d{4}" name="businessPhone" id="businessPhone" required="required" placeholder="ex)전화번호 : 010-1111-1111"><br>
		
		<h4 class="TitleText">사업자 홈페이지</h4>
		<input type="text" class="input" name="businessHomepage" id="businessHomepage" required="required" placeholder="홈페이지 메인 주소를 입력해주세요">
	</div>		
		<div id="loginDiv">
			<input id="login" type="submit" value="사업자 등록"><br>
		</div>
</form>
</div>
</body>
</html>
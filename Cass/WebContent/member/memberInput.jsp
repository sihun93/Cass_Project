<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원가입 페이지</title>
<script type="text/javascript" src="/Cass/js/members_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link type="text/css" rel="stylesheet" href="../css/management.css">

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
</script>
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
<hr>

<form action="${CONTEXT_PATH}/member/frontController?action=memberInput" method="post">
<table border="1">
	<tr>
		<th colspan="2" id="title">Cass 회원 가입</th>
	</tr>

	<tr>
		<td>아이디 </td>
		<td>
		<input type="text" name="memberId" id="memberId" autofocus="autofocus" required="required" placeholder="아이디를 입력해주세요">
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 </td>
		<td>
		<input type="password" name="memberPw" id="memberPw" required="required" placeholder="비밀번호를 입력해주세요">
		<span id="memberPwMessage"></span>
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 확인</td>
		<td>
		<input type="password" name="memberPwConfirm" id="memberPwConfirm" required="required" onblur="checkMemberPw()" placeholder="비밀번호 재확인입니다">
		<input type="checkbox" name="memberPwShow" id="memberPwShow" 
				onclick="showMemberPw()">비밀번호 보이기
		<div id="memberPwConfirmMessage"></div>
		</td>
	</tr>
	
	<tr>
		<td>우편번호 </td>
		<td>
		<input type="text" name="addrCode" size="7" id="addrCode" readonly="readonly" required="required">
		<input type="button" value="우편번호찾기" onclick="postcode()">
		</td>
	</tr>
	
	<tr>
		<td>주소 </td>
		<td>
		<input type="text" name="memberAddr1" id="memberAddr1" size="70" required="required" readonly="readonly" placeholder="도로명주소" >
		<input type="text" name="memberAddr2" id="memberAddr2" size="70" placeholder="상세주소" required="required">
		</td>
	</tr>
	
	
	<tr>
		<td>이메일 </td>
		<td>
			<input type="text" name="email1" id="email1"  required="required"> 
			<input type="text" name="email2" id="email2" required="required" readonly="readonly"> 
			<select name="emailDomain" id="emailDomain" onchange="changeEmailDomain()">
				<option value="none">==선택==</option>
				<option value="@naver.com">네이버</option>
				<option value="@gmail.com">구글</option>
				<option value="@nate.com">네이트</option>
				<option value="input">직접입력</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>휴대번호 </td>
		<td>010-<input type="text" pattern="\d{4}-\d{4}" name="memberMobile" id="memberMobile" required="required" placeholder="ex)1111-1111"></td>
	</tr>
	
	<tr>
		<td>생년월일 </td>
		<td><input type="text" pattern="\d{4}-\d{2}-\d{2}" name="memberBirth" id="memberBirth" required="required" placeholder="ex)yyyy-mm-dd 형식 입력"></td>
	</tr>
	
	<tr>
		<td>성별 </td>
		<td>
			남 : <input type="radio" name="sex" id="sex" value="M" checked>
			여 : <input type="radio" name="sex" id="sex" value="F" >
		</td>
	</tr>
	
	<tr>
		<th colspan="2">
			<input type="submit" value="회원가입">
			<input type="reset" value="가입취소">
		</th>
	</tr>
</table>
</form>
<hr>
</body>
</html>
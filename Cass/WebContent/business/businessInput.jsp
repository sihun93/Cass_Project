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
</script>

</head>
<body>
<a href="${CONTEXT_PATH }/welcome.jsp">[Cass Main]</a>
<hr>

<form action="${CONTEXT_PATH}/business/frontController?action=businessInput" method="post">
<table border="1">
	<tr>
		<th colspan="2" id="title">Cass 사업자 등록</th>
	</tr>
	<tr>
		<td>아이디 </td>
		<td>
		<input type="text" name="businessId" id="businessId" autofocus="autofocus" required="required" placeholder="아이디를 입력해주세요">
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 </td>
		<td>
		<input type="password" name="businessPw" id="businessPw" required="required" placeholder="비밀번호를 입력해주세요">
		<span id="businessPwMessage"></span>
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 확인</td>
		<td>
		<input type="password" name="businessPw2" id="businessPw2" required="required" placeholder="비밀번호 재확인입니다" onblur="bsPwCheck()">
		<input type="checkbox" name="businessPwShow" id="businessPwShow" 
				onclick="showBusinessPw()">비밀번호 보이기
		<div id="businessPwConfirmMessage"></div>
		</td>
	</tr>
	
	<tr>
		<td>사업자번호</td>
		<td>
			<input type="text" pattern="\d{3}-\d{2}-\d{5}" name="businessNum" id="businessNum" required="required" placeholder="ex)000-00-00000">
		</td>
	</tr>
	
	<tr>
		<td>상호명</td>
		<td>
			<input type="text" name="businessTitle" id="businessTitle" required="required" placeholder="상호명을 입력해주세요.">		
		</td>
	</tr>
	
	
	<tr>
		<td>우편번호 </td>
		<td>
		<input type="text" name="addrCode" size="7" id="addrCode" readonly="readonly">
		<input type="button" value="우편번호찾기" onclick="postcode()">
		</td>
	</tr>
	
	<tr>
		<td>사업자 주소 </td>
		<td>
		<input type="text" name="businessAddr1" id="businessAddr1" size="70" readonly="readonly" placeholder="도로명주소" >
		<input type="text" name="businessAddr2" id="businessAddr2" size="70" placeholder="상세주소를 입력해주세요">
		</td>
	</tr>
	
	<tr>
		<td>전화번호 </td>
		<td>
			<input type="text" pattern="\d{3}-\d{4}-\d{4}" name="businessPhone" id="businessPhone" required="required" placeholder="ex)010-1111-1111">
		</td>
	</tr>

			<tr>
				<td>사업자 홈페이지 </td>
				<td>
					<input type="text" size="70" name="businessHomepage" id="businessHomepage" required="required" placeholder="홈페이지 메인 주소를 입력해주세요">
				</td>
			</tr>
			<tr>
			<th colspan="2">
			<input type="submit" value="사업자등록">
			<input type="reset" value="등록취소">
			</th>
		</tr>
</table>
</form>
<hr>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="../css/checkForm.css">
</head>
<body>
	<div id="ckMain">
	<h2>아이디 중복 체크</h2><br><br>
		<form method="post" action="idCheckOk.jsp" onsubmit="return blankCheck(this)">
			<b>아이디 :</b> <input type="text" name="memberId" id="memberId" class="idbox">
			<input type="submit" value="중복확인" class="bt">
			<input id="cbt" type="button" value="취소" onclick="window.close()" class="bt">
		</form>
	</div>		
	<script>
	var id = window.opener.document.getElementById("memberId").value; 
	document.getElementById("memberId").value = id;
	function blankCheck(f){
		
		var memberId = f.memberId.value;
		var engPattern = /[^a-zA-Z0-9]/gi;
		memberId = memberId.trim();
		if(memberId.length<6 || memberId.length>30){
			alert("아이디는 6~30자 사이로 입력해주십시오.");
			return false;
		}
		if (engPattern.test(memberId)) {
			alert("아이디는 숫자 또는 영어만 입력 가능합니다.");
			return false;
		}
		return true;
	}
	</script>
</body>
</html>
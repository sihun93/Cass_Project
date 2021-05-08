<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
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
		var memberId=f.memberId.value;
		memberId=memberId.trim();
		if(memberId.length<6){
			alert("아이디는 최소 6자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="../css/businessFind.css">
<style type="text/css">
input:focus {
	outline: none;
}
</style>
</head>
<body>
<form action="${CONTEXT_PATH }/business/frontController?action=businessPwFind" method="post">
	<a href="${CONTEXT_PATH }/welcome.jsp"><h1 id="h1" align="center">CASS 사업자 회원 비밀번호 찾기</h1></a>
	<div class="totalForm">
		<p class="text">아이디</p>
		<input type="text" name="memberId" id="memberId" required="required" placeholder="회원 아이디">
		<p class="text">휴대폰</p>
		<input type="text" pattern="\d{3}-\d{4}-\d{4}" name="businessPhone" id="businessPhone" placeholder="ex)010-1111-2222 형식 입력" required="required"><br>
		<input class="submit" type="submit" value="찾기">
	</div>	
</form>
</body>
</html>
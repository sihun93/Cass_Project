<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="../css/businessFind.css">
</head>
<body>
<form action="${CONTEXT_PATH }/business/frontController?action=memberIdFind" method="post">
	<a href="${CONTEXT_PATH }/welcome.jsp"><h1 id="h1id" align="center">CASS 사업자 회원 아이디 찾기</h1></a>
	<div class="totalForm">
		<p class="text">사업자번호</p>
		<input type="text" pattern="\d{3}-\d{2}-\d{5}"
			name="businessNum" id="businessNum" required="required"
			placeholder="ex)000-00-00000">
		<p class="text">전화번호</p>
		<input type="text" pattern="\d{3}-\d{4}-\d{4}"
			name="businessPhone" id="businessPhone" required="required"
			placeholder="ex)010-1111-1111"><br>
		<input class="submit" type="submit" value="찾기"> <br>
	</div>
</form>	
</body>
</html>
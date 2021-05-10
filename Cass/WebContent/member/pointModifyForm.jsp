<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<style type="text/css">
html, body {
	height: 0;
}

input:focus {
	outline: none;
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="container">
		<div class="point_update_div">		
		<div class="point_update_form">	
			<form action="${CONTEXT_PATH}/member/frontController?action=pointModify" method="post">
			<div id="title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 포인트를 변경할 회원의 아이디</a></div>
			<input type="text" id="memberId" class="id" name="memberId" placeholder="아이디 입력">	<br>
			<input type="submit" value="확인">
			</form>
			</div>
		</div>
		</div>
	</div>
</body>
</html>
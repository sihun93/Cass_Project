<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 로그인 페이지입니다.</title>
<link type="text/css" rel="stylesheet" href="css/management.css">
</head>
<body>
<br>
<table border="1">
<tr>
<th colspan="2" id="title">회원님의 해당하는 로그인 서비스를 선택해주세요.</th>
</tr>
<tr>
	<th><a href="${CONTEXT_PATH }/member/frontController?action=loginForm">일반 회원님 로그인</a></th>
	<th><a href="${CONTEXT_PATH }/business/frontController?action=businessLoginForm">사업자 회원님 로그인</a></th>
</tr>
</table>
</body>
</html>
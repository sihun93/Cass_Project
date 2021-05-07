<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원님의 아이디/비밀번호 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="css/management.css">
</head>
<body>
<br>
<table border="1">
<tr>
<th colspan="2" id="title">회원님의 해당하는 서비스를 선택해주세요.</th>
</tr>
<tr>
	<th><a href="${CONTEXT_PATH }/member/frontController?action=findForm">일반회원 아이디/비밀번호 찾기</a></th>
	<th><a href="${CONTEXT_PATH }/business/frontController?action=businessFindForm">사업자 아이디/비밀번호 찾기</a></th>
</tr>
</table>
</body>
</html>
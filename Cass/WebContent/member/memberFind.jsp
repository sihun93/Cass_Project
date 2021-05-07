<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
<table border="1">
<tr>
<th colspan="2" id="title">원하시는 서비스를 선택해주세요.</th>
</tr>
<tr>
<th><a href="${CONTEXT_PATH }/member/frontController?action=findIdForm">아이디 찾기</a></th>
<th><a href="${CONTEXT_PATH }/member/frontController?action=findPwForm">비밀번호 찾기</a></th>
</tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 찾기 페이지</title>
</head>
<body>
<h3>원하시는 서비스를 선택해주세요.</h3>
<a href="${CONTEXT_PATH }/member/frontController?action=findIdForm">아이디 찾기</a>
<a href="${CONTEXT_PATH }/member/frontController?action=findPwForm">비밀번호 찾기</a>
</body>
</html>
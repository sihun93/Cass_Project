<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<form action="${CONTEXT_PATH}/member/frontController?action=login" method="post">
<table border="1">
	<tr>
		<th colspan="2" id="title">Cass 일반 회원 로그인</th>
	</tr>
	<tr>
		<td>아이디 : </td>
		<td><input type="text" name="memberId" id="memberId" required="required" placeholder="아이디를 입력하세요."></td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td><input type="password" name="memberPw" id="memberPw" required="required" placeholder="비밀번호를 입력하세요."></td>
	</tr>
	<tr>
		<th colspan="2" >
		<input type="submit" value="로그인" name="login">
		<input type="reset" value="초기화">
		</th>
	</tr>
</table>
</form>

<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
</body>
</html>
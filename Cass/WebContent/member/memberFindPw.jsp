<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원 정보 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
<hr>
	<form action="${CONTEXT_PATH}/member/frontController?action=memberPwFind" method="post">
		<table border="1">
			<tr>
				<th colspan="2" id="title">Cass 회원 비밀번호 찾기</th>
			</tr>
			<tr>
				<td colspan="2">비밀번호를 찾기위해 회원님의 해당 정보를 입력해 주세요.</td>
			</tr>

			<tr>
				<td>아이디</td>
				<td><input type="text" name="memberId" id="memberId" required="required">
				</td>
			</tr>
			
			<tr>
				<td>휴대폰</td>
				<td>
					010-<input type="text" pattern="\d{4}-\d{4}" name="memberMobile" id="memberMobile" placeholder="ex)1111-2222 형식 입력" required="required">
				</td>
			</tr>

			<tr>
				<th colspan="2">
				<input type="submit" value="찾기"> 
				<input type="reset" value="취소">
				</th>
			</tr>
		</table>
	</form>
	<hr>
</body>
</html>
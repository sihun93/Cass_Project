<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
	<form action="${CONTEXT_PATH}/member/frontController?action=pointModify" method="post">
		<table border="1">
			<tr>
				<td colspan="2" id="title">포인트를 변경할 회원의 아이디를 입력하세요.</td>
			</tr>
			<tr>
				<td>아이디 : <input type="text" id="memberId" name="memberId" placeholder="아이디 입력"></td>
			</tr>
			<tr>
				<th>
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>
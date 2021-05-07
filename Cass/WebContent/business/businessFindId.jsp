<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 사업자 회원 정보 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<hr>
<a href="${CONTEXT_PATH }/welcome.jsp">[Cass Main]</a>
<form action="${CONTEXT_PATH }/business/frontController?action=memberIdFind" method="post">
		<table border="1">
			<tr>
				<th colspan="2" id="title">Cass 사업자 회원 아이디 찾기</th>
			</tr>
			<tr>
				<td colspan="2">아이디를 찾기위해 사업자 회원님의 해당 정보를 입력해 주세요.</td>
			</tr>
			<tr>
				<td>사업자번호</td>
				<td><input type="text" pattern="\d{3}-\d{2}-\d{5}"
					name="businessNum" id="businessNum" required="required"
					placeholder="ex)000-00-00000"></td>
			</tr>

			<tr>
				<td>전화번호</td>
				<td><input type="text" pattern="\d{3}-\d{4}-\d{4}"
					name="businessPhone" id="businessPhone" required="required"
					placeholder="ex)010-1111-1111"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="찾기"> <input
					type="reset" value="취소"></th>
			</tr>
		</table>
	</form>	
	<br>
	<hr>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원 정보 찾기 페이지</title>
</head>
<body>
<h3>회원 아이디 찾기</h3>
<hr>
<form action="#" method="post">
		<table>
			<caption>아이디를 찾는 곳입니다.</caption>
			<tr>
				<td colspan="2">아이디를 찾기위해 회원님의 해당 정보를 입력해 주세요.</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
					<input type="text" pattern="\d{4}-\d{2}-\d{2}" name="memberBirth" id="memberBirth" required="required" placeholder="ex)yyyy-mm-dd 형식 입력">
				</td>
			</tr>

			<tr>
				<td>휴대폰</td>
				<td>
					010-<input type="text" pattern="\d{4}-\d{4}" id="memberMobile" name="memberMobile" placeholder="ex)1111-2222 형식 입력" required="required">
				</td>
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
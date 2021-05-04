<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 사업자 회원 정보 찾기 페이지</title>
</head>
<body>
<h3>사업자 회원 비밀번호 찾기</h3>
<hr>
<form action="#" method="post">
		<table>
			<caption>비밀번호를 찾는 곳입니다.</caption>

			<tr>
				<td colspan="2">비밀번호를 찾기위해 사업자 회원님의 해당 정보를 입력해 주세요.</td>
			</tr>

			<tr>
				<td>아이디</td>
				<td><input type="text" name="businessId" id="businessId" required="required">
				</td>
			</tr>
			
			<tr>
				<td>휴대폰</td>
				<td>
				<input type="text" pattern="\d{3}-\d{4}-\d{4}" name="memberMobile" id="memberMobile" placeholder="ex)010-1111-2222 형식 입력" required="required">
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
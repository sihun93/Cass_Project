<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 전용 로그인 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
.bTitle{
	font-family: 'Lobster', cursive;
}
</style>
</head>
<body>
<div id="wrapper">
      <jsp:include page="/inc/header_menu.jsp" />
<div class="bTitle"><h1>CASS</h1></div>    
<form action="${CONTEXT_PATH}/business/frontController?action=bsLogin" method="post">
		<table border="1">
			<tr>
				<th colspan="2" id="title">Cass 사업자 회원 로그인</th>
			</tr>
			<tr>
				<th>아이디 </th>
				<td><input type="text" name="memberId" id="memberId"
					required="required" placeholder="아이디를 입력하세요."></td>
			</tr>
			<tr>
				<th>비밀번호 :</th>
				<td><input type="password" name="businessPw" id="businessPw"
					required="required" placeholder="비밀번호를 입력하세요."></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="로그인" name="login" class="formbt">
			</tr>
		</table>
	</form>
	<jsp:include page="/inc/footer_menu.jsp"/>
</div> 
</body>
</html>
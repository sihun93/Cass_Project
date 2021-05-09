<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/login.css">
<style type="text/css">
html, body {
	height: 0;
}

#memberId, #memberPw{
	padding: 10px 10px 10px 20px;
	width: 20%;
	margin-top: 1%;
	border-radius: 20px;
}
#loginDiv{
	margin-top: 1%;
}
#login{
padding: 10px 10px 10px 20px;
	width: 22%;
	border-radius: 20px;
}
h1{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
	font-size: 3em;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');}
</style>
</head>
<body>
<center>
<form action="${CONTEXT_PATH}/member/frontController?action=login" method="post">
	<div style="margin-top: 10%;">
		<a href="${CONTEXT_PATH}/welcome.jsp"><h1>CASS 일반 회원 로그인</h1></a><br>
		<input type="text" name="memberId" id="memberId" required="required" placeholder="아이디"><br>
		<input type="password" name="memberPw" id="memberPw" required="required" placeholder="비밀번호">
		<br>
		<div id="loginDiv">
			<input id="login" type="submit" value="로그인" name="login"><br>
		</div>
	</div>
</form>
</center>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원 정보 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
<style>
@font-face{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');
    }
.Title{
	font-family:'InkLipquid';
	margin-left: -6%;
    margin-bottom: 5%;
}

#findDiv {
    margin: 12% 40%;
    width: 40%;
}
</style>
</head>
<body>
<div id="findDiv">
	<form action="${CONTEXT_PATH}/member/frontController?action=memberPwFind" method="post">
		<div class="Title"><a href="${CONTEXT_PATH}/welcome.jsp">Cass 회원 비밀번호 찾기</a></div>
		<input type="text" class="input" name="memberId" id="memberId" required="required" placeholder="아이디"><br>
		<input type="text" class="input" pattern="\d{4}-\d{4}" id="memberMobile" name="memberMobile" placeholder="ex)휴대폰 : 1111-2222 형식 입력" required="required">
			<br>
		<div id="loginDiv">
			<input class="memfind" type="submit" value="찾기"><br>
		</div>
	</form>
</div>
</body>
</html>
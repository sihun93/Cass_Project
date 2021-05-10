<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
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
.text{
		font-family:'InkLipquid';
		font-size: 20px;
}

#findDiv {
    margin: 12% 43%;
    width: 40%;
}
</style>
</head>
<body>
<div id="findDiv">
	<form action="${CONTEXT_PATH}/member/frontController?action=memberIdFind" method="post">
	<div class="Title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 회원 아이디 찾기</a></div>
	<div class="ip">
		<p class="text">생년월일</p>
		<input type="text" class="input" pattern="\d{4}-\d{2}-\d{2}" name="memberBirth" id="memberBirth" required="required" placeholder="ex)생년월일 : yyyy-mm-dd"><br>
		<p class="text">휴대폰</p>
		<input type="text" class="input" pattern="\d{4}-\d{4}" id="memberMobile" name="memberMobile" placeholder="ex)휴대폰 : 1111-2222 형식 입력" required="required">
	
			<br>
		<div id="loginDiv">
			<input class="memfind" type="submit" value="찾기"><br>
		</div>
	</div>	
	</form>
</div>
</body>
</html>
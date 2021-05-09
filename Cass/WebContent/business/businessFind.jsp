<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 회원 정보 찾기 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
<link type="text/css" rel="stylesheet" href="../css/welcome.css">
<style>
@font-face{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');
    }
.bTitle{
	font-family:'InkLipquid';
}
</style>
</head>
<body>
<div id="wrapper">
      <jsp:include page="/inc/header_menu2.jsp" />
</div>
<div class="bTitle">
<a href="${CONTEXT_PATH }/welcome.jsp">
CASS
</a>
</div>
<table class="cl">

<tr>
<th><button class="formbt" onclick="location.href='${CONTEXT_PATH }/business/frontController?action=businessFindIdForm'"><b>사업자 회원님<br>아이디 찾기</b></button></th>
<th><button class="formbt" onclick="location.href='${CONTEXT_PATH }/business/frontController?action=businessFindPwForm'"><b>사업자 회원님<br>비밀번호 찾기</b></button></th>
</tr>
</table>
<div id="wrapper">
	<jsp:include page="/inc/footer_menu.jsp"/>
</div> 
</body>
</html>
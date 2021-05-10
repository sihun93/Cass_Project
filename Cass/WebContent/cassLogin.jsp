<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/management.css">
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
<div id="cassTitle">
<div class="bTitle">
<a href="${CONTEXT_PATH }/welcome.jsp">
CASS
</a>
</div>
</div>
<table class="cl">
<tr>
	<th><button class="formbt" onclick="location.href='${CONTEXT_PATH }/member/frontController?action=loginForm'"><b>일반 회원님<br> 로그인</b></button></th>
	<th><button class="formbt" onclick="location.href='${CONTEXT_PATH }/business/frontController?action=businessLoginForm'"><b>사업자 회원님<br> 로그인</b></button></th>
</tr>
</table>
<div id="wrapper">
	<jsp:include page="/inc/footer_menu.jsp"/>
</div> 
</body>
</html>
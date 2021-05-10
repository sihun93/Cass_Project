<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<%@ page import="com.work.model.dto.MessageEntity" %>    
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
.msgTitle{
	font-family:'InkLipquid';
}
</style>
</head>
<body>
<div id="wrapper">
      <jsp:include page="/inc/header_menu2.jsp" />
</div>
	<div class="msgTitle" align="center" style="margin-left: 0; margin-top: 5%;">
		<a href="${CONTEXT_PATH}/welcome.jsp">Cass Error Message</a>
	</div><br>
<div  align="center">
	<h4 class="msgTitle">${requestScope.messageEntity.message}</h4>
	<h4 class="msgTitle">${messageEntity.linkTitle}</h4>
	<input type="button" class="input" onclick="location.href='${messageEntity.url}'" value="로그인 하러 가기">
</div>
<div id="wrapper">
	<jsp:include page="/inc/footer_menu.jsp"/>
</div> 
</body>
</html>
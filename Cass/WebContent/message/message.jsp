<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<%@ page import="com.work.model.dto.MessageEntity" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답결과 페이지</title>
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
<div id="findDiv" align="center">
<div class="msgTitle"><a href="${CONTEXT_PATH}/welcome.jsp">Cass Error Message</a></div><br>
<h4 class="msgTitle">응답처리결과</h4>
<h4 class="msgTitle">${requestScope.messageEntity.message}</h4>
<input type="button" class="input" onclick="location.href='${messageEntity.url}'" value="${messageEntity.linkTitle}">
</div>
<div id="wrapper">
	<jsp:include page="/inc/footer_menu.jsp"/>
</div> 
</body>
</html>
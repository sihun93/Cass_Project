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
</head>
<body>

<table border="1" class="msg">
<tr><th>응답처리결과</th></tr>
<tr><th>${requestScope.messageEntity.message}</th></tr>
<tr><th><a href="${messageEntity.url}">${messageEntity.linkTitle}</a></th></tr>
</table>
</body>
</html>
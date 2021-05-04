<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<%@ page import="com.work.model.dto.MessageEntity" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답결과 페이지</title>
</head>
<body>


<h3>응답처리결과</h3>
<h3>${requestScope.messageEntity.message}</h3>
<h3><a href="${messageEntity.url}">${messageEntity.linkTitle}</a></h3>

</body>
</html>
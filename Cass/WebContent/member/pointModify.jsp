<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.work.model.dto.MemberDto" %> 
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<style type="text/css">
html, body {
	height: 0;
}

input:focus {
	outline: none;
}
</style>
</head>
<body>
   <c:choose>
      <c:when test="${empty dto }">
		         <jsp:setProperty property="message" name="messageEntity" value="0" />
         <jsp:setProperty property="linkTitle" name="messageEntity"
            value="로그인" />
         <jsp:setProperty property="url" name="messageEntity"
            value="/cass/member/loginForm" />

         <c:set var="messageEntity" value="${messageEntity}" scope="request" />
         <jsp:forward page="/message/message.jsp" />
      </c:when>
   </c:choose>
<div id="wrapper">
		<div id="container">
		<div class="point_update_div">		
		<div class="point_update_form">	
			<form action="${CONTEXT_PATH}/member/frontController?action=pointUpdate" method="post">
			<div id="title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 회원 포인트 수정</a></div>
			<div class="point_up_th">아이디</div>
			<input type="text" name="memberId" class="point_up_input"
					value='${dto.memberId }' autofocus="autofocus" readonly="readonly">	<br>
			<div class="point_up_th">포인트</div>
			<input type="text" pattern="[0-9]+" value='${dto.point }'
					name="point" class="point_up_input"><br>
			<input type="submit" value="확인">
			</form>
			</div>
		</div>	
		</div>
	</div>
</body>
</html>
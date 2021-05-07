<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.work.model.dto.MemberDto" %> 
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자전용 - 일반회원의 포인트를 수정하는 페이지</title>
<link type="text/css" rel="stylesheet" href="../css/management.css">
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
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
<hr>
<form action="${CONTEXT_PATH}/member/frontController?action=pointUpdate" method="post">
		<table border="1">
			<tr>
				<th colspan="2" id="title">관리자 전용 - 회원 포인트 수정</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="memberId" id="memberId"
					value='${dto.memberId }' autofocus="autofocus" readonly="readonly">
				</td>
			</tr>

			<tr>
				<td>포인트</td>
				<td><input type="text" pattern="[0-9]+" value='${dto.point }'
					name="point" id="point"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="포인트수정"> <input
					type="reset" value="취소"></th>
			</tr>
		</table>
	</form>
</body>
</html>
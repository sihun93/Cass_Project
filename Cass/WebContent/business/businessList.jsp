<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.work.model.dto.BusinessMemberDto"%>    
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
</style>
<script>
function businessDelete(memberId){

	if(confirm("정말 탈퇴하시겠습니까?")){
		document.businessDeleteForm.memberId.value = memberId;
		document.businessDeleteForm.submit();
	}
}
</script>
</head>
<body>
<form name="businessDeleteForm" id="businessDeleteForm" action="${CONTEXT_PATH}/business/frontController?action=businessDelete" method="post">
<input type="hidden" name="memberId" value="">
<input type="hidden" name="gubun" value="info">
</form>
<div id="title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 사업자 회원 전체 조회</a></div>
<div class="list_table">
<table border="1">
	<tr>	
		<th>아이디</th>
		<th>비밀번호</th>
		<th>사업자 번호</th>
		<th>상호명</th>
		<th>사업자 주소</th>
		<th>전화번호</th>
		<th>사업자 홈페이지 주소</th>
		<th>사업자 회원 삭제</th>
	</tr>
	
	<c:forEach var="dto" items="${list}">
		<tr>	
			<td>${dto.memberId}</td>
			<td>${dto.businessPw}</td>
			<td>${dto.businessNum}</td>
			<td>${dto.businessTitle}</td>
			<td>${dto.businessAddr}</td>
			<td>${dto.businessPhone}</td>
			<td>${dto.businessHomepage}</td>
			<td><button onclick="javascript:businessDelete('${dto.memberId}');">회원탈퇴</button></td>
		</tr>
	</c:forEach>	
</table>
</div>

</body>
</html>
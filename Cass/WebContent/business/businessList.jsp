<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 리스트 페이지</title>
</head>
<body>
<h3> 관리자 전용 - 사업자 회원 전체 조회</h3>
<hr>

<table>
	<tr>	
		<th>아이디</th>
		<th>비밀번호</th>
		<th>사업자 번호</th>
		<th>상호명</th>
		<th>사업자 주소</th>
		<th>전화번호</th>
		<th>사업자 홈페이지 주소</th>
	</tr>
	
	<c:forEach var="bdto" items="${list}">
		<tr>	
			<td>${bdto.businessId}</td>
			<td>${bdto.businessPw}</td>
			<td>${bdto.businessNum}</td>
			<td>${bdto.businessTitle}</td>
			<td>${bdto.businessAddr}</td>
			<td>${bdto.businessPhone}</td>
			<td>${bdto.businessHomepage}</td>
			<td><a href="${initParam.CONTEXT_PATH}/business/businessController?action=businessDelete&businessId=${bdto.businessId}" title="${bdto.businessId} 클릭 회원 탈퇴">탈퇴</a></td>
		</tr>
	</c:forEach>	
</table>

</body>
</html>
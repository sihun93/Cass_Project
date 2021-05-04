<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.work.model.dto.MemberDto" %> 
<%@ page import="java.util.ArrayList" %>    
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원 리스트 페이지</title>
</head>
<body>
<h3>관리자전용 - 회원 전체 조회</h3>
<hr>

<table>
	<tr>	
		<th>아이디</th>
		<th>비밀번호</th>
		<th>주소</th>
		<th>이메일</th>
		<th>휴대번호</th>
		<th>생일</th>
		<th>포인트</th>
		<th>등급</th>
		<th>성별</th>
	</tr>
	
	<c:forEach var="dto" items="${list}">
		<tr>	
			<td>${dto.memberId}</td>
			<td>${dto.memberPw}</td>
			<td>${dto.memberAddr}</td>
			<td>${dto.memberEmail}</td>
			<td>${dto.memberMobile}</td>
			<td>${dto.memberBirth}</td>
			<td>${dto.point}</td>
			<td>${dto.grade}</td>
			<td>${dto.sex}</td>
		</tr>
	</c:forEach>	
</table>

</body>
</html>
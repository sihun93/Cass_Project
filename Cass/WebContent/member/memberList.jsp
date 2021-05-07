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
<link type="text/css" rel="stylesheet" href="../css/management.css">
<script>
function memberDelete(memberId){

	if(confirm("정말 탈퇴하시겠습니까?")){
		document.memberDeleteForm.memberId.value = memberId;
		document.memberDeleteForm.submit();
	}
}

function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
</script>
</head>
<body>
<a href="${CONTEXT_PATH}/welcome.jsp">[Cass Main]</a>
<hr>
<form name="memberDeleteForm" id="memberDeleteForm" action="${CONTEXT_PATH}/member/frontController?action=memberDelete" method="post">
<input type="hidden" name="memberId" value="">
<input type="hidden" name="gubun" value="list">
</form>
<table border="1">
	<tr>
		<th colspan="10" id="title">관리자전용 - 회원 전체 조회</th>
	</tr>
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
		<th>회원 삭제</th>
	</tr>
	
	<c:forEach var="dto" items="${list}">
		<tr>	
			<td>${dto.memberId }</td>
			<td>${dto.memberPw}</td>
			<td>${dto.memberAddr}</td>
			<td>${dto.memberEmail}</td>
			<td>${dto.memberMobile}</td>
			<td>${dto.memberBirth}</td>
			<td align="right"><fmt:formatNumber value="${dto.point}" pattern="#,###" /></td>
			<td>${dto.grade}</td>
			<td>${dto.sex}</td>
			<td>
			<button onclick="javascript:memberDelete('${dto.memberId}');">회원삭제</button>
			</td>
		</tr>
	</c:forEach>	
</table>

</body>
</html>
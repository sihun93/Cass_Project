<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.work.model.dto.MemberDto"%>
<%@page import="com.work.model.dto.MessageEntity"%>    
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 회원 내 정보 조회 페이지</title>
<script type="text/javascript" src="/Cass/js/members_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
function postcode(){
	   new daum.Postcode({
		    oncomplete: function(data) {
		    	 document.getElementById('addrCode').value = data.zonecode;
		    	 document.getElementById("memberAddr1").value = data.roadAddress;
		         document.getElementById("memberAddr2").focus();
		    }
		}).open();	
}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty memberId or empty grade }">
			<jsp:setProperty property="message" name="messageEntity" value="0" />
			<jsp:setProperty property="linkTitle" name="messageEntity"
				value="로그인" />
			<jsp:setProperty property="url" name="messageEntity"
				value="/cass/member/loginForm" />

			<c:set var="messageEntity" value="${messageEntity}" scope="request" />
			<jsp:forward page="/message/message.jsp" />
		</c:when>
	</c:choose>
<h3>회원님의 정보 입니다</h3>
<hr>
<form action="${CONTEXT_PATH}/member/frontController?action=myInfoUpdateSave" method="post">
	<table>
	
	<tr>
		<td>아이디 </td>
		<td>
		<input type="text" name="memberId" id="memberId" value='${dto.memberId }' autofocus="autofocus" readonly="readonly">
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 </td>
		<td>
		<input type="password" name="memberPw" id="memberPw" value='${dto.memberPw }' required="required" placeholder="비밀번호를 입력해주세요">
		<span id="memberPwMessage"></span>
		</td>
	</tr>
	
	<tr>
		<td>비밀번호 확인</td>
		<td>
		<input type="password" name="memberPwConfirm" id="memberPwConfirm" value='${dto.memberPw }' required="required" onblur="checkMemberPw()" placeholder="비밀번호 재확인입니다">
		<input type="checkbox" name="memberPwShow" id="memberPwShow" onclick="showMemberPw()">비밀번호 보이기
		<div id="memberPwConfirmMessage"></div>
		</td>
	</tr>
	<tr>
		<td>우편번호 </td>
		<td>
		<input type="text" name="addrCode" value='' size="7"  id="addrCode" readonly="readonly">
		<input type="button" value="우편번호찾기" onclick="postcode()">
		</td>
	</tr>
	<tr>
		<td>주소 </td>
		<td>
		<input type="text" name="memberAddr1" id="memberAddr1" value="" size="70" readonly="readonly" placeholder="도로명주소" >
		<input type="text" name="memberAddr2" id="memberAddr2" value="" size="70" placeholder="상세주소">
		</td>
	</tr>
	
	<tr>
		<td>휴대번호 </td>
		<td>010-<input type="text" pattern="\d{4}-\d{4}" value='${dto.memberMobile }' name="memberMobile" id="memberMobile" required="required" placeholder="ex)1111-1111"></td>
	</tr>
			<tr>
				<td>이메일 </td>
				<td><input type="email" value='${dto.memberEmail }' name="memberEmail" id="memberEmail" required="required"></td>
			</tr>
			<tr>
				<td>생년월일 </td>
				<td><input type="text" value='${dto.memberBirth }' name="memberBirth" id="memberBirth" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td>포인트</td>
				<td><input type="text" value='${dto.point }' name="point" id="point" readonly="readonly"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="text" value='${dto.grade }' name="grade" id="grade" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td>성별 </td>
				<td>
					<input type="text" value='${dto.sex }' readonly="readonly">
				</td>
			</tr>
			<tr>
			<th colspan="2">
			<input type="submit" value="회원정보수정">
			<input type="reset" value="가입취소">
			</th>
		</tr>
	</table>
</form>
<br>
</body>
</html>
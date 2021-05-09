<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.work.model.dto.BusinessMemberDto"%>
<%@page import="com.work.model.dto.MessageEntity"%>
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 사업자 내 정보 조회 페이지</title>
<script type="text/javascript" src="/Cass/js/business_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<style type="text/css">
html, body {
	height: 0;
}
</style>
<script>
function postcode(){
	   new daum.Postcode({
		    oncomplete: function(data) {
		    	 document.getElementById('addrCode').value = data.zonecode;
		    	 document.getElementById("businessAddr1").value = data.roadAddress;
		         document.getElementById("businessAddr2").focus();
		    }
		}).open();	
}

function businessDelete(memberId){

	if(confirm("정말 탈퇴하시겠습니까?")){
		document.businessDeleteForm.memberId.value = memberId;
		document.businessDeleteForm.submit();
	}
}
</script>
<% 
	String businessAddr = ((BusinessMemberDto)session.getAttribute("dto")).getBusinessAddr();
	String[] addrSplit = new String[3];
	String[] bsp = businessAddr.split("/");
	if(bsp.length == 2){
		addrSplit[0] = bsp[0];
		addrSplit[1] = bsp[1];
		addrSplit[2] = "없음";
	}else{
		addrSplit = bsp;
	}
%>
</head>
<body>
<form name="businessDeleteForm" id="businessDeleteForm" action="${CONTEXT_PATH}/business/frontController?action=businessDelete" method="post">
<input type="hidden" name="memberId" value="">
<input type="hidden" name="gubun" value="info">
</form>
<div id="title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 내정보 조회</a></div>
<div class="myInfo">
<form action="${CONTEXT_PATH}/business/frontController?action=businessInfoUpdate" method="post">
	<table border="1">
	<tr>
		<th>아이디 </th>
		<td>
		<input type="text" name="memberId" id="memberId" value="${dto.memberId }" autofocus="autofocus" readonly="readonly" >
		</td>
	</tr>
	
	<tr>
		<th>비밀번호 </th>
		<td>
		<input type="password" name="businessPw" id="businessPw" value="${dto.businessPw }" required="required" placeholder="비밀번호를 입력해주세요">
		<span id="businessPwMessage"></span>
		</td>
	</tr>
	
	<tr>
		<th>비밀번호 확인</th>
		<td>
		<input type="password" name="businessPw2" id="businessPw2" value="${dto.businessPw }" required="required" placeholder="비밀번호 재확인입니다" onblur="bsPwCheck()">
		<input type="checkbox" name="businessPwShow" id="businessPwShow" onclick="showBusinessPw()">비밀번호 보이기
		<div id="businessPwConfirmMessage"></div>
		</td>
	</tr>
	
	<tr>
		<th>사업자번호</th>
		<td>
			<input type="text" pattern="\d{3}-\d{2}-\d{5}" value="${dto.businessNum }" name="businessNum" id="businessNum" readonly="readonly">
		</td>
	</tr>
	
	<tr>
		<th>상호명</th>
		<td>
			<input type="text" name="businessTitle" id="businessTitle" value="${dto.businessTitle }" required="required" placeholder="상호명을 입력해주세요.">		
		</td>
	</tr>
	
	
	<tr>
		<th>우편번호 </th>
		<td>
		<input type="text" name="addrCode" size="7" id="addrCode" value="<%=addrSplit[0]%>" readonly="readonly">
		<input type="button" value="우편번호찾기" onclick="postcode()" style="margin-left: 6%; width: 200px;">
		</td>
	</tr>
	
	<tr>
		<th>사업자 주소 </th>
		<td>
		<input type="text" name="businessAddr1" id="businessAddr1" value="<%=addrSplit[1]%>" size="70" readonly="readonly" placeholder="도로명주소" >
		<input type="text" name="businessAddr2" id="businessAddr2" value="<%=addrSplit[2]%>" size="70" placeholder="상세주소를 입력해주세요">
		</td>
	</tr>
	
	<tr>
		<th>휴대번호 </th>
		<td>
			<input style="text-align: left;" type="text" pattern="\d{3}-\d{4}-\d{4}" value="${dto.businessPhone }" name="businessPhone" id="businessPhone" required="required" placeholder="ex)010-1111-1111">
		</td>
	</tr>

			<tr>
				<th>사업자 홈페이지 </th>
				<td>
					<input type="text" size="70" value="${dto.businessHomepage }" name="businessHomepage" id="businessHomepage" required="required" placeholder="홈페이지 메인 주소를 입력해주세요">
				</td>
			</tr>
			<tr>
			<th colspan="2" id="myInfo_th">
			<input type="submit" value="회원정보수정" style="width: 300px; margin-top: 3%;">
			</th>
		</tr>
	</table>
</form>
			<button onclick="javascript:businessDelete('${dto.memberId}');"style="margin-top:2%; width: 300px; margin-left: 41%;">회원탈퇴</button>
</div>
</body>
</html>
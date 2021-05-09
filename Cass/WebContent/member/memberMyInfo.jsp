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
              document.getElementById("memberAddr1").value = data.roadAddress;
               document.getElementById("memberAddr2").focus();
          }
      }).open();   
}

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
<% 
	String memberAddr = ((MemberDto)session.getAttribute("dto")).getMemberAddr();
	String[] addrSplit = new String[3];
	String[] sp = memberAddr.split("/");
	if(sp.length == 2){
		addrSplit[0] = sp[0];
		addrSplit[1] = sp[1];
		addrSplit[2] = "없음";
	}else{
		addrSplit = sp;
	}
%>
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
<form name="memberDeleteForm" id="memberDeleteForm" action="${CONTEXT_PATH}/member/frontController?action=memberDelete" method="post">
<input type="hidden" name="memberId" value="">
<input type="hidden" name="gubun" value="info">
</form>
<div id="title"><a href="${CONTEXT_PATH}/welcome.jsp">CASS 내정보 조회</a></div>
<div class="myInfo">
<form action="${CONTEXT_PATH}/member/frontController?action=myInfoUpdateSave" method="post">
   <table border="1">
   <tr>
      <th>아이디 </th>
      <td>
      <input type="text" name="memberId" id="memberId" value='${dto.memberId }' autofocus="autofocus" readonly="readonly">
      </td>
   </tr>
   
   <tr>
      <th>비밀번호 </th>
      <td>
      <input type="password" name="memberPw" id="memberPw" value='${dto.memberPw }' required="required" placeholder="비밀번호를 입력해주세요">
      <span id="memberPwMessage"></span>
      </td>
   </tr>
   
   <tr>
      <th>비밀번호 확인</th>
      <td>
      <input type="password" name="memberPwConfirm" id="memberPwConfirm" value='${dto.memberPw }' required="required" onblur="checkMemberPw()" placeholder="비밀번호 재확인입니다">
      <input type="checkbox" name="memberPwShow" id="memberPwShow" onclick="showMemberPw()">비밀번호 보이기
      <div id="memberPwConfirmMessage"></div>
      </td>
   </tr>
   <tr>
      <th>우편번호 </th>
      <td>
      <input type="text" name="addrCode" value='<%=addrSplit[0]%>' size="7"  id="addrCode" readonly="readonly">
      <input type="button" value="우편번호찾기" onclick="postcode()" style="margin-left: 6%; width: 200px;">
      </td>
   </tr>
   <tr>
      <th>주소 </th>
      <td>
      <input type="text" name="memberAddr1" id="memberAddr1" value='<%=addrSplit[1]%>' size="70" readonly="readonly" placeholder="도로명주소" >
      <input type="text" name="memberAddr2" id="memberAddr2" value='<%=addrSplit[2]%>' size="70" placeholder="상세주소">
      </td>
   </tr>
   
   <tr>
      <th>휴대번호 </th>
      <td>010-<input style="text-align: left;" type="text" pattern="\d{4}-\d{4}" value='${dto.memberMobile }' name="memberMobile" id="memberMobile" required="required" placeholder="ex)1111-1111"></td>
   </tr>
         <tr>
            <th>이메일 </th>
            <td><input type="email" value='${dto.memberEmail }' name="memberEmail" id="memberEmail" required="required"></td>
         </tr>
         <tr>
            <th>생년월일 </th>
            <td><input type="text" value='${dto.memberBirth }' name="memberBirth" id="memberBirth" readonly="readonly"></td>
         </tr>
         
         <tr>
            <th>포인트</th>
            <td><input type="text"  value='<fmt:formatNumber value="${dto.point}" pattern="#,###" />' name="point" id="point" readonly="readonly"></td>
         </tr>
         <tr>
            <th>등급</th>
            <td><input type="text" value='${dto.grade }' name="grade" id="grade" readonly="readonly"></td>
         </tr>
         
         <tr>
            <th>성별 </th>
            <td>
               <input type="text" value='${dto.sex }' readonly="readonly">
            </td>
         </tr>
         <tr>
         <th colspan="2" id="myInfo_th">
         <input type="submit" value="회원정보수정" style="width: 300px; margin-top: 3%;">
         </th>
      	</tr>
   </table>
</form>
         <button onclick="javascript:memberDelete('${dto.memberId}');" style="margin-top:2%; width: 300px; margin-left: 41%;">회원탈퇴</button>
         
</div>
</body>
</html>
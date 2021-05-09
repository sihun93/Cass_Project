<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@page import="com.work.util.Utility"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 포인트 상품 구매</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pointPage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pointBuy.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
    $(window).scroll(function () {
        var curpos = $(window).scrollTop()+180;
        $(".sky").stop().animate({"top":curpos}); 
    });
});

</script>
</head>
<body>
<div id="wrapper">
			<jsp:include page="/inc/header_menu.jsp" />
   <div id="container">
   <div class="sky">
  	<table>
  		<tr>
  			<td><a href="#">▲ 위로</a></td>
  		</tr>
  		<tr>
  			<td><a href="${CONTEXT_PATH}/point/pointController?action=pointMain">상품 보기</a></td>
  		</tr>
  		<tr>
  		<c:if test="${dto.grade == 'A'}">
  			<td><a href="${CONTEXT_PATH}/point/pointController?action=pointInputForm">상품 등록</a></td>
  		</c:if>
  		</tr>
  		<tr>
  		<c:if test="${dto.grade == 'G'}">
  			<td><a href="${CONTEXT_PATH}/point/pointController?action=pointBuyList">구매 내역</a></td>
  		</c:if>
  		</tr>	
  	</table>
  </div>

	<div class="content_buy">
		<h3>상품 구매</h3>
			<form action="${CONTEXT_PATH}/point/pointController?action=pointBuy&pboardNum=${pointDto.pboardNum}" method="post" id="pointBuy">
				<table border="1">
					<tr>
						<th><img id="content_buy_img" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${pointDto.pboardImg}?alt=media">
						<input type="hidden" name="pboardImg" value="${pointDto.pboardImg}">
						</th>
						<th>상품명: ${pointDto.pboardTitle}<input type="hidden" id="pboardTitle" name="pboardTitle" value="${pointDto.pboardTitle}"></th>
						<th>수량: ${pboardCount}<input type="hidden" id="pboardCount" name="pboardCount" value="${pboardCount}"></th>
						<th>포인트 금액: <fmt:formatNumber value="${pointDto.pboardPrice*pboardCount}" pattern="###,###" />
						<input type="hidden" id="pboardPrice" name="pboardPrice" value="${pointDto.pboardPrice*pboardCount}" >
						</th>
					</tr>
					<tr>
						<th>받는아이디</th>
						<td colspan="3">${memberDto.memberId}
						<input type="hidden" name="memberId" value="${memberDto.memberId}">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">${memberDto.memberAddr}
						<input type="hidden" name="memberAddr" value="${memberDto.memberAddr}">
						</td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td colspan="3">010-${memberDto.memberMobile}
						<input type="hidden" name="memberMobile" value="${memberDto.memberMobile}">
						</td>
					</tr>
					<tr>
						<th>포인트</th>
						<td colspan="3"><fmt:formatNumber value="${memberDto.point}" pattern="###,###" />
						<input type="hidden" id="point" name="point" value="${memberDto.point}">
					    <input type="hidden" id="buyDate" name="buyDate" value="<%= Utility.getCurrentDate() %>">
						</td>
					</tr>
					<tr>
						<th colspan="4">
						<input type="reset" value="돌아가기" onclick="location.href='/Cass/point/pointController?action=pointInfoForm&pboardNum=${pointDto.pboardNum}'">
						<input type="button" value="상품 구매" id="buybtn" onclick="pointBuy()">
						<input type="reset" value="취소">
						</th>
					</tr>
				</table>
			</form>
	</div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>

</body>
</html>
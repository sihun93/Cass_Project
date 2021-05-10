<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pointPage.css">
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

			<div class="buy_list">
				<h3>구매내역</h3>
				<table border="1">
					<tr>
						<th>번호</th>
						<th>이미지</th>
						<th>상품명</th>
						<th>구매갯수</th>
						<th>포인트가격</th>
						<th>구매날짜</th>
					</tr>
					<c:choose>
						<c:when
							test="${fn:length(pointBuylist)==0 or pointBuylist == null}">
							<tr>
								<th colspan="6">구매내역이 없습니다.</th>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="pointBuy" items="${pointBuylist}"
								varStatus="list" begin="0" end="40">
								<tr>
									<td>${list.count}</td>
									<td class="buy_img"><img class="buy_img"
										src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${pointBuy.pboard_img}?alt=media"></td>
									<td>${pointBuy.pboard_title}</td>
									<td>${pointBuy.pboardCount}</td>
									<td><fmt:formatNumber value="${pointBuy.pboardPrice}"
											pattern="###,###" /></td>
									<td>${pointBuy.buyDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>

		<jsp:include page="/inc/footer_menu.jsp" />
	</div>
</body>
</html>
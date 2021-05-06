<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 포인트 상품 구매내역</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/inc/header_menu.jsp" />


		<div id="container">
			<jsp:include page="/inc/point_submenu.jsp" />

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
								<th colspan="5">구매내역이 없습니다.</th>
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
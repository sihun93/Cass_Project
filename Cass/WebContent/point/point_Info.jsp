<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<%@page import="com.work.util.Utility"%>
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

window.onload = function() {
	var price = document.getElementById('pboardPrice').value;
	price = parseInt(price);
	document.getElementById('orderPrice').innerHTML =price.toLocaleString();
}

function count() {
		var count = document.getElementById('pboardCount').value;
		var price = document.getElementById('pboardPrice').value;
		console.log("구매갯수: " + count + ", " + price);
		count = parseInt(count);
		price = parseInt(price);
		var orderPrice = 0;
		orderPrice = Number(orderPrice);
		orderPrice = count * price;
		console.log('orderPrice:', orderPrice);
		document.getElementById('orderPrice').innerHTML = orderPrice.toLocaleString();
		if (count <= 0) {
			alert("구매가능한 수량이 아닙니다.");
			document.getElementById('pboardCount').value = 1;
			document.getElementById('orderPrice').innerHTML =${pointDto.pboardPrice};
		}
	}
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

			<div class="content_Info">
				<h3>상품 상세조회</h3>
				<c:choose>
				<c:when test="${memberDto.grade == 'A'}">
				<table id="up_del">
				<tr>
				<th><form action="${CONTEXT_PATH}/point/pointController?action=pointUpdateForm&pboardNum=${pointDto.pboardNum}" method="post">
				<input type="submit" id="update" value="수정">
				</form>
				</th>
				<th><form action="${CONTEXT_PATH}/point/pointController?action=pointDelete&pboardNum=${pointDto.pboardNum}" method="post">
				<input type="submit" id="delete" value="삭제">
				</form>
				</th>
				</tr>
				</table>
				</c:when>
				</c:choose>
				<div id="point_Info">
					<form action="${CONTEXT_PATH}/point/pointController?action=pointBuyForm&pboardNum=${pointDto.pboardNum}" method="post">
						<table border="1">
							<tr>
								<th rowspan="6" class="img_th"><img class="img_th"
									src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${pointDto.pboardImg}?alt=media"></th>
								<th>상품명: ${pointDto.pboardTitle}</th>
							</tr>
							<tr>
								<th>상품 가격: <div id="orderPrice"></div>
								<input type="hidden" id="pboardPrice" value="${pointDto.pboardPrice}">
								</th>
							</tr>
							<tr>
								<th>상품 배송정보</th>
							</tr>
							<tr>
								<td>배송예정: <%= Utility.getCurrentDates() %></td>
							</tr>
							<tr>
								<th>구매수량: <input type="number" id="pboardCount" name="pboardCount" value="1" onclick="count()"></th>
							</tr>
							<tr>
							<c:choose>
							<c:when test="${memberDto.grade == 'A'}">
							<td><input type="submit" value="구매하기" disabled="disabled"></td>
							</c:when>
							<c:otherwise>
							<td><input type="submit" value="구매하기"></td>
							</c:otherwise>
							</c:choose>
							</tr>
							<tr>
								<th>상품 설명</th>
								<td>${pointDto.pboardContent}</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="othersInfo">
					<br> <br> <br>
					<hr>
					<h3>다른 포인트 상품</h3>
					<c:if test="${pointlist != null}">
						<c:set var="doneLoop" value="false" />
						<c:forEach items="${pointlist}" var="point" varStatus="list"
							begin="1" end="30">
							<c:if test="${list.count<=3}">
								<div class="point_tables">
									<table border="1"
										onclick="location.href='${CONTEXT_PATH}/point/pointController?action=pointInfoForm&pboardNum=${point.pboardNum}'">
										<tr>
											<th><img class="info_img_th" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${point.pboardImg}?alt=media"></th>
										</tr>
										<tr>
											<th>
											<c:choose>
											<c:when test="${fn:length(point.pboardTitle)<6}">
											상품명 : ${point.pboardTitle}
											</c:when>
											<c:otherwise>
											상품명 : ${point.pboardTitle.substring(0, 6)}..
											</c:otherwise>
											</c:choose>
											</th>
										</tr>
										<tr>
											<th>상품가격 : <fmt:formatNumber value="${point.pboardPrice}" pattern="###,###" /></th>
										</tr>
									</table>
								</div>
								<c:set var="doneLoop" value="true" />
							</c:if>
						</c:forEach>
						<div id="info_a">
							<a href="/Cass/point/pointController?action=pointMain"
								id="info_a_tag">더 많은 상품 보러가기</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<jsp:include page="/inc/footer_menu.jsp"/>
</div>
	
</body>
</html>
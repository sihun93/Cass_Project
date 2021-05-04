<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 마일리지 상품 상세조회</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />

		<div id="container">
			<jsp:include page="/inc/point_submenu.jsp" />

			<div class="content_Info">
				<div class="info_up_del">
				<h3>상품 상세조회</h3>
				<form action="" method="post">
				<input type="submit" value="수정">
				</form>
				<form action="" method="post">
				<input type="submit" value="삭제">
				</form>
				</div>
				<div id="point_Info">
					<form action="/Cass/point/pointController?action=pointBuyForm&pboardNum=${pointDto.pboardNum}" method="post">
						<table border="1">
							<tr>
								<th rowspan="6" class="img_th"><img class="img_th"
									src="${url}"></th>
								<th>상품명: ${pointDto.pboardTitle}</th>
							</tr>
							<tr>
								<th>상품 가격: ${pointDto.pboardPrice}</th>
							</tr>
							<tr>
								<th>상품 배송정보</th>
							</tr>
							<tr>
								<td>배송예정: 2021-05-11</td>
							</tr>
							<tr>
								<th>구매수량: <input type="number" name="pboardCount" value="1"></th>
							</tr>
							<tr>
								<td><input type="submit" value="구매하기"></td>
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
					<h3>다른 마일리지 상품</h3>
					<c:if test="${pointlist != null}">
						<c:set var="doneLoop" value="false" />
						<c:forEach items="${pointlist}" var="point" varStatus="list"
							begin="1" end="30">
							<c:if test="${list.count<=3}">
								<div class="point_tables">
									<table border="1"
										onclick="location.href='/Cass/point/pointController?action=pointInfoForm&pboardNum=${point.pboardNum}'">
										<tr>
											<th><img class="info_img_th" src="${url}"></th>
										</tr>
										<tr>
											<th>상품명 : ${point.pboardTitle}</th>
										</tr>
										<tr>
											<th>상품가격 : ${point.pboardPrice}</th>
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
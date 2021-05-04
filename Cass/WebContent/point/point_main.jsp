<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 마일리지 상품</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />


		<div id="container">
			<jsp:include page="/inc/point_submenu.jsp" />

			<div class="content">
				<h3>상품 목록</h3>
				<c:if test="${pointlist != null}">
					<c:forEach items="${pointlist}" var="point">
						<div class="point_table">
							<table border="1"
								onclick="location.href='/Cass/point/pointController?action=pointInfoForm&pboardNum=${point.pboardNum}'">
								<tr>
									<th><img class="main_img_th" src="${url}"></th>
								</tr>
								<tr>
									<th>상품명 : ${point.pboardTitle}</th>
								</tr>
								<tr>
									<th>상품가격 : ${point.pboardPrice}</th>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>

		<jsp:include page="/inc/footer_menu.jsp"/>
</div>
	
</body>
</html>
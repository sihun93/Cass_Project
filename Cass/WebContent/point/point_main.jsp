<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 포인트 상품</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />


		<div id="container">
			<jsp:include page="/inc/point_submenu.jsp" />

			<div class="content">
				<h3>상품 목록</h3>
				<div id="list_search_form">
				<table border="1">
				<tr>
				<th>
				<form action="${CONTEXT_PATH}/point/pointController?action=listsearchform" method="post">
				상품 분류: <select name="search" class="search">
					<option value="pboardTitle" selected="selected">상품명</option>
					<option value="pboardPrice">가격</option>
				</select> 검색 : <input type="text" name="searchName"> <input
					type="submit" value="검색하기">
				</form>
				</th>
				<th>
				<form action="${CONTEXT_PATH}/point/pointController?action=mcategorysearchform" method="post" id="searchmcategory">
				카테고리: <select class="search" name="mcategoryNum" id="mcategory" onchange="this.form.submit()">
						<option value="" selected="selected">선택</option>
						<c:forEach var="MainCategoryDto" items="${categorylist}">
						<option value="${MainCategoryDto.mcategoryNum}">${MainCategoryDto.mcategoryName}</option>
						</c:forEach>
				</select>
				</form>
				</th>
				</tr>
				</table>
				</div>
				<c:choose>
				<c:when test="${fn:length(pointlist)==0 or pointlist == null}">
			    <div class="listnull">
							<table border="1">
								<tr>
									<th>검색결과가 없습니다.</th>
								</tr>
							</table>
						</div>
			    </c:when>
			    <c:otherwise>
			    <c:if test="${pointlist != null}">
					<c:forEach items="${pointlist}" var="point">
						<div class="point_table">
							<table border="1"
								onclick="location.href='${CONTEXT_PATH}/point/pointController?action=pointInfoForm&pboardNum=${point.pboardNum}'">
								<tr>
									<th><img class="main_img_th" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${point.pboardImg}?alt=media"></th>
								</tr>
								<tr>
									<th>상품명 : ${point.pboardTitle}</th>
								</tr>
								<tr>
									<th>상품가격 : <fmt:formatNumber value="${point.pboardPrice}" pattern="###,###" /></th>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
			    </c:otherwise>
				</c:choose>
			</div>
		</div>

		<jsp:include page="/inc/footer_menu.jsp"/>
</div>
</body>
</html>
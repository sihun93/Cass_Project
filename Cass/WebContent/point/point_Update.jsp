<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 포인트 상품 게시글 수정</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	
	
   <div id="container">
   <jsp:include page="/inc/point_submenu.jsp" />
	
	<div class="content_input">
	<h3>포인트 상품 게시글 수정</h3>
	
	<form action="${CONTEXT_PATH}/point/pointController?action=pointUpdate&pboardNum=${pointDto.pboardNum}" method="post">
		<table border="1">
			<tr>
				<th>메인 카테고리</th>
				<td><select name="mcategoryNum">
						<c:forEach var="MainCategoryDto" items="${categorylist}">
						<c:if test="${pointDto.mcategoryNum == MainCategoryDto.mcategoryNum}">
	                    <option value="${MainCategoryDto.mcategoryNum}" selected="selected">${MainCategoryDto.mcategoryName}</option>
	                    </c:if>
						<option value="${MainCategoryDto.mcategoryNum}">${MainCategoryDto.mcategoryName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pboardTitle" value="${pointDto.pboardTitle}"></td>
			</tr>
			<tr>
				<th>상품 가격</th>
				<td><input type="text" name="pboardPrice" value="${pointDto.pboardPrice}"></td>
			</tr>
			<tr>
			    <th>상품 설명</th>
				<td><textarea name="pboardContent">${pointDto.pboardContent}</textarea></td>
			</tr>
			<tr>
			    <th>상품 이미지</th>
				<th><img class="info_img_th" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/point%2Ftest%2F${pointDto.pboardImg}?alt=media">
				<input type="hidden" name="pboardImg" value="${pointDto.pboardImg}">
				</th>
			</tr>
			<tr>
				<th colspan="2">
				<input type="submit" value="수정하기">
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
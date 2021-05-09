<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cass 포인트 상품 게시글 수정</title>
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

	function checkfrm() {
		if (!document.checkform.pboardTitle.value) {
			alert("상품명을 입력하세요");
			return false;
		}

		if (!document.checkform.pboardPrice.value) {
			alert("가격을 입력하세요");
			return false;
		}

		if (!document.checkform.pboardContent.value) {
			alert("내용을 입력하세요");
			return false;
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
	
	<div class="content_input">
	<h3>포인트 상품 게시글 수정</h3>
	
	<form action="${CONTEXT_PATH}/point/pointController?action=pointUpdate&pboardNum=${pointDto.pboardNum}" method="post" name="checkform" onsubmit="return checkfrm()">
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
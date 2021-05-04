<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 마일리지 상품 구매</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	
	
   <div id="container">
   <jsp:include page="/inc/point_submenu.jsp" />

	<div class="content_buy">
		<h3>상품 구매</h3>
			<form action="" method="post">
				<table border="1">
					<tr>
						<th><img id="content_buy_img" src="${url}"></th>
						<th>상품명: ${pointDto.pboardTitle}</th>
						<th>수량: ${pboardCount}</th>
						<th>포인트 금액: ${pointDto.pboardPrice*pboardCount}</th>
					</tr>
					<tr>
						<th>받는사람</th>
						<td colspan="3"><input type="text" name=""></td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3"><input type="text" name=""></td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td colspan="3"><input type="text" name=""></td>
					</tr>
					<tr>
						<th colspan="4">
						<input type="reset" value="돌아가기" onclick="location.href='/Cass/point/pointController?action=pointInfoForm&pboardNum=${pointDto.pboardNum}'">
						<input type="submit" value="상품 구매">
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
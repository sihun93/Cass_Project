<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 서브메뉴</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/pointPage.css">
<style type="text/css">
.sub_menu {
list-style-image: url("../img/dogfoot.png"); 
}
</style>
</head>
<body>
<div class="sub_menu">
	<h3>포인트 상품</h3>
	<ul>
	<li><a href="${CONTEXT_PATH}/point/pointController?action=pointMain">상품 보기</a></li>
	<li><a href="${CONTEXT_PATH}/point/pointController?action=pointInputForm">상품 등록</a></li>
	<li><a href="${CONTEXT_PATH}/point/pointController?action=pointBuyList">구매 내역</a></li>
	</ul>
	</div>
</body>
</html>
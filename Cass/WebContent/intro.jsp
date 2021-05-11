<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<link type="text/css" rel="stylesheet" href="css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
<script type="text/javascript">
$(function () {
    $(window).scroll(function () {
        var curpos = $(window).scrollTop()+180;
        $(".sky").stop().animate({"top":curpos}); 
    });
});
</script>
<link type="text/css" rel="stylesheet" href="css/welcome.css">
<link type="text/css" rel="stylesheet" href="css/introCSS.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	<div id="container">
		<div class="sky">
			<table class="scroll">
				<tr>
					<td><a href="#">▲ 위로</a></td>
				</tr>
				<tr>
					<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1">반려동물 서비스</a></td>
				</tr>
				<tr>
					<td><a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q & A 게시판</a></td>
				</tr>
				<tr>
					<td><a href="${CONTEXT_PATH}/point/pointController?action=pointMain">포인트 샵</a></td>
				</tr>
				<tr>
					<td><a href="${CONTEXT_PATH}/cass/frontController?action=dataCenter">데이터 센터</a></td>
				</tr>
			</table>
		</div>
		<div class="info" align="center">
			Companion Animal Service Site<br>
			반려동물 플랫폼 사이트
		</div>
		<br>
		<br>
		<br>
		<div class="firstText">
			<span class="triangle test_1"></span>
			<img class="firstImg" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg05.jpeg?alt=media">
			<div class="intro">
				저희 CASS는 Companion Animal Service Site의 <br>
				줄임말로 1500만이 넘는 반려인들이 반려동물을 위한<br>
				서비스들을 비교해볼 수 있는 종합 플랫폼 사이트 입니다.
			</div>
		</div>
		<div class="secondText">
			<span class="triangle test_2"></span>
			<img class="secondImg" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg_cat.png?alt=media">
			<div class="secondIntro">
				CASS는 어떠한 서비스들이 준비 되어 있나요?
			</div>
		</div>
		<div class="thirdText">
			<span class="triangle test_3"></span>
			<img class="thirdImg" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg05.jpeg?alt=media">
			<div class="thirdIntro">
				저희 CASS는 장난감이나 악세서리, 미용상품부터 <br>
				돌봄 서비스, 병원, 학교 같은 반려동물을 위한 서비스를<br>
				제공하고 있습니다.
			</div>
		</div>
	</div>
	<jsp:include page="/inc/footer_menu.jsp"/>
</div>
</body>
</html>
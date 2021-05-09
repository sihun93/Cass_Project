<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 포인트 상품 등록</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pointPage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
</head>
<body>
<c:choose>
<c:when test="${memberDto.grade != 'A'}">
<jsp:forward page="/error/error.jsp" />
</c:when>
</c:choose>
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
	<h3>포인트 상품 등록</h3>
	
	<form action="${CONTEXT_PATH}/point/pointController?action=pointInput" method="post" id="inputform">
		<table border="1">
			<tr>
				<th>메인 카테고리</th>
				<td><select name="mcategoryNum">
						<c:forEach var="MainCategoryDto" items="${categorylist}">
						<option value="${MainCategoryDto.mcategoryNum}">${MainCategoryDto.mcategoryName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pboardTitle"></td>
			</tr>
			<tr>
				<th>상품 가격</th>
				<td><input type="text" name="pboardPrice"></td>
			</tr>
			<tr>
			    <th>상품 설명</th>
				<td><textarea name="pboardContent"></textarea></td>
			</tr>
			<tr>
			    <th>상품 이미지</th>
				<td><input type="file" id="fileimg" value="" name="pboardImg"></td>
			</tr>
			<tr>
				<th colspan="2">
				<input type="button" value="등록하기" id="filebtn">
				<input type="reset" value="취소">
				</th>
			</tr>
		</table>
		</form>
	</div>
   </div>
<jsp:include page="/inc/footer_menu.jsp"/>
</div>

<script>
		var firebaseConfig = {
			apiKey : "AIzaSyAqSBsp8MVYfepmDrJH59y_3kDyo2IqUYc",
			authDomain : "clever-cass.firebaseapp.com",
			projectId : "clever-cass",
			storageBucket : "clever-cass.appspot.com",
			messagingSenderId : "1040299710220",
			appId : "1:1040299710220:web:ba3a12d171aed2c47ddb29",
			measurementId : "G-VX528GR619"
		};
		firebase.initializeApp(firebaseConfig);
		firebase.analytics();

		var fileimg = document.getElementById('fileimg');
		var filebtn = document.getElementById('filebtn');
		var file = null;
		var storageRef = null;
		fileimg.addEventListener('change', function(e) {
			file = e.target.files[0];
		});
		filebtn.addEventListener('click', function() {	
			if(file==null){	
			alert("사진을 넣어주세요");
			console.log(file);
			return;
			}
			storageRef = firebase.storage().ref('point/test/' + file.name);
			storageRef.put(file).then(function (snapshot) {
				console.log('업로드');
				$("#inputform").submit();		
			});
			
		});
	</script>
</body>
</html>
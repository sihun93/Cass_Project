<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CASS 마일리지 상품 등록</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
	
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	
	
   <div id="container">
   <jsp:include page="/inc/point_submenu.jsp" />
	
	<div class="content_input">
	<h3>마일리지 상품 등록</h3>
	
	<form action="/Cass/point/pointController?action=pointInput" method="post" id="inputform">
		<table border="1">
			<tr>
				<th>메인 카테고리</th>
				<td><select name="mcategoryNum">
						<option value="mc5">메인카테고리5</option>
						<option value="mc6">메인카테고리6</option>
						<option value="mc7">메인카테고리7</option>
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
				<td><input type="file" id="fileimg" name="pboardImg" multiple="multiple"></td>
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
			storageRef = firebase.storage().ref('point/test/' + file.name);
			storageRef.put(file).then(function (snapshot) {
				console.log('업로드');
				$("#inputform").submit();		
			});
			
		});
	</script>
</body>
</html>
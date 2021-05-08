<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.work.model.dto.QboardDto" %>
<%@ page import="com.work.model.dto.AboardDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시글 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/qboard.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	<c:forEach var="qboardDetailUpdate" items="${qboardDetailUpdate}">
   <div id="container">
   <form action="${CONTEXT_PATH}/cass/qboardController?action=updateQboard2&qboardNum=${qboardDetailUpdate.qboardNum}" method="post" id="Inputform">
  	<div id="mainWrapper">
		<ul>
			<li>
				<ul id="ulTable">
				
					<li>
						<ul>
							<li>제목</li>
							<li>
								<textarea rows="1" cols="60" name="qboardTitle" id="qboardTitle">${qboardDetailUpdate.qboardTitle}</textarea>
							</li>							
						</ul>
					</li>
					<li>
						<ul>
							<li>내용</li>
							<li><textarea rows="15" cols="60" name="qboardContent" id="qboardContent">${qboardDetailUpdate.qboardContent}</textarea></li>							
						</ul>
					</li>
					<li>
						<ul>
							<li>이미지</li>
							<li><input type="file" name="qboardImg" id="qboardImg"></li>					
						</ul>
					</li>
					
						<li>
						<ul>
							<li>게시글</li>
							<li>
							<input type="button" value="등록" id="btn"/>
							<input type="button" value="취소" onclick="location.href='${CONTEXT_PATH}/cass/qboardController?action=qboardList'" id="btn">
							</li>					
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</div>
	</form>
   </div>
   </c:forEach>
   
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

		var qboardImg = document.getElementById('qboardImg');
		var btn = document.getElementById('btn');
		var file = null;
		var storageRef = null;
		qboardImg.addEventListener('change', function(e) {
			file = e.target.files[0];
		});
		btn.addEventListener('click', function() {
			storageRef = firebase.storage().ref('qboard/' + file.name);
			storageRef.put(file).then(function (snapshot) {
				console.log('업로드');
				$("#Inputform").submit();		
			});
			
		});
	</script>	
</body>
</html>
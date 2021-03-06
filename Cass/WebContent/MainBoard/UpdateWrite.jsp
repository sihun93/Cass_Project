<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#mcategory").change(function(){
		 var mcategory = $("#mcategory option:selected").val();
		 var scategory = $("#scategory");
		 var size = $("#scategory option:last").index();
		 for(index =1; index <= size ; index++){
			 var eq = "#scategory option:eq("+index+")";
			 if($(eq).attr("id") == mcategory){
				 $(eq).removeAttr("style");
			 }else{
				 $(eq).attr("style","display: none;");
			 }
			 if($("#mcategory option:selected").val() == "0"){
				 $("#scategory option:first").attr("selected", "selected");
			 }
		 }

	});
	$("#imgbtn").click(function() {
		$("#mbimg").val('');
		var content = $("#content");
		var mbcontainer = $("#mbImage_container");
		content.attr("style", "display: block;");
		mbcontainer.attr("style", "display: none;");
		$("#mbimgval").val("");
		console.log("실행");
	});
});
</script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/welcome.css">
<style type="text/css">
.sky {
	font-family: 'InkLipquid';
	font-size: 1.9em;
	position: absolute;
	width: 160px;
	left: 60px;
	top: 180px;
	height: 250px;
}

.review {
	margin-left: 500px;
	font-family: 'InkLipquid';
	font-size: 1.5em;
	font-family: 'InkLipquid';
	font-size: 1.8em;
}

table#contable {
	width: 40%;
	margin-left: auto;
	margin-right: auto;
}

#pageNumber {
	text-align: center;
}
.wh98 {
	width: 98%;
	height: 98%;
}
#updatetable {
	width: 50%;
	height: 70%;
	margin-left: auto;
	margin-right: auto;
}
textarea {
	resize: none;
}
</style>
</head>
<body>

	<div id="wrapper">
		<jsp:include page="/inc/header_menu.jsp" />

		<!-- 카테고리 매뉴 -->
		<div id="container">
			<div class="sky">
				<table>
					<tr>
						<td><a href="#">▲ 위로</a></td>
					</tr>
					<tr>
						<td><a href="#">메인 카테고리1</a></td>
					</tr>
					<tr>
						<td><a href="#">메인 카테고리2</a></td>
					</tr>
					<tr>
						<td><a href="#">메인 카테고리3</a></td>
					</tr>
					<tr>
						<td><a href="#">메인 카테고리4</a></td>
					</tr>
				</table>
			</div>

<form action="${CONTEXT_PATH}/MainBoard/mainboardController?action=upDateWrite " method="post" id="write">

<input type="hidden" value="${MainBoardDto.businessId }" id="loginId"> 
<input type="hidden" value="${MainBoardDto.mboardNum }" name="mboardNum"> 
<table border="2" id="updatetable">
<tr>
<td colspan="2" align="center">
<input name="title" id="title" type="text" autofocus="autofocus" style="width: 98%" value="${MainBoardDto.mboardTitle }">
</td>
</tr>

<tr>
<td colspan="2">
<select name="mcategory" id="mcategory">
<option value="0">메인 카테고리입니다.</option>
<c:forEach items="${mainCategorylist }" var="mdto">
<c:choose>
<c:when test="${MainBoardDto.mcategoryNum == mdto.mcategoryNum}">
<option value="${mdto.mcategoryNum }" selected="selected">${mdto.mcategoryName }</option>
</c:when>
<c:otherwise>
<option value="${mdto.mcategoryNum }">${mdto.mcategoryName }</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>

<select name="scategory" id="scategory">
<option value="0">메인 카테고리를 선택 해주세요</option>
<c:forEach items="${subCategorylist }" var="sdto">
<c:choose>
<c:when test="${MainBoardDto.scategoryNum == sdto.scategoryNum}">
<option id="${sdto.mcategoryNum }" value="${sdto.scategoryNum }" selected="selected">${sdto.scategoryName }</option>
</c:when>
<c:otherwise>
<option id="${sdto.mcategoryNum }" value="${sdto.scategoryNum }" style="display: none;" >${sdto.scategoryName }</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
<img id="bImage_container" style=" width: 200px; height: 200px" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${MainBoardDto.businessId}%2F${MainBoardDto.mboardImg }?alt=media">
</td>
<td width="80%">
<c:set var="keywordArr" value="${fn:split(MainBoardDto.mboardInfo,'\\\\')}"/>
회사이름:<input type="text" name="data1" value="${keywordArr[0]}" style="width: 80%"><br>
회사주소:<input type="text" name="data2" value="${keywordArr[1] }" style="width: 80%"><br>
전화번호:<input type="text" name="data3" value="${keywordArr[2] }" style="width: 80%"><br>
홈페이지:<input type="text" name="data4" value="${keywordArr[3] }" style="width: 80%">
</td>
</tr>

<tr>
<td colspan="2" height="600px" width="100%" align="center">

<c:set var="iValue" value="/Image/"/>
<c:set var="imglength" value="${fn:length(MainBoardDto.mboardContent)}"/>
<c:choose>
<c:when test="${fn:substring(MainBoardDto.mboardContent,0,7) == iValue }">
<textarea class="wh98" name="mcontent" id="content"  style="display: none;">
</textarea>
<img id="mbImage" width="100%" height="100%" 
src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${MainBoardDto.businessId}%2F${fn:substring(MainBoardDto.mboardContent,7,imglength) }?alt=media">
</c:when>

<c:otherwise>
<textarea class="wh98" name="mcontent" id="content">
<c:out value=" ${fn:substring(MainBoardDto.mboardContent,6,imglength) }"/>
</textarea>
<img id="mbImage" width="100%" height="100%" style="display: none;" >
</c:otherwise>

</c:choose>
</td>
</tr>




<tr>
<td colspan="2">
<input type="hidden" name="bimgval" id="bimgval" value="${MainBoardDto.mboardImg }">
<input type="file" name="bimg" id="bimg" 
onchange="setBImg(event);" style="display: block;">
</td>
</tr>

<tr>
<td colspan="2">
<c:choose>
<c:when test="${fn:substring(MainBoardDto.mboardContent,0,7) == iValue }">
<input type="hidden" name="mbimgval" id="mbimgval" value="${fn:substring(MainBoardDto.mboardContent,7,imglength) }">
<input type="file" name="mbimg" id="mbimg" value=""
onchange="setMbImg(event);" style="display: block;">
</c:when>
<c:otherwise>
<input type="hidden" name="mbimgval" id="mbimgval">
<input type="file" name="mbimg" id="mbimg" 
onchange="setMbImg(event);" style="display: block;">
</c:otherwise>
</c:choose>
</td>
</tr>


<tr>
<td colspan="2" align="left">
<input id="imgbtn" type="button" value="게시글 이미지 초기화" >
<input type="reset" value="초기화">
<input id="filebtn" type="button" value="수정">
</td>
</tr>

</table>
</form>

			<hr>
			<div class="info">
				Companion Animal Service Site<br> 반려동물 플랫폼 사이트
				<h6>
					<a href="">+더 알아보기</a>
				</h6>
			</div>
		</div>

		<jsp:include page="/inc/footer_menu.jsp" />
	</div>


	<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
	<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
	<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
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

		var fileimg1 = document.getElementById('bimg');
		var fileimg2 = document.getElementById('mbimg');
		var filebtn = document.getElementById('filebtn');
		var file1 = null;
		var file2 = null;
		var storageRef = null;
		fileimg1.addEventListener('change', function(e) {
			file1 = e.target.files[0];
		});
		fileimg2.addEventListener('change', function(e) {
			file2 = e.target.files[0];
		});
		filebtn.addEventListener('click', function() {
			if(file1 != null){
				storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file1.name);
				storageRef.put(file1).then(function(snapshot) {
					$("#bimgval").val(file1.name);
					  console.log('Uploaded a blob or file1!');
					  if(file2 != null){
						  storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file2.name);
							storageRef.put(file2).then(function(snapshot) {
								$("#mbimgval").val(file2.name);
								  console.log('Uploaded a blob or file2!');
								  $("#write").submit();
							});
					  }else{
						  $("#write").submit();
					  }
						
				});
			}else{
				  if(file2 != null){
					  storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file2.name);
						storageRef.put(file2).then(function(snapshot) {
							$("#mbimgval").val(file2.name);
							  console.log('Uploaded a blob or file2!');
							  $("#write").submit();
						});
				  }else{
					  $("#write").submit();
				  }
			}

		});
		
		function setBImg(event) {
			var reader = new FileReader(); 
			reader.onload = function(event) { 
				var bcontainer = $("#bImage_container");
				bcontainer.attr("src", event.target.result);
				}; 
			reader.readAsDataURL(event.target.files[0]); 
		};
		function setMbImg(event) {
			var reader = new FileReader(); 
			reader.onload = function(event) { 
				var content = $("#content");
				var mbImage = $("#mbImage");
				mbImage.attr("src", event.target.result);
				content.attr("style", "display: none;");
				content.html("");
				mbImage.attr("style", "display: block;");
				}; 
			reader.readAsDataURL(event.target.files[0]); 
		} ;
	</script>


</body>
</html>
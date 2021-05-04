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
});
</script>
</head>
<body>

<h3> 게시글 작성</h3>

<input type="hidden" value="cathost" id="loginId"> 
<form action="${CONTEXT_PATH}/MainBoard/mainboardController?action=write " method="post" id="write">
메인<select name="mcategory" id="mcategory">
<option value="0">선택 해주세요</option>
<c:forEach items="${mainCategorylist }" var="mdto">
<option value="${mdto.mcategoryNum }">${mdto.mcategoryName }</option>
</c:forEach>
</select>

서브<select name="scategory" id="scategory">
<option value="0">메인 카테고리를 선택 해주세요</option>
<c:forEach items="${subCategorylist }" var="sdto">
<option id="${sdto.mcategoryNum }" value="${sdto.scategoryNum }" style="display: none;">${sdto.scategoryName }</option>
</c:forEach>
</select>

<input name="title" id="title" type="text" autofocus="autofocus" style="display: block;">

<div style=" display: inline;">
<img id="bImage_container" style=" width: 200px; height: 200px" src="">
<textarea name="bcontent" style="resize: none; width:300px; height:195px; font-size: 12"></textarea>
</div>

<textarea name="mcontent" id="content" rows="12" cols="30" style="display: block; ">
</textarea>

<div id="mbImage_container"  style="display: none;" >
</div>

<input type="file" name="bimg" id="bimg"  placeholder="회사 이미지 입니다." 
onchange="setBImg(event);" style="display: block;">

<input type="file" name="mbimg" id="mbimg" placeholder="게시글 이미지 입니다." 
onchange="setMbImg(event);" style="display: block;"> 

<input type="reset" value="초기화">
<input id="filebtn" type="button" value="저장">


</form>
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
			storageRef = firebase.storage().ref($("#loginId").val()+'/' + file1.name);
			storageRef.put(file1).then(function(snapshot) {
				  console.log('Uploaded a blob or file1!');
					storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file2.name);
					storageRef.put(file2).then(function(snapshot) {
						  console.log('Uploaded a blob or file2!');
						  storageRef = firebase.storage().ref($("#loginId").val()+'/' + file2.name);
						  $("#write").submit();
					});
			});
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
				var mbcontainer = $("#mbImage_container");
				var img = $("<img>"); 
				img.attr("src", event.target.result);
				mbcontainer.append(img); 
				content.attr("style", "display: none;");
				mbcontainer.attr("style", "display: block;");
				}; 
			reader.readAsDataURL(event.target.files[0]); 
		} ;
</script>


</body>
</html>
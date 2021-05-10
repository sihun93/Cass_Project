<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mcategory").change(function() {
			var mcategory = $("#mcategory option:selected").val();
			var scategory = $("#scategory");
			var size = $("#scategory option:last").index();
			for (index = 1; index <= size; index++) {
				var eq = "#scategory option:eq(" + index + ")";
				if ($(eq).attr("id") == mcategory) {
					$(eq).removeAttr("style");
				} else {
					$(eq).attr("style", "display: none;");
				}
				if ($("#mcategory option:selected").val() == "0") {
					$("#scategory option:first").attr("selected", "selected");
				}
			}

		});
		$("#imgbtn").click(function() {
			$("#mbimg").val('');
			var content = $("#content");
			var mbImage = $("#mbImage");
			content.attr("style", "display: block;");
			mbImage.attr("style", "display: none;");
			$("#bImage_container").removeAttr("src");
			console.log("실행");
		});
	});
	$(function() {
		$(window).scroll(function() {
			var curpos = $(window).scrollTop() + 180;
			$(".sky").stop().animate({
				"top" : curpos
			});
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


#writetable {
	width: 50%;
	height: 70%;
	margin-left: auto;
	margin-right: auto;
}

.wh98 {
	width: 98%;
	height: 98%;
}

textarea {
	resize: none;
}
#resetbtn, #filebtn{
	width: 100px;
	height: 40px;
	border-radius: 20px;
	background-color: #D1DAE7;
	border:none;
}
#imgbtn{
	width: 150px;
	height: 40px;
	border-radius: 20px;
	background-color: #D1DAE7;
	border:none;
}
.input-file-button{
  padding: 6px 25px;
  background-color:#D1DAE7;
  border-radius: 20px;
  cursor: pointer;
}

</style>
</head>
<body>
<c:if test="${dto.grade eq 'G' or dto.grade eq 'A'}">
	<script type="text/javascript">
		alert("이 페이지는 사업자 회원만 이용할수 있습니다.");
		window.history.back();
	</script>
</c:if>
	<div id="wrapper">
		<jsp:include page="/inc/header_menu.jsp" />

		<!-- 카테고리 매뉴 -->
		<div id="container">
			<div class="sky">
				<table>
  		<tr>
  			<td><a href="#">▲ 위로</a></td>
  		</tr>
  		<c:forEach var="mainCategoryList" items="${mainCategoryList}">
  		<tr>
  			<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=${mainCategoryList.mcategoryNum}">${mainCategoryList.mcategoryName }</a></td>
  		</tr>
  		</c:forEach>
				</table>
			</div>

			<input type="hidden" value="${dto.memberId }" id="loginId">

			<form
				action="${CONTEXT_PATH}/MainBoard/mainboardController?action=write "
				method="post" id="write">
				<table id="writetable">
					<!-- 제목 입력 창 -->
					<tr>
						<td colspan="2" align="center"><input name="title" id="title"
							type="text" autofocus="autofocus" style="width: 99%"></td>
					</tr>
					<tr>
						<td colspan="2">
							<!-- 메인 셀렉터 --> <select name="mcategory" id="mcategory">
								<option value="0">메인 카테고리입니다.</option>
								<c:forEach items="${mainCategorylist }" var="mdto">
									<option value="${mdto.mcategoryNum }">${mdto.mcategoryName }</option>
								</c:forEach>
						</select> <!-- 서브 셀렉터 --> <select name="scategory" id="scategory">
								<option value="0">메인 카테고리를 선택 해주세요</option>
								<c:forEach items="${subCategorylist }" var="sdto">
									<option id="${sdto.mcategoryNum }"
										value="${sdto.scategoryNum }" style="display: none;">${sdto.scategoryName }</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<!-- 회사 정보창 -->
					<tr>
						<td id="tdImg">
							<img id="bImage_container" width="150px" height="150px">
						</td>
						<td width="80%">
						<c:set var="keywordArr" value="${fn:split(dto.businessAddr,'/')}"/>
						회사이름:<input type="text" name="data1" value="${dto.businessTitle }" style="width: 80%"><br>
						회사주소:<input type="text" name="data2" value="${keywordArr[1] }" style="width: 80%"><br>
						전화번호:<input type="text" name="data3" value="${dto.businessPhone }" style="width: 80%"><br>
						홈페이지:<input type="text" name="data4" value="${dto.businessHomepage }" style="width: 80%">
						</td>

					</tr>
					<tr>
						<td colspan="2" height="600px" width="100%" align="center">
							<!-- 게시글 내용창 --> <textarea class="wh98" name="mcontent"
								id="content" >
</textarea>
							
							<img id="mbImage" width="100%" height="100%" style="display: none;">
						</td>
					</tr>
					<tr>
						<td colspan="2"><label class="input-file-button" for="bimg">로고 이미지 업로드</label></td>
						<td><input type="file" name="bimg" id="bimg"
							onchange="setBImg(event);" style="display: none;"></td>
					</tr>
					<tr>
					<td colspan="2"><label class="input-file-button" for="mbimg">게시글 이미지 업로드</label></td>
						<td><input type="file" name="mbimg" id="mbimg"
							required="required" onchange="setMbImg(event);"
							style="display: none;"></td>
					</tr>
					<tr>
						<td colspan="2" align="left"><input id="imgbtn" type="button"
							value="게시글 이미지 초기화"> <input type="reset" value="초기화"
							id="resetbtn"> <input id="filebtn" type="button"
							value="저장"></td>
					</tr>
				</table>
			</form>

		</div>

		<jsp:include page="/inc/footer_menu.jsp" />
	</div>

	<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
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
			storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file1.name);
			storageRef.put(file1).then(function(snapshot) {
				  console.log('Uploaded a blob or file1!');
				  if(file2 != null){
					  storageRef = firebase.storage().ref('mainboard/'+$("#loginId").val()+'/' + file2.name);
						storageRef.put(file2).then(function(snapshot) {
							  console.log('Uploaded a blob or file2!');
							  $("#write").submit();
						});
				  }else{
					  $("#write").submit();
				  }
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
				var mbImage = $("#mbImage");
				mbImage.attr("src", event.target.result);
				content.attr("style", "display: none;");
				mbImage.attr("style", "display: block;");
			};
			reader.readAsDataURL(event.target.files[0]);
		};
	</script>

</body>
</html>
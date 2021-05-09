<%@page import="com.work.model.dto.ReviewDto"%>
<%@page import="com.work.model.dto.BusinessMemberDto"%>
<%@page import="com.work.model.dto.MemberDto"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>
function starcilck(star) {
	var a = star;
	$("#reviewscore").val(a.value);
}
function updatereview(formId){
	var id = "#"+formId+"-1-1";
	$(id).submit();
	
}
function updatereviewform(formId) {
	var id = "#"+formId+"-1";
	$("#"+formId).css('display','none');
	$(id).css('display','');
}
function updatecancel(formId) {
	var id = "#"+formId+"-1";
	$("#"+formId).css('display','');
	$(id).css('display','none');
}
function resize(imgThis){
	var seletImg = imgThis;
	var img=new Image();
	img.src=seletImg.src;
	var img_width=img.width;
	var win_width=img.width+25;
	var img_height=img.height;
	var win=img.height+30;
	var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
	OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+seletImg.src+"' width='"+win_width+"'>");
}
</script>
<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/star.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mainboardDetail.css">
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/inc/header_menu.jsp" />


		<div id="container">
		<!-- 이동 메뉴바 -->
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
			
<input type="hidden" name="loginId" id="loginId" value="${dto.memberId }">
<input type="hidden" name="writeId" id="writeId" value="${detaildto.businessId }"> 
<table 	id="detailtable" class="retable">
<!-- 회사 정보 -->
<tr>
<td>
<img id="bImage_container" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${detaildto.businessId}%2F${detaildto.mboardImg }?alt=media">
</td>
<td id="keyword">
<c:set var="keywordArr" value="${fn:split(detaildto.mboardInfo,'\\\\')}"/>
회사이름: ${keywordArr[0]}<br><br>
회사주소: <a onclick="mapup()">${keywordArr[1] }</a><br><br>
<input type="hidden" value="${keywordArr[1] }" id="mapaddr">
전화번호: ${keywordArr[2] }<br><br>
홈페이지: <a href="${keywordArr[3] }" target ="_blank">${keywordArr[3] }</a>
</td>
</tr>


<!-- 메인 내용 -->
<tr>
<td colspan="2" width="100%" align="center">

<c:set var="iValue" value="/Image/"/>
<c:set var="imglength" value="${fn:length(detaildto.mboardContent)}"/>
<c:choose>

<c:when test="${fn:substring(detaildto.mboardContent,0,7) == iValue }">
<img id="mbImage"
src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${detaildto.businessId}%2F${fn:substring(detaildto.mboardContent,7,imglength) }?alt=media">
</c:when>

<c:otherwise>
${fn:substring(detaildto.mboardContent,6,imglength) }
</c:otherwise>

</c:choose>
</td>
</tr>

</table>
<div id="deletebtn">
<c:if test="${(dto.grade eq 'A') or (dto.grade eq 'B' and dto.memberId eq detaildto.businessId)}">
<input type="button" value="게시글 삭제" onclick="location.href='${CONTEXT_PATH}/MainBoard/mainboardController?action=deleteMainBoard&mboardNum=${detaildto.mboardNum}'">
</c:if>
</div>

<br>
<!-- 리뷰 입력창 -->
<form id="inputreviewform" method="post" action="${CONTEXT_PATH}/MainBoard/mainboardController?action=inputreview">
<input type="hidden" name="mboardNum" value="${detaildto.mboardNum }">
<table id="inputTable" class="retable" >
<thead>
<tr style="padding: 0">
<td colspan="2" style="text-align: left; padding-top: 1px padding-bottom: 1px">
<textarea id="reviewTextarea" name="reviewTextarea" style="resize: none; width: 89%; height: 75px; margin-left: 5%;">
</textarea><br>
<img id="img0" width="75px" height="75px" style="display: none;">
<img id="img1" width="75px" height="75px" style="display: none;">
<img id="img2" width="75px" height="75px" style="display: none;">
</td>
<td align="center" rowspan="2" id="inputbtn">
등록
</td>
</tr>

<tr>
<td align="left" width="50%">
<div class="startRadio">
  <label class="startRadio__box">
    <input type="radio" name="star" id="star1" value="1" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 0.5개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star2" value="2" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 1개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star3" value="3" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 1.5개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star4" value="4" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 2개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star5" value="5" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 2.5개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star6" value="6" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 3개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star7" value="7" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 3.5개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star8" value="8" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 4개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star9" value="9" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 4.5개</span></span>
  </label>
  <label class="startRadio__box">
    <input type="radio" name="star" id="star10" value="10" onclick="starcilck(this)">
    <span class="startRadio__img"><span class="blind">별 5개</span></span>
  </label>
</div>
<input id="reviewscore" name="reviewscore" type="hidden">
</td>
<td align="right">
<input type="file" id="reviewimg" name="reviewimg" multiple="multiple">

</td>
</tr>
</table>
</form>
<c:if test="${(detaildto.businessId eq dto.memberId  and dto.grade eq 'B') or dto.grade eq 'A'}">
	<input type="button" value="수정" onclick="location.href='${CONTEXT_PATH}/MainBoard/mainboardController?action=upDateWriteForm&mBoardNum=${detaildto.mboardNum}'">
</c:if>
<br>

<!-- 리뷰 출력 창 -->
<c:if test="${!empty ReviewList and fn:length(ReviewList) !=0 }">
<c:forEach items="${ReviewList }" var="review" varStatus="state">
<table class="retable"  id="${state.index}">
<tr style="padding: 0">

<td align="left">
아이디 : ${review.memberId}
</td>
<td align="right" >
별점:
<div class="startRadio">
  <label class="startRadio__box">
    <c:if test="${review.score >=1 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 0.5개</span></span>
    </c:if>
    <c:if test="${review.score <1 }">
    <span class="startRadio__img" ><span class="blind">별 0.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=2 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 1개</span></span>
    </c:if>
    <c:if test="${review.score <2 }">
    <span class="startRadio__img" ><span class="blind">별 1개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=3 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 1.5개</span></span>
    </c:if>
    <c:if test="${review.score <3 }">
    <span class="startRadio__img" ><span class="blind">별 1.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=4 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 2개</span></span>
    </c:if>
    <c:if test="${review.score <4 }">
    <span class="startRadio__img" ><span class="blind">별 2개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=5 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 2.5개</span></span>
    </c:if>
    <c:if test="${review.score <5 }">
    <span class="startRadio__img" ><span class="blind">별 2.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=6 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 3개</span></span>
    </c:if>
    <c:if test="${review.score <6 }">
    <span class="startRadio__img" ><span class="blind">별 3개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=7 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 3.5개</span></span>
    </c:if>
    <c:if test="${review.score <7 }">
    <span class="startRadio__img" ><span class="blind">별 3.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=8 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 4개</span></span>
    </c:if>
    <c:if test="${review.score <8 }">
    <span class="startRadio__img" ><span class="blind">별 4개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=9 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 4.5개</span></span>
    </c:if>
    <c:if test="${review.score <9 }">
    <span class="startRadio__img" ><span class="blind">별 4.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${review.score >=10 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 5개</span></span>
    </c:if>
    <c:if test="${review.score <10 }">
    <span class="startRadio__img" ><span class="blind">별 5개</span></span>
    </c:if>
  </label>
</div>
</td>
<td align="center" rowspan="2"  width="10%" >
<c:choose>
<c:when test="${review.memberId eq dto.memberId }">
<input  type="button" value="수정" 
onclick="updatereviewform('${state.index}')"><br><br>
<input  type="button" value="삭제" 
onclick="location.href='${CONTEXT_PATH}/MainBoard/mainboardController?action=deletereview&mboardNum=${review.mboardNum}&memberId=${review.memberId}&reviewNum=${review.reviewNum}'">
</c:when>
<c:otherwise>
<input  type="button" value="수정" disabled="disabled"><br><br>
<input  type="button" value="삭제" disabled="disabled">
</c:otherwise>
</c:choose>
</td>
</tr>

<tr>
<td colspan="2" width="77%" height="80px">
${review.reviewContent}<br>
<c:forEach items="${fn:split(review.reviewImg,'\\\\') }" var="imgName">
<img width="75px" height="75px" onclick="resize(this)";
src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${detaildto.businessId}%2F${review.memberId }%2F${imgName }?alt=media">
</c:forEach>
</td>
</tr>
</table>

<form action="${CONTEXT_PATH}/MainBoard/mainboardController?action=updatereview" method="post" id="${state.index}-1-1">
<input type="hidden" value="${review.mboardNum }" name="mboardNum">
<input type="hidden" value="${review.memberId}" name="memberId">
<input type="hidden" value="${review.reviewNum}" name="reviewNum"> 
<table class="retable"  id="${state.index}-1" style="display: none;">
<tr style="padding: 0">
<td colspan="2" width="75%" height="90%" style="text-align: left; padding-top: 1px padding-bottom: 1px">
<textarea id="reviewTextarea" name="reviewTextarea" style="resize: none; width: 99%; height: 75px">
${review.reviewContent}
</textarea>
</td>
<td align="center" width="10%">
<input type="button" value="저장" onclick="updatereview(${state.index})"><br>
<input type="button" value="취소" onclick="updatecancel(${state.index})"><br>
</td>
</tr>
</table>
</form>
</c:forEach>
</c:if>

<!-- 페이지 번호 -->
<table class="retable">
<tr>
<th>
&lt;|
<%	
	int max = (Integer)request.getAttribute("maxReviewPageNum");
	for(int i = 1 ; i <= max;i++){
%>
	<a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum=${detaildto.mboardNum }&pageNum=<%= i%>" ><%= i%> |</a>
<%
	}
%>
&gt;
</th>
</tr>
</table>

			<br> <br> <br> <br> <br> <br>


		</div>

		<jsp:include page="/inc/footer_menu.jsp" />
	</div>
	<script src="https://www.gstatic.com/firebasejs/8.4.3/firebase-app.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/8.4.3/firebase-analytics.js"></script>
	<script
		src="https://www.gstatic.com/firebasejs/8.4.3/firebase-storage.js"></script>
	<script>
	$(function () {
	    $(window).scroll(function () {
	        var curpos = $(window).scrollTop()+180;
	        $(".sky").stop().animate({"top":curpos}); 
	    });
	});
	$(document).ready( function() {
		 
	    $("#reviewimg").change(function(e) {
	        $("#img0").attr("style", "display: none;");
	        $("#img1").attr("style", "display: none;");
	        $("#img2").attr("style", "display: none;");
	        var files = e.target.files;
	        if(files.length > 3){
	        	alert("이미지는 3장 까지 가능합니다.")
	        	$("#reviewimg").val('');
	        	return;
	        }
	        var fileArr = Array.prototype.slice.call(files)
	        for(index = 0 ;index < files.length;index++){
	        	var imgId = "img"+index;
	            imageLoader(fileArr[index], imgId);
	        }

	    });

	    imageLoader = function(file,imgname){
	        var reader = new FileReader();
	        reader.onload = function(e){
	          var img = $("#"+imgname)
	          img.attr('src',e.target.result);
	          img.attr("style", "display: inline;");
	        }
	        reader.readAsDataURL(file);
	    }

	});
	function mapup(){
		var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	    var contextpath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	    var url = contextpath+"/MainBoard/map.jsp";
	    var name = "지도";
	    var option = "width = 625, height = 420, top = 100, left = 200, location = no"
	    window.open(url, name, option);
	}	

	
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
		var fileimg1 = document.getElementById('reviewimg');
		var filebtn = document.getElementById('inputbtn');
		var files = null;
		var file2 = null;
		var storageRef = null;
		fileimg1.addEventListener('change', function(e) {
			files = e.target.files;
		});
		filebtn.addEventListener('click', function() {
			if (files != null) {
				var max = files.length;
				var index = 0;
		        	storageRef = firebase.storage().ref('mainboard/'+$("#writeId").val()+'/'+ $("#loginId").val() + '/' + files[index].name);
					storageRef.put(files[index]).then(function(snapshot) {
						console.log('Uploaded a blob or file');
						index += 1;
						if(index < max){
			        		storageRef = firebase.storage().ref('mainboard/'+$("#writeId").val()+'/'+ $("#loginId").val() + '/' + files[index].name);
							storageRef.put(files[index]).then(function(snapshot) {
								console.log('Uploaded a blob or file');	
								if(index < max){
					        		storageRef = firebase.storage().ref('mainboard/'+$("#writeId").val()+'/'+ $("#loginId").val() + '/' + files[index].name);
									storageRef.put(files[index]).then(function(snapshot) {
										console.log('Uploaded a blob or file');	
										$("#inputreviewform").submit();
									});
								}else{
									$("#inputreviewform").submit();
								}
							});
						}else{
							$("#inputreviewform").submit();
						}
						
					});
		     }else {
				$("#inputreviewform").submit();
			}
		});
	</script>
</body>
</html>
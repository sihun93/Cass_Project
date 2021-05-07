<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="css/welcome.css">
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
   <div id="container">
	  <div class="sky">
	  	<table>
	  		<tr>
	  			<td><a href="#">▲ 위로</a></td>
	  		</tr>
	  		<tr>
	  			<td><a href="#">반려동물 서비스</a></td>
	  		</tr>
	  		<tr>
	  			<td><a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q&A 게시판</a></td>
	  		</tr>
	  		<tr>
	  			<td><a href="#">포인트 샵</a></td>
	  		</tr>
	  		<tr>
	  			<td><a href="#">데이터</a></td>
	  		</tr>
	  	</table>
	  </div>
  <div class="table">
  <h1>Service</h1>
  <table>
  	<tr>
  		<td><a href=""><img src="img/service/beauty.jpg"></a></td>
  		<td><a href=""><img src="img/service/play.jpg"></a></td>
  		<td><a href=""><img src="img/service/hospital.jpg"></a></td>
  		<td><a href=""><img src="img/service/private.jpg"></a></td>
  	</tr>
  	<tr>
  	<c:forEach var="mainCategoryList" items="${mainCategoryList}">
  		<th class="dropdown">▼  ${mainCategoryList.mcategoryName }
  			<div class="drop">
  			<div class="dropdown-content">
  			<c:forEach var="subCategoryList" items="${subCategoryList}">
  			<c:if test="${mainCategoryList.mcategoryNum eq subCategoryList.mcategoryNum}">
  			<a href="#">${subCategoryList.scategoryName }</a>
  			</c:if>
    		</c:forEach>
  			</div>
  			</div>
  		</th>
  		</c:forEach>
  	</tr> 	
  </table>
  </div>
  <br>
  <br>
  <hr>
  <div class="review">
  <h1>Best Review</h1>
  <c:forEach var="bestReviewList" items="${bestReviewList}">
  	<table>
  		<tr class="review_">
  			<td><img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/review%2F${bestReviewList.reviewImg}?alt=media" width="150px" height="150px"></td>
  			<td>${bestReviewList.reviewContent }</td>
  		</tr>
  		<tr>
  			<td>${bestReviewList.memberId}님의 후기</td>
  		</tr>
  		<tr>
  			<td>평점 : ${bestReviewList.score}</td>
  		</tr>
  	</table>
  	</c:forEach>
  </div>
  <br>
  <br>
  <hr>
  <div class="info">
  	 Companion Animal Service Site<br>
  	 반려동물 플랫폼 사이트
  	 <h6><a href="intro.jsp">+더 알아보기</a></h6>
  </div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
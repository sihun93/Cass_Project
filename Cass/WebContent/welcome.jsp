<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<style type="text/css">

.sky {
	font-family:'InkLipquid';
	font-size:1.9em;
    position:absolute;
    width:160px;
    left:60px;
    top:180px;
    height:250px;
}
.review{
	margin-left:500px;
	font-family:'InkLipquid';
	font-size:1.5em;
	font-family:'InkLipquid';
	font-size:1.8em;
}
 .table{
    	margin-top:5%;
		margin-left:500px;
		font-family:'InkLipquid';
		font-size:1.8em;
    }
    .info{
 	animation: fadein 10s;
    -moz-animation: fadein 10s;
    -webkit-animation: fadein 10s;
    -o-animation: fadein 10s;
	margin-top:60px;
	margin-left:500px;
	font-family:'InkLipquid';
	font-size:4em;
	}
	.dropdown{
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
	}
	.review_{
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
	}
	img[src*="img/service"]{
		width:200px; height:200px;
	}
	table{
	border-spacing: 20px;
	}
</style>
</head>
<body>

<div id="wrapper">
<%-- <c:choose>
   <c:when test="${empty grade}">
      <jsp:include page="/inc/header_menu.jsp" />
   </c:when>

   <c:when test="${grade == 'G'}">
      <jsp:include page="/inc/member_header_menu.jsp" />
   </c:when>

   <c:when test="${grade == 'B'}">
      <jsp:include page="/inc/business_header_menu.jsp" />
   </c:when>
   
   <c:when test="${grade == 'A'}">
      <jsp:include page="/inc/admin_header_menu.jsp" />
   </c:when>
</c:choose> --%>
	<c:choose>
		<c:when test="${empty bdto}">
			<jsp:include page="/inc/header_menu.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/inc/business_header_menu.jsp" />
		</c:otherwise>
	</c:choose>
   <div id="container">
  <div class="sky">
  	<table>
  		<tr>
  			<td><a href="#">??? ??????</a></td>
  		</tr>
  		<c:forEach var="mainCategoryList" items="${mainCategoryList}">
  		<tr>
  			<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=${mainCategoryList.mcategoryNum}">${mainCategoryList.mcategoryName }</a></td>
  		</tr>
  		</c:forEach>
  	</table>
  </div>
  <div class="table">
  <h1>Service</h1>
  <table>
  	<tr>
  		<td><a href=""><img src="${CONTEXT_PATH}/img/service/play.jpg"></a></td>
  		<td><a href=""><img src="${CONTEXT_PATH}/img/service/beauty.jpg"></a></td>
  		<td><a href=""><img src="${CONTEXT_PATH}/img/service/school.jpg"></a></td>
  		<td><a href=""><img src="${CONTEXT_PATH}/img/service/food.jpeg"></a></td>
  	</tr>
  	<tr>
  	<c:forEach var="mainCategoryList" items="${mainCategoryList}">
  		<th class="dropdown">
  		<a href= "${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=${mainCategoryList.mcategoryNum}">???  ${mainCategoryList.mcategoryName }</a>
  			<div class="drop">
  			<div class="dropdown-content">
  			<c:forEach var="subCategoryList" items="${subCategoryList}">
  			<c:if test="${mainCategoryList.mcategoryNum eq subCategoryList.mcategoryNum}">
  			<a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&scategoryNum=${subCategoryList.scategoryNum}">${subCategoryList.scategoryName}</a>
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
  			<td>${bestReviewList.memberId}?????? ??????</td>
  		</tr>
  		<tr>
  			<td>?????? : ${bestReviewList.score}???</td>
  		</tr>
  	</table>
  	</c:forEach>
  </div>
  <br>
  <br>
  <hr>
  <div class="info">
  	 Companion Animal Service Site<br>
  	 ???????????? ????????? ?????????
  	 <h6><a href="intro.jsp">+??? ????????????</a></h6>
  </div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
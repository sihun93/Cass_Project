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
  		<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=mc1"><img src="${CONTEXT_PATH}/img/service/play.jpg"></a></td>
  		<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=mc2"><img src="${CONTEXT_PATH}/img/service/beauty.jpg"></a></td>
  		<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=mc3"><img src="${CONTEXT_PATH}/img/service/school.jpg"></a></td>
  		<td><a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=mc4"><img src="${CONTEXT_PATH}/img/service/food.jpeg"></a></td>
  	</tr>
  	<tr>
  	<c:forEach var="mainCategoryList" items="${mainCategoryList}">
  		<th class="dropdown">
  		<a href= "${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1&mcategoryNum=${mainCategoryList.mcategoryNum}">▼  ${mainCategoryList.mcategoryName }</a>
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
  <h1>Best Service</h1>
  <c:forEach var="bestMainList" items="${bestMainList}">
  	<table width="1000px">
  		<tr class="review_">
  			<c:if test="${not empty bestMainList.mboardImg}">
  			<td><img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${bestMainList.businessId}%2F${bestMainList.mboardImg}?alt=media" width="150px" height="150px"></td>
  			</c:if>
  			<td><c:set var="keywordArr" value="${fn:split(bestMainList.mboardInfo,'\\\\')}"/>
	${keywordArr[0]}<br><br>
	<a onclick="mapup()">${keywordArr[1] }</a><br><br>
	<input type="hidden" value="${keywordArr[1] }" id="mapaddr">
	${keywordArr[2] }<br><br>
	<a href="${keywordArr[3] }" target ="_blank">${keywordArr[3] }</a></td>
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
  	 <h6><a href="${CONTEXT_PATH}/intro.jsp">+더 알아보기</a></h6>
  </div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
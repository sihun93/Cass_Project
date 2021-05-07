<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
    $(window).scroll(function () {
        var curpos = $(window).scrollTop()+180;
        $(".sky").stop().animate({"top":curpos}); 
    });
});
function fsubmit(eventClass) {
	$("."+eventClass).submit();
}
</script>
<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="/css/welcome.css">
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
table#contable{
	width: 40%;
	margin-left: auto;
	margin-right: auto;
}
#pageNumber{
	text-align: center;
}
</style>
</head>
<body>
	<div id="wrapper">
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


<div id="content">
<c:if test="${!empty mainbaordList }">
<c:forEach items="${mainbaordList}" var="mainbaord">
<form action="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordDetail&pageNum=1" id="listcolumn" class="${mainbaord.mboardNum }" onclick="fsubmit('${mainbaord.mboardNum }')" method="post">
<input type="hidden" value="${mainbaord.mboardNum }" name="mBoardNum">
<table id="contable" border="2">
<tr>
<td rowspan="2">
<img style="width: 175px; height: 175px" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${mainbaord.businessId}%2F${mainbaord.mboardImg }?alt=media">
</td>
<td>제목</td>
<td align="right">${mainbaord.mboardScore }점</td>
</tr>

<tr>
<td colspan="2" rowspan="2"><textarea  style="resize: none; width:500px; height:160px;">${mainbaord.mboardInfo }</textarea></td>
</tr>

<tr>
<td>화사명</td>
</tr>
</table>
</form>
<br>

</c:forEach>
</c:if>
</div>
<div id="pageNumber" style="align-content: center;">
&lt;|
<%	
	int max = (Integer)request.getAttribute("maxPageNum");
	for(int i = 1 ; i <= max;i++){
%>
	<a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=<%= i%>"><%= i%></a>
<%
	}
%>
|&gt;
</div>
			<br> <br> <br> <br> <br> <br>

			<hr>


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
</body>
</html>
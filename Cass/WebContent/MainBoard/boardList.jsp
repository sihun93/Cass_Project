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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
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
	width: 580px;
	height: 220px;
	margin-left: auto;
	margin-right: auto;
	background-color: #D1DAE7;
	padding-bottom: 5px;
}
#pageNumber{
	text-align: center;
}
</style>
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


<div id="content">
<div style="margin-top: 5%;"></div>
<c:if test="${dto.grade eq 'B' }">
<input type="button" value="게시글 작성" onclick="location.href='${CONTEXT_PATH}/MainBoard/mainboardController?action=writeForm'">
</c:if>
<c:if test="${!empty mainbaordList }">
<c:forEach items="${mainbaordList}" var="mainbaord">
<form action="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordDetail&pageNum=1" id="listcolumn" class="${mainbaord.mboardNum }" method="post">
<input type="hidden" value="${mainbaord.mboardNum }" name="mBoardNum">
<table id="contable"  onclick="fsubmit('${mainbaord.mboardNum }')" >
<tr>
<td rowspan="2">
<img style="width: 175px; height: 175px" src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F${mainbaord.businessId}%2F${mainbaord.mboardImg }?alt=media">
</td>
<td>제목:${mainbaord.mboardTitle }</td>
<td align="right">${mainbaord.mboardScore }점</td>
</tr>

<tr>
<td colspan="2" width="500px"; height="130px";>
<c:set var="keywordArr" value="${fn:split(mainbaord.mboardInfo,'\\\\')}"/>
회사이름:${keywordArr[0]}<br>
회사주소:${keywordArr[1] }<br>
전화번호:${keywordArr[2] }<br>
홈페이지:${keywordArr[3] }
</td>
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

		</div>

		<jsp:include page="/inc/footer_menu.jsp" />
	</div>
</body>
</html>
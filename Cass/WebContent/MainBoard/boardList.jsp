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
table#contable{
	width: 1000px;
	height: 220px;
	margin-left: auto;
	margin-right: auto;
	padding-bottom: 5px;
	border:3px outset;
}
#pageNumber{
	margin-top:4%;
	text-align: center;
	font-family: 'InkLipquid';
	font-size: 1.8em;
}
#content{
	font-family: 'InkLipquid';
	font-size: 1.8em;
}
#inputbtn{
	margin-left:71%;
	margin-bottom:1%;
	width: 100px;
	height: 40px;
	border-radius: 20px;
	background-color: #D1DAE7;
	border:none;
}
#logoImg{
	image-rendering: -moz-auto;
	image-rendering: -o-auto;
	image-rendering: -webkit-optimize-contrast;
	image-rendering: auto;
	-ms-interpolation-mode: nearest-neighbor;
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
<input type="button" value="게시글 작성" onclick="location.href='${CONTEXT_PATH}/MainBoard/mainboardController?action=writeForm'" id="inputbtn">
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
<td align="right" width="110px;" style="margin-right: 2px;">
<c:if test="${mainbaord.mboardScore == 0}">
리뷰가 부족합니다.
</c:if>
<c:if test="${mainbaord.mboardScore != 0}">
<div class="startRadio">
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=1 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 0.5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <1 }">
    <span class="startRadio__img" ><span class="blind">별 0.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=2 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 1개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <2 }">
    <span class="startRadio__img" ><span class="blind">별 1개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=3 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 1.5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <3 }">
    <span class="startRadio__img" ><span class="blind">별 1.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=4 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 2개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <4 }">
    <span class="startRadio__img" ><span class="blind">별 2개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=5 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 2.5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <5 }">
    <span class="startRadio__img" ><span class="blind">별 2.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=6 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 3개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <6 }">
    <span class="startRadio__img" ><span class="blind">별 3개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=7 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 3.5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <7 }">
    <span class="startRadio__img" ><span class="blind">별 3.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=8 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 4개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <8 }">
    <span class="startRadio__img" ><span class="blind">별 4개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=9 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 4.5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <9 }">
    <span class="startRadio__img" ><span class="blind">별 4.5개</span></span>
    </c:if>
  </label>
  <label class="startRadio__box">
    <c:if test="${mainbaord.mboardScore >=10 }">
    <span class="startRadio__img" id="reviewStar1"><span class="blind">별 5개</span></span>
    </c:if>
    <c:if test="${mainbaord.mboardScore <10 }">
    <span class="startRadio__img" ><span class="blind">별 5개</span></span>
    </c:if>
  </label>
</div>
</c:if>
</td>
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
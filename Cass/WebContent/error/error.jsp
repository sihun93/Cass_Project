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
	font-size:5em;
	}
	.dropdown{
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
	}
	.review_{
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
	}
	img[src^="img/img"]{
		width:400px; height:400px;
	}
	h2{
	text-align: center;
	}
</style>
</head>
<body>
<div id="wrapper">
<c:choose>
	<c:when test="${empty dto.grade}">
		<jsp:include page="/inc/header_menu.jsp" />
	</c:when>

	<c:when test="${dto.grade == 'G'}">
		<jsp:include page="/inc/member_header_menu.jsp" />
	</c:when>

	<c:when test="${dto.grade == 'B'}">
		<jsp:include page="/inc/business_header_menu.jsp" />
	</c:when>
	
	<c:when test="${dto.grade == 'A'}">
		<jsp:include page="/inc/admin_header_menu.jsp" />
	</c:when>
</c:choose>
   <div id="container">
  <div class="sky">
  	<table>
  		<tr>
  			<td><a href="#">??? ??????</a></td>
  		</tr>
  		<tr>
  			<td><a href="#">???????????? ?????????</a></td>
  		</tr>
  		<tr>
  			<td><a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q&A ?????????</a></td>
  		</tr>
  		<tr>
  			<td><a href="#">????????? ???</a></td>
  		</tr>
  		<tr>
  			<td><a href="#">?????????</a></td>
  		</tr>
  	</table>
  </div>
      <h2>????????? ?????? ??????????????????.</h2>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
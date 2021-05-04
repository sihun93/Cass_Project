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
<link type="text/css" rel="stylesheet" href="css/common.css">
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
    	margin-top:500px;
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
	#main_menu{
	position:absolute;
	font-size:1.2em;
	padding-top:33px;
	padding-left:320px;
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
  		<tr>
  			<td><a href="#">반려동물 서비스</a></td>
  		</tr>
  		<tr>
  			<td><a href="${CONTEXT_PATH}/cass/frontController?action=qboardList">Q&A게시판</a></td>
  		</tr>
  		<tr>
  			<td><a href="#">마일리지 상품</a></td>
  		</tr>
  		<tr>
  			<td><a href="#">데이터</a></td>
  		</tr>
  	</table>
  </div>
   
   <div class="slide">
    	<ul>
      <li><img src="img/img2.PNG"></li>
      <li><img src="img/img12.PNG"></li>
      <li><img src="img/img4.PNG"></li>
      <li><img src="img/img13.PNG"></li>
      <li><img src="img/img11.PNG"></li>
    	</ul>
  </div>
  <br>
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
  		<th class="dropdown">▼  뷰티샵
  			<div class="drop">
  			<div class="dropdown-content">
  			<a href="#">서브1</a>
    		<a href="#">서브2</a>
    		<a href="#">서브3</a>
  			</div>
  			</div>
  		</th>
  		<th class="dropdown">▼  놀이터
  			<div class="drop">
  			<div class="dropdown-content">
  			<a href="#">서브</a>
    		<a href="#">서브</a>
    		<a href="#">서브</a>
  			</div>
  			</div>
  		</th>
  		<th class="dropdown">▼  병원/장례
  			<div class="drop">
  			<div class="dropdown-content">
  			<a href="#">서브1</a>
    		<a href="#">서브2</a>
    		<a href="#">서브3</a>
  			</div>
  			</div>
  		</th>
  		<th class="dropdown">▼  애완용품
  			<div class="drop">
  			<div class="dropdown-content">
  			<a href="#">서브1</a>
    		<a href="#">서브2</a>
    		<a href="#">서브3</a>
  			</div>
  			</div>
  		</th>
  	</tr> 	
  </table>
  </div>
  <br>
  <br>
  <hr>
  <div class="review">
  <h1>Best Review</h1>
  	<table>
  		<tr>
  			<td><img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg05.jpeg?alt=media"></td>
  			<td>리뷰 내용</td>
  		</tr>
  		<tr>
  			<td>회원 ID</td>
  		</tr>
  	</table>
  		<table>
  		<tr>
  			<td><img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg05.jpeg?alt=media"></td>
  			<td>리뷰 내용</td>
  		</tr>
  		<tr>
  			<td>회원 ID</td>
  		</tr>
  	</table>
  		<table>
  		<tr>
  			<td><img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainPage%2Fimg05.jpeg?alt=media"></td>
  			<td>리뷰 내용 </td>
  		</tr>
  		<tr>
  			<td>회원 ID</td>
  		</tr>
  	</table>
  </div>
  <br>
  <br>
  <hr>
  <div class="info">
  	 Companion Animal Service Site<br>
  	 반려동물 플랫폼 사이트
  	 <h6><a href="">+더 알아보기</a></h6>
  </div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
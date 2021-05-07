<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/inc/taglib_menu.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 센터</title>
<link rel="stylesheet" type="text/css" href="/Cass/css/common.css">
<link rel="stylesheet" type="text/css" href="/Cass/css/dataCenterCSS.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="js/grape_sex.js"></script>
<script type="text/javascript" src="js/grape_age.js"></script>
<script type="text/javascript" src="js/grape_category.js"></script>
<script type="text/javascript" src="js/grape_select.js"></script>
</head>
<body>
<%

%>
<c:if test="${empty dto.businessId}">
	<script type="text/javascript">
		alert("이 페이지는 사업자 회원만 사용 가능 합니다.");
		window.history.back();
	</script>
</c:if>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	<div id="container">
		<input type="hidden" id="mCount" value="${Count.get(0)}">
		<input type="hidden" id="fCount" value="${Count.get(1)}">
		<input type="hidden" id="10Count" value="${ageCount.get(0)}">
		<input type="hidden" id="20Count" value="${ageCount.get(1)}">
		<input type="hidden" id="30Count" value="${ageCount.get(2)}">
		<input type="hidden" id="40Count" value="${ageCount.get(3)}">
		<input type="hidden" id="50Count" value="${ageCount.get(4)}">
		<input type="hidden" id="cateCount1" value="${categoryCount.get(0)}">
		<input type="hidden" id="cateCount2" value="${categoryCount.get(1)}">
		<input type="hidden" id="cateCount3" value="${categoryCount.get(2)}">
		<input type="hidden" id="cateCount4" value="${categoryCount.get(3)}">
		<c:choose>
			<c:when test="${selectMCount != null }">
				<input type="hidden" id="selectCount1" value="${selectMCount.get(0)}">
				<input type="hidden" id="selectCount2" value="${selectMCount.get(1)}">
				<input type="hidden" id="selectCount3" value="${selectMCount.get(2)}">
				<input type="hidden" id="selectCount4" value="${selectMCount.get(3)}">
			</c:when>
			<c:when test="${selectFCount != null }">
				<input type="hidden" id="selectCount1" value="${selectFCount.get(0)}">
				<input type="hidden" id="selectCount2" value="${selectFCount.get(1)}">
				<input type="hidden" id="selectCount3" value="${selectFCount.get(2)}">
				<input type="hidden" id="selectCount4" value="${selectFCount.get(3)}">
			</c:when>
			<c:when test="${selectAge != null }">
				<input type="hidden" id="selectCount1" value="${selectAge.get(0)}">
				<input type="hidden" id="selectCount2" value="${selectAge.get(1)}">
				<input type="hidden" id="selectCount3" value="${selectAge.get(2)}">
				<input type="hidden" id="selectCount4" value="${selectAge.get(3)}">
			</c:when>
		</c:choose>
			<br>
			<br>
			<br>
			<div align="center">
			  	<div id="sexPiChart"></div>
			   	<div id="agePiChart"></div>
			   	<div id="categoryPiChart"></div>
				<div id="selectPiChart"></div>
			</div>
		  <div id="selectForm">
		  <form action="${CONTEXT_PATH}/cass/frontController?action=selectAgeDataCenter" method="post">
			   <select name="age">
			   	<option>==나이==</option>
			   	<option value="age10">10대</option>
			   	<option value="age20">20대</option>
			   	<option value="age30">30대</option>
			   	<option value="age40">40대</option>
			   	<option value="age50">50대</option>
			   </select>
			   <input type="submit" value="적용" >
		  </form>
		  <form action="${CONTEXT_PATH}/cass/frontController?action=selectSexDataCenter" method="post">
			   <select name="sex">
				   	<option>==성별==</option>
				   	<option value="M">남자</option>
				   	<option value="F">여자</option>
			   </select>
			   <input type="submit" value="적용">
		  </form>
		  
		   
		   </div>
		   <div id="jsonDown">
			   <form action="${CONTEXT_PATH}/cass/frontController?action=jsonDown" method="post">
				   <input type="submit" value="json 파일 다운로드" >
			   </form>
		   </div>
	</div>
	<jsp:include page="/inc/footer_menu.jsp"/>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/inc/taglib_menu.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 센터</title>
<link rel="stylesheet" type="text/css" href="/Cass/css/common.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="js/grape_sex.js"></script>
<script type="text/javascript" src="js/grape_age.js"></script>
<script type="text/javascript" src="js/grape_category.js"></script>
</head>
<body>
<center>
<input type="hidden" id="mCount" value="${Count.get(0)}">
<input type="hidden" id="fCount" value="${Count.get(1)}">
<jsp:include page="/inc/header_menu.jsp"/>

	<br>
	<br>
	<br>
   <div id="sexPiChart" style="float: left;padding-left:120px; width: 33%; position: absolute;"></div>
   <div id="agePiChart" style="float: left; padding-left:600px;width: 20%;position: absolute;" ></div>
   <div id="categoryPiChart" style="float: left;padding-left:900px; width: 33%;position: absolute;"></div>
</center>
<div align="center">
<jsp:include page="/inc/footer_menu.jsp"/>
</div>
</body>
</html>
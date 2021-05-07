<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾을 수 없는 페이지</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="../css/pointPage.css">
<style type="text/css">
#pre {
	width: 500px;
	margin: 0 auto;
	font-size: 1.1em;
}
</style>
</head>
<body>
<div id="wrapper">
<jsp:include page="/inc/header_menu.jsp" />
<div id="container">
<div id="pre">
<pre>
죄송합니다.
요청하신 페이지를 찾을 수 없습니다.
방문하시려는 페이지의 주소가 잘못 입력되었거나,
페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.

입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.

관련 문의사항은 고객센터에 알려주시면 친절하게 안내해 드리겠습니다.

감사합니다.
</pre>
</div>
</div>
<jsp:include page="/inc/footer_menu.jsp" />
</div>
</body>
</html>
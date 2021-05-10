<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html style="height: 50%">
<head>
<meta charset="UTF-8">
<title>찾을 수 없는 페이지</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pointPage.css">
<style type="text/css">
#pre {
	width: 500px;
	margin: 0 auto;
	font-size: 1.5em;
}

pre {
	font-family:'InkLipquid';
}

@font-face{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');
    }
</style>
</head>
<body>

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

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link type="text/css" rel="stylesheet" href="css/common.css">
<div id="header">
   <a href="${CONTEXT_PATH}/welcome.jsp"><div id="header_logo"><h1>CASS</h1></div></a>
   <div id="main_menu">
   		<a href="">반려동물 서비스</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q&A 게시판</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/point/pointController?action=pointMain">포인트 샵</a> <span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/cass/frontController?action=dataCenter">데이터 센터</a> <span class="seperator"></span>
	</div>
	<div id="login_menu">
   		<a href="${CONTEXT_PATH}/member/frontController?action=loginForm">로그인</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/member/frontController?action=memberInputForm">회원가입</a>
   	</div>
</div>
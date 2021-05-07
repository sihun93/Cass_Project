<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link type="text/css" rel="stylesheet" href="css/common.css">
    <link type="text/css" rel="stylesheet" href="css/welcome.css">
    
<div id="header">
   <a href="${CONTEXT_PATH}/welcome.jsp"><div id="header_logo"><h1>CASS</h1></div></a>
   <div id="main_menu">
   		<a href="">반려동물 서비스</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q&A 게시판</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/point/pointController?action=pointMain">포인트 샵</a> <span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/cass/frontController?action=dataCenter">데이터 센터</a> <span class="seperator"></span>
	</div>
	<div id="login_menu">
   		<a href="${CONTEXT_PATH}/member/frontController?action=logout">로그아웃</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/member/frontController?action=myInfoForm">내정보조회</a><span class="seperator">|</span>
   		<div class="dropdown">회원관리 ▼
   		<div class="drop">
  		<div class="dropdown-content">
   		<a href="${CONTEXT_PATH }/member/frontController?action=memberList">회원전체조회</a>
   		<a href="${CONTEXT_PATH }/business/frontController?action=businessList">사업자회원전체조회</a>
   		<a href="${CONTEXT_PATH }/member/frontController?action=pointModifyForm">회원포인트수정</a>
   		<a href="${CONTEXT_PATH }/cassFind.jsp">회원정보찾기</a> 	
   		</div>
   		</div>
   		</div>
   	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link type="text/css" rel="stylesheet" href="css/management.css">
    <link type="text/css" rel="stylesheet" href="css/welcome.css">
    
    
<div id="header">
   <a href="${CONTEXT_PATH}/welcome.jsp"><div id="header_logo"><h1>CASS</h1></div></a>
   <div id="main_menu">
   		<a href="${CONTEXT_PATH}/MainBoard/mainboardController?action=mainbaordListform&pageNum=1">반려동물 서비스</a><span class="seperator">|</span>
   		<a href="${CONTEXT_PATH}/cass/qboardController?action=qboardList">Q&A 게시판</a><span class="seperator">|</span>
   		<c:if test="${ dto.grade eq 'G' || dto.grade eq 'A' || empty dto.grade  }">
	   		<a href="${CONTEXT_PATH}/point/pointController?action=pointMain">포인트 샵</a> 
   		</c:if>
   		<c:if test="${dto.grade eq 'B' || dto.grade eq 'A' }">
 	  		<a href="${CONTEXT_PATH}/cass/frontController?action=dataCenter">데이터 센터</a> <span class="seperator"></span>
   		</c:if>
	</div>
	<div id="login_menu">
		<c:choose>
			<c:when test="${!empty dto}">
   				<a href="${CONTEXT_PATH}/member/frontController?action=logout">로그아웃</a><span class="seperator">|</span>
			</c:when>
			<c:when test="${empty dto}">
   				<a href="${CONTEXT_PATH}/cassLogin.jsp">로그인</a><span class="seperator">|</span>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${!empty dto }">
		   		<c:if test="${dto.grade eq 'A' || dto.grade eq 'G'}">
			   		<a href="${CONTEXT_PATH}/member/frontController?action=myInfoForm">내정보조회</a>
				</c:if>
				<c:if test="${dto.grade eq 'B'}">
			   		<a href="${CONTEXT_PATH}/business/frontController?action=businessInfoForm">내정보조회</a>
				</c:if>
			</c:when>
			<c:when test="${empty dto}">
		   		<a href="${CONTEXT_PATH}/cassInput.jsp">회원가입</a><span class="seperator">|</span>
		   		<a href="${CONTEXT_PATH }/cassFind.jsp">회원정보찾기</a> 	
			</c:when>
		</c:choose>
   		<c:if test="${dto.grade eq 'A' }">
   		<div class="dropdown">회원관리 ▼
   		<div class="drop">
  		<div class="dropdown-content">
   		<a href="${CONTEXT_PATH }/member/frontController?action=memberList">회원전체조회</a>
   		<a href="${CONTEXT_PATH }/business/frontController?action=businessList">사업자회원전체조회</a>
   		<a href="${CONTEXT_PATH }/member/frontController?action=pointModifyForm">회원포인트수정</a>
   		</div>
   		</div>
   		</div>
   		</c:if>
   	</div>
</div>
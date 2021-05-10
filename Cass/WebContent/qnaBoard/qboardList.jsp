<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.work.model.dto.QboardDto" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
    $(window).scroll(function () {
        var curpos = $(window).scrollTop()+180;
        $(".sky").stop().animate({"top":curpos}); 
    });
});

</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/qboard.css">
<style type="text/css">
#ulTable>li:first-child>ul>li {
	text-align: center;
	background-color: #D1DAE7;
}
</style>
</head>
<body>
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
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	
   <div id="container">
  	<div id="mainWrapper">
		<ul>
			<li class="boardTable">
				<ul id="ulTable">
					<li>
						<ul class="ul">
							<li>No</li>
							<li>제목</li>
							<li>작성자</li>
							<li>작성일</li>
							<c:if test="${dto.grade eq 'A'}">
							<li>삭제</li>
							</c:if>
						</ul>
					</li>
					<c:forEach var="qboardList" items="${qboardList}">
					<li>
						<ul>
							<li>${fn:substring(qboardList.qboardNum,2,6)}</li>
							<li><a href="${CONTEXT_PATH}/cass/qboardController?action=qboardDetail&qboardNum=${qboardList.qboardNum}">${qboardList.qboardTitle}</a></li>
							<li>${qboardList.memberId}</li>
							<li>${qboardList.qboardDate}</li>
							<c:if test="${dto.grade eq 'A'}">
							<li><form action="${CONTEXT_PATH}/cass/qboardController?action=deleteQboard&qboardNum=${qboardList.qboardNum}" method="post">
								<input type="submit"  value="삭제" id="deletebtn"/></form></li>
							</c:if>
						</ul>
					</li>
					</c:forEach>
				</ul>
			</li>
			<li id='liSearchOption'>
				<div>
				<form action="${CONTEXT_PATH}/cass/qboardController?action=searchQboard" method="post">
				<div class="search">
					<select id="selSearchOption" name="selSearchOption">
						<option value="A">제목+내용</option>
						<option value="T">제목</option>
						<option value="C">내용</option>
					</select> 
					<input id="txtKeyWord" name="txtKeyWord"/> <input type="submit" value="검색" id="btn"/>
					</div>
					</form>
					<c:if test="${not empty dto}">
					<div class="btn">
					<form action="${CONTEXT_PATH}/qnaBoard/qboardInput.jsp" method="post">
					<input type="submit" value="글 등록" id="btn"/>
					</form>
					</div>
					</c:if>
				</div>
				<div>
				
				</div>
			</li>
		</ul>
	</div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
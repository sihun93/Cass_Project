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
<title>Q&A 게시판</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<style type="text/css">
body {
	line-height: 2.4em;
}

ul, li {
	text-align:center;
	list-style: none;
	padding: 0;
	margin: 0;
}

#mainWrapper {
	width: 900px;
	margin: 0 auto; /*가운데 정렬*/
}

#mainWrapper>ul>li:first-child {
	font-size: 14pt;
	height: 40px;
	vertical-align: middle;
	line-height: 30px;
}

#ulTable {
	margin-top: 10px;
}

#ulTable>li:first-child>ul>li {
	background-color: #c9c9c9;
	font-weight: bold;
}

#ulTable>li>ul {
	clear: both;
	padding: 0px auto;
	position: relative;
	min-width: 40px;
}

#ulTable>li>ul>li {
	float: left;
	font-size: 10pt;
	border-bottom: 1px solid silver;
	vertical-align: baseline;
}

#ulTable>li>ul>li:first-child {
	width: 10%;
}
#ulTable>li>ul>li:first-child+li {
	width: 45%;
}
#ulTable>li>ul>li:first-child+li+li {
	width: 20%;
} 
#ulTable>li>ul>li:first-child+li+li+li {
	width: 20%;
}

#divPaging {
	clear: both;
	margin: 0 auto;
	margin-top : 90px;
	width: 220px;
	height: 50px;
}

#divPaging>div {
	float: left;
	width: 30px;
	margin: 0 auto;
	text-align: center;
}

#liSearchOption {
	clear: both;
}

#liSearchOption>div {
	margin: 0 auto;
	margin-top: 30px;
	width: auto;
	height: 100px;
}
#btn{
	background-color:rgba(0, 0, 0, 0);
	border:1px solid;
}
.btn{
	margin-left:82%;
	margin-top:-4%;
}
#deletebtn{
	border:none;
}
</style>
</head>
<body>
<div id="wrapper">
	<c:choose>
		<c:when test="${empty bdto}">
			<jsp:include page="/inc/header_menu.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/inc/business_header_menu.jsp" />
		</c:otherwise>
	</c:choose>
   <div id="container">
  	<div id="mainWrapper">
		<ul>
		<li>Q&A게시판</li>
			<li>
				<ul id="ulTable">
					<li>
						<ul>
							<li>No</li>
							<li>제목</li>
							<li>작성자</li>
							<li>작성일</li>
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
			<br>
			<li id='liSearchOption'>
				<div>
				<form action="${CONTEXT_PATH}/cass/qboardController?action=searchQboard" method="post">
					<select id="selSearchOption" name="selSearchOption">
						<option value="A">제목+내용</option>
						<option value="T">제목</option>
						<option value="C">내용</option>
					</select> 
					<input id="txtKeyWord" name="txtKeyWord"/> <input type="submit" value="검색" id="btn"/>
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
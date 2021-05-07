<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.work.model.dto.QboardDto" %>
<%@ page import="com.work.model.dto.AboardDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시글 상세조회</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<style type="text/css">

#mainWrapper {
	margin-left:28%
}

#btn{
	background-color:rgba(0, 0, 0, 0);
	border:1px solid;
}
.left{
	text-align:center;
}
td{
	border-bottom: 1px solid silver;
}
table{
	border-spacing: 10px;
	width:800px;
}
#qList{
margin-left:4%;
margin-top:-1.65%
}

</style>
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />
	<c:forEach var="qboardDetail" items="${qboardDetail}">
   <div id="container">
  	<div id="mainWrapper">
	<table>
	
		<tr>
			<td>제목</td>
			<td>${qboardDetail.qboardTitle}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${qboardDetail.memberId}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${qboardDetail.qboardDate}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
			<c:if test="${not empty qboardDetail.qboardImg}">
			<img src="https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/qboard%2F${qboardDetail.qboardImg}?alt=media" width="300px" height="300px">
			</c:if>
			${qboardDetail.qboardContent}
			</td>
		</tr>
		
		<tr>
			<td>답글</td>
			<td><form action="${CONTEXT_PATH}/cass/qboardController?action=addAboard&qboardNum=${qboardDetail.qboardNum}" method="post">
				<textarea rows="3" cols="60" name="reply" id="reply"></textarea>
				<input type="submit" value="등록" id="btn">
			</form></td>
		</tr>

		<c:forEach var="aboardList" items="${aboardList}">
		<tr>
			<td>관리자 답글</td>
			<td>${aboardList.aboardContent}</td>
		</tr>
		</c:forEach>
	</table>
	<form action="${CONTEXT_PATH}/cass/qboardController?action=updateQboard&qboardNum=${qboardDetail.qboardNum}" method="post">
	<input type="submit" value="수정" id="btn"/>
	</form>
	<form action="${CONTEXT_PATH}/cass/qboardController?action=qboardList" method="post" id="qList">
	<input type="submit" value="글 목록" id="btn"/>
	</form>	
	</div>
</div>
</c:forEach>
   
<jsp:include page="/inc/footer_menu.jsp"/>
</div>
</body>
</html>
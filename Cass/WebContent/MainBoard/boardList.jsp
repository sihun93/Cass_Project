<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="content">
<c:if test="${!empty mainbaordList }">
<c:forEach items="${mainbaordList}" var="mainbaord">
<img src="${mainbaord.mboardImg }">
<div>
<a>${mainbaord.mboardScore }</a>
<textarea rows="" cols="">${mainbaord.mboardInfo }</textarea>
</div>
</c:forEach>
</c:if>
</div>
<div id="pageNumber" style="align-content: center;">
&lt;|
<%	
	int max = Integer.parseInt(request.getParameter("maxPageNum"));
	for(int i = 1 ; i <= max;i++){
%>
	<a href="${CONTEXT_PATH} /MainBoard/mainboardController?action=mainbaordListform&pageNum=<%= i%>"><%= i%>|</a>
<%
	}
%>
&gt;
</div>

</body>
</html>
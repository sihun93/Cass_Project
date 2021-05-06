<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.work.model.dto.QboardDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<style type="text/css">
body {
	line-height: 2em;
	font-family: "맑은 고딕";
}

ul, li {
	list-style: none;
	text-align: center;
	padding: 0;
	margin: 0;
}

#mainWrapper {
	width: 800px;
	margin: 0 auto; /*가운데 정렬*/
}

#mainWrapper>ul>li:first-child {
	text-align: center;
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
	text-align: center;
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
} /*No 열 크기*/
#ulTable>li>ul>li:first-child+li {
	width: 45%;
} /*제목 열 크기*/
#ulTable>li>ul>li:first-child+li+li {
	width: 20%;
} /*작성일 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li {
	width: 20%;
} /*작성자 열 크기*/

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

.left {
	text-align: left;
}
</style>
</head>
<body>
<div id="wrapper">
	<jsp:include page="/inc/header_menu.jsp" />	
   <div id="container">
  	<div id="mainWrapper">
		<ul>
			<li>Q&A 게시판</li>
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
							<li>${qboardList.qboardNum}</li>
							<li><a href="${CONTEXT_PATH}/cass/frontController?action=qboardDetail&qboardNum=${qboardList.qboardNum}">${qboardList.qboardTitle}</a></li>
							<li>${qboardList.memberId}</li>
							<li>${qboardList.qboardDate}</li>
						</ul>
					</li>
					</c:forEach>
				</ul>
			</li>
			<li>
				<div id="divPaging">
					<div>◀</div>
					<div>
					<b>1</b>
					</div>
					<div>2</div>
					<div>3</div>
					<div>4</div>
					<div>5</div>
					<div>▶</div>
				</div>
			</li>
			<li id='liSearchOption'>
				<div>
					<select id='selSearchOption'>
						<option value='A'>제목+내용</option>
						<option value='T'>제목</option>
						<option value='C'>내용</option>
					</select> <input id='txtKeyWord' /> <input type='button' value='검색' />
				</div>
			</li>
		</ul>
	</div>
   </div>

<jsp:include page="/inc/footer_menu.jsp"/>
</div>
 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.work.model.dto.QboardDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	margin: 0 auto; 
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

.left {
	text-align: left;
}
html,body {
	margin:50;
	padding:0;
	height:150%;
	}
#wrapper{
	position:relative;
	min-height:100%;
	}
#header{
	margin-top:30px;
	height:90px;
	background-color:#D1DAE7;
	}
#container{
	padding:20px;
	}
#footer{
	position:absolute;
	bottom:0;
	left:0;
	right:0;
	background-color:#D1DAE7;
	font-size:1.2em;
	height:100px;
	text-align:center;
	}
#main_menu{
	display:flex;
	font-size:1.2em;
	padding-top:33px;
	padding-left:320px;
	}
body{
	background-color:#F4F3EF;
	}
#header_logo{
	font-size:1.5em;
	font-family:'InkLipquid';
	float:left;
	margin-top:-15px;
	margin-right:250px;
	margin-left:50px;
	height: 80px;
	}
@font-face{
	font-family:'InkLipquid';
	font-weight:normal;
	font-style:normal;
	font-color:white;
    src:url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/InkLipquid.woff')format('woff');
    }
a{
	text-decoration:none;
	}
a:visited{
	text-decoration:none;
	color:black;
	}
a:hover{
	text-decoration:none;
	color:152a84;
	}
a:active{
	text-decoration:none;
	color:black;
	}
#login_menu{
	padding:8px 15px;
	font-size: 1.2em;
	float:right;
	margin-top:-35px; 
	}
span.seperator{
	padding:0px 10px;
	margin:0 5px;
	font-size:1.0em;
	color:gray;
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
							<li>${qboardList.qboardTitle}</li>
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
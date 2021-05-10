<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.work.model.dto.BusinessMemberDto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Companion Animal Service Site</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="../css/checkForm.css">
</head>
<body>
<h2>아이디 중복 확인 결과</h2>
<%
	String memberId = request.getParameter("memberId");
	String cId = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	BusinessMemberDto dto = new BusinessMemberDto();
	try{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Oracle11g");
		con = ds.getConnection();
		pstmt = con.prepareStatement("SELECT * FROM BUSINESS WHERE BUSINESS_ID = ?");
		pstmt.setString(1, memberId);
		rs = pstmt.executeQuery();
		if(rs.next()){
			cId = rs.getString("business_Id");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if(pstmt != null ) pstmt.close();
		 if(con != null) con.close();
	}
	if(cId == null){
		out.println("<h3>"+memberId+"</h3>");
		out.println("<h3>사용 가능한 아이디입니다.</h3>");
		out.println("<a href='javascript:apply(\"" + memberId + "\")'>적용</a>");
	
%>
<%
	}else{
		out.println("<h3 class='ne'>해당 아이디는 사용하실 수 없습니다.</h3>");
	}
%>
<hr>
	<a href="javascript:history.back()" class="bt">재시도</a>
	&nbsp; &nbsp;
	<a href="javascript:window.close()" class="bt">창닫기</a>
	
	
	<script>
	function apply(cId){
		opener.document.getElementById('memberId').value=cId;
		window.opener.document.getElementById('memberId').readOnly = true;
		window.opener.document.getElementById("memberPw").focus();
		window.close();
	}
	</script>
</body>
</html>
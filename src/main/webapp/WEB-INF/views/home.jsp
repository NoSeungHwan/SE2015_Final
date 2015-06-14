<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<center> 명지대학교 수강 처리 사이트 입니다.
<P>  The time on the server is ${serverTime}. </P>

	<form action="/loginController/login.do" method="post">
		사용자 아이디<input type="text" name="userID"> <br/>
		비밀번호<input type="password" name="userPassword"> <br/>
		<input type="submit" value="로그인">
	</form>
	
	<a href="">회원가입</a>
	
<h6 color ='red'>만일 로그인이 되지 않는다면 관리자에게 문의 바랍니다.</h6>

	<form action="loginController/createAccount.do" method="post">
		<input type="submit" value="계정 생성">
	</form>
</center>	


	<form action="loginController/createDB.do" method="post">
		<input type="submit" value="데이터베이스 생성">
	</form>
	
	<form action="loginController/createTable.do" method="post">
		<input type="submit" value="테이블 생성">
	</form>
	
</body>
</html>





<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
<h1>회원가입 페이지</h1>
<form action="/loginController/login.do" method="post">
		아이디<input type="text" name="userID"> <br/>
		이름<input type="text" name="userName"> <br/>
		비밀번호<input type="password" name="userPassword"> <br/>
		분류 <input type="radio" name = "userStudent">학생
		<input type="radio" name = "userProfes">교수
		<input type="radio" name = "userAdmin">관리자 
		<input type="submit" value="가입 신청하기">
	</form>

</body>
</html>
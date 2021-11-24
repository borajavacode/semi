<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH escape</title>
<link rel="stylesheet" href="/css/memberDefault.css">
<style>
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container row col-md-5" style="float: none;margin:100px auto;">
		<br>
		<form action="/login" method="post" name="login">
		<h2 class="text-fff">로그인</h2>
			<div class="form-group">
				<label class="form-label mt-4 text-fff">아이디</label>
				<div class="form-floating ">
					<input type="text" class="form-control float-center" id="loginId" name="loginId" style="width: 100%;border-radius: 10px;" placeholder="아이디입력"> 
				</div>
				<label class="form-label mt-4 text-fff">패스워드</label>
				<div class="form-floating">
					<input type="password" class="form-control" id="loginPw" name="loginPw" placeholder="패스워드 입력" style="width: 100%;border-radius: 10px;"> 
				</div>
				<br>
				<button type="submit" class="btn btn-danger" style="width: 100%; border-radius: 10px;">로그인</button>
				<div class="mt-4">
					<span><a href="/find" class="text-danger" style="font-size: medium;border-radius: 10px;">아이디/비밀번호 찾기</a></span>
					<span><a href="/joinFrm" class="text-info float-end" style="font-size: medium;border-radius: 10px;">회원가입</a></span>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
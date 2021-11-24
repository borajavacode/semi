<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH escape</title>
<link rel="stylesheet" href="/css/memberDefault.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container text-fff" style="float: none; margin:120px auto;width: auto;">
		<form action="/findId" method="post" name="findId" style="display: inline-block;padding-right: 200px;">
			<legend class="text-fff">아이디 찾기</legend>
			<div class="form-group">
				<label class="control-label text-fff" for="memberName">이름</label>
				<input type="text" name ="memberName" id="memberName" class="form-control" placeholder="이름 입력 ex.김한국"
				style="width: 50%;width: 400px;border-radius: 10px;" required>
			</div>
			<br>
			<div class="form-group">
				<label class="control-label text-fff" for="phone">전화번호</label>
				<input type="text" name ="phone" id="phone" class="form-control" placeholder="010-0000-0000"
				style="width: 50%;width: 400px;border-radius: 10px;" required>
			</div>
			<br>
			<button type="submit" class="btn btn btn-warning btn-block" style="width: 400px;border-radius: 10px;">아이디 찾기</button>
		</form>
		<form action="/findPw" method="post" name="findPw" style="display: inline-block;">
			<legend class="text-fff">비밀번호 찾기</legend>
			<div class="form-group">
				<label class="control-label text-fff" for="memberId">아이디</label>
				<input type="text" name ="memberId" id="memberId" class="form-control" placeholder="아이디 입력 ex.test1"
				style="width: 50%;width: 400px;border-radius: 10px;" required>
			</div>
			<br>
			<div class="form-group">
				<label class="control-label text-fff" for="phone">전화번호</label>
				<input type="text" name ="phone" id="phone" class="form-control" placeholder="010-0000-0000"
				style="width: 50%;width: 400px;border-radius: 10px;" required>
			</div>
			<br>
			<button type="submit" class="btn btn btn-warning btn-block" style="width: 400px;border-radius: 10px;">비밀번호 찾기</button>
		</form>
		<div style="text-align: center;margin-top: 100px; ">
		<br><br>
			<span class="text-muted">모두 기억이 안나신다고요??</span><a class="text-info" style="display: inline-block;" href="/checkOpen">여기를 클릭해주세요</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
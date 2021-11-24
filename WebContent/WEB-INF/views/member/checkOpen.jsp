<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH escape</title>
<link rel="stylesheet" href="/css/memberDefault.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container text-fff" style="margin: 150px auto;">
		<h1>고객센터</h1>
		<hr>
		<div class="innerContainer row" style="float: none;margin: 0 auto;text-align: center;">
			<fieldset style="padding: 10px auto 30px auto;">
			<br><br>
			<h2 style="color: #bf2222;"> 고객센터 : 02 - 8082 - 8282</h2>
			<h3> 대표 전화 : 02 - 2163 - 8560</h3>
			<h6> 문의사항은 Q&A로도 이용 가능합니다</h6> 
			<br><br>
			<p>대표이사 | 이윤수</p>
			<p>대표주소 | 서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F, 20F</p>
			<p>전화번호 | 02-2163-8560</p>
			<p>Copyrightⓒ 2021 <span data-text="team MOHB">team MOHB</span>. All right reserved</p>
			<br>
   			<div class="form-gorup text-center mb-4">
				<a href="/" class="btn btn-outline-warning">메인으로</a>
				<a href="javascript:history.go(-1)" class="btn btn-outline-secondary">뒤로가기</a>
			</div>
			</fieldset>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
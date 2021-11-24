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
	body{
		color: #fff; 
	}
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/myheader.jsp"/>
	<div class="container">
		<table border="1" class="table" style="width: 900px; float: none;margin: 0 auto;margin-top: 130px;">
			<tbody>
				<tr class="table-thema">
					<td rowspan="3" style="width: 250px;"><img src="/img/Detective.jpg" style="width: 100%;height: 100%;"></td>
					<td style="width: 150px;height: 80px;">회원명</td>
					<td style="text-align: center;">${sessionScope.m.memberName } 님</td>
				</tr>
				<tr class="table-thema">
					<td style="height: 80px;">전화전호</td>
					<td style="text-align: center;">${sessionScope.m.phone }</td>
				</tr>
				<tr class="table-thema">
					<td style="height: 80px;">가입일</td>
					<td style="text-align: center;">${sessionScope.m.enrollDate }</td>
				</tr>
				<tr class="table-thema">
					<td style="text-align: center;"><h4>${sessionScope.m.memberId }님</h4></td>
					<td colspan="2"><h4 class="text-center">M Y P A G E</h4></td>
				</tr>
			</tbody>
		</table>
		<div class="row col" style="float:none;margin: 0 auto">
			<a type="button" href="/myInfo" class="btn-my" style="width: 450px;height: 200px;line-height: 170px;">내 정보 관리</a>
			<a type="button" href="/myRv" class="btn-my" style="width: 450px;height: 200px;line-height: 170px;">예약확인</a>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
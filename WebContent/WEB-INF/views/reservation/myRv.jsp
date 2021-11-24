<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 예약 내역</title>
<link rel="stylesheet" href="/css/bootstrap.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
@font-face {
	font-family: "g_r";
	src: url(/font/GothicA1-Regular.ttf);
}

body {
	font-family: "g_r";
	color: #111111;
}
.head>button {
	width: 30%;
}
h3,h5{
color:#ffff;
}

</style>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/myheader.jsp"/>


<body>
<div class="container" style="margin-top:150px;">
<h3>내 예약 내역</h3><div style="float:right;"><h5>최근 10건의 내역만 조회 가능합니다.</h5></div>
<table class="table table-th table-hover">

<tr class="table-thema">
<th>예약번호</th><th>테마명</th><th>지점</th><th>날짜</th><th>시간</th><th>인원</th>
</tr>

<c:forEach items="${rvList }" var="rv">
<tr class="table-dark">
<td>${rv.rvNo }</td><td>${rv.rvThema }</td><td>${rv.rvPoint }</td><td>${rv.rvDate }</td><td>${rv.rvTime }</td><td>${rv.rvPerson }</td>
</tr>
</c:forEach>
</table>

</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script src="/js/main.js"></script>
</body>
</html>
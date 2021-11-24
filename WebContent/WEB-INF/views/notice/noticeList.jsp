<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- jstl을 사용하기위해 기본적으로 넣어줘야하는 항목 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- noev css -->
	<link rel="stylesheet" href="/css/noev.css">
	<div class="container" style="padding-top: 15px;margin-top:90px;">
		<fieldset>
			<legend><h2>공지사항</h2></legend>
			<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1 }">
				<div style="text-align: center; margin-top: 50px;">
					<a class="btn btn-info writeBtn" href="/noticeWriteFrm" id="write">글쓰기</a>
				</div>
			</c:if>
			<div id="searchBox">
				<form action="/searchNotice" method="post">
					<c:choose>
						<c:when test="${not empty type }">
							<select class="form-control" name="type">
							<c:choose>
								<c:when test='${type eq "title" }'>
									<option value="title" selected>제목</option>
									<option value="writer">작성자</option>									
								</c:when>
								<c:otherwise>
									<option value="title">제목</option>
									<option value="writer" selected>작성자</option>
								</c:otherwise>
							</c:choose>
							</select>
						</c:when>
						<c:otherwise>
							<select class="form-control" name="type">
								<option value="title">제목</option>
								<option value="writer">작성자</option>
							</select>
						</c:otherwise>
					</c:choose>
					<input type="text" name="keyword" class="form-control" value="${keyword }">
                 	<button type="submit" class="btn btn-info">검색</button>
               	</form>
               	</div>
				<table class="table-th table-sm"style="width:100%;">
				<tr class="table-thema">
					<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
				</tr>
				<c:forEach items="${list }" var="n" varStatus="i">
					<tr class="table-dark">
						<td>${start + i.index }</td>
						<td style="text-align: left; width:60%;">
							<a href="/noticeView?noticeNo=${n.noticeNo }">
								${n.noticeTitle }
							</a>
							[${n.ncCount}]
						</td>
						<td>${n.noticeWriter }</td>
						<td>${n.regDate }</td>
						<td>${n.noticeReadCount }</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
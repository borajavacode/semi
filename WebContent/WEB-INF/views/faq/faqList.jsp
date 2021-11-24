<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/fontawesome/css/all.css">
<script type="text/javascript" src="/fontawesome/js/all.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/font.css">
<style>
	#searchBox{
	display: inline-block;
	width: 40%;
	float: left
	}
	select[name=type]{
	display : inline-block;
	width: 30%
	}
	input[name=keyword]{
	display : inline-block;
	width: 50%
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container" style="padding-top:60px" >
		<fieldset>
			<legend><h2>Q&A 게시판</h2></legend>
			<hr>
			<div id="searchBox" style="margin-top: 0;float: right;">
			<form action="/searchFaq" method="post">
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
				<button type="submit" class="btn btn-primary">검색</button>
			</form>
			</div>
			<table class="table-th table" style="width: 100%; ">
				<tr class="table-thema">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list }" var="f" varStatus="i">
					<tr class="table-dark" id="list-board">
						<td>${start+ i.index }</td>
						<td style="text-align: left;" width="50%;">
						<c:choose>
							<c:when test="${f.faqStatus == 1 }">
							<a href='/faqView?faqNo=${f.faqNo }' style="color: white;">${f.faqTitle }</a>[${f.faqCount }]
							</c:when>
							<c:when test="${f.faqStatus== 2}">
								<c:choose>
									<c:when test="${(sessionScope.m.memberId eq f.faqWriter) or sessionScope.m.memberLevel == 1 }">
										<a href='/faqView?faqNo=${f.faqNo }' style="color: white;"><i class="fas fa-lock-open"></i> ${f.faqTitle }</a>[${f.faqCount }]
									</c:when>
									<c:otherwise>
									<p style="color: white; margin: 0;"><i class="fas fa-lock "></i> 비공개글</p>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
						</td>
						<td>
						<c:choose>
							<c:when test="${f.faqStatus==1 }">
							${f.faqWriter }
							</c:when>
							<c:when test="${f.faqStatus==2 }">
								<c:choose>
									<c:when test="${(sessionScope.m.memberId eq f.faqWriter) or sessionScope.m.memberLevel == 1 }">
										${f.faqWriter }
									</c:when>
									<c:otherwise>
									<i class="fas fa-lock "></i>
									비공개
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
						</td>
						<td>${f.regDate }</td>
					</tr>
				</c:forEach>
			</table>
			<div style="overflow: hidden;">
			<div id="pageNavi" style="width: 300px; float: left; margin-left:450px;">${pageNavi }</div>
			<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 2}">
				<div style=" float: right;">
					<a class="btn btn-info writeBtn" href = "/faqWriteFrm" >질문하기</a>
				</div>
			</c:if>						
			</div>
		</fieldset>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
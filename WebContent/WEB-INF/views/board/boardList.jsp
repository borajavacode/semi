<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	color: #111111;
}

#searchBox {
	display: inline-block;
	width: 50%;
	margin: 0;
}

select[name=type] {
	display: inline-block;
	width: 30%
}

input[name=keyword] {
	display: inline-block;
	width: 50%
}

.sub-banner {
	widows: 100%
}

.sub-title {
	background-size: cover;
	text-align: center;
}

.tab_title li {
	list-style: none;
	float: left;
	width: 120px;
	padding: 10px 15px;
	text-align: center;
	border: 1px solid #ededed;
}

.tab_content {
	clear: both;
}
</style>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/font.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="main">
		<div class="container" style="margin-bottom: 200px">
			<div class="tab_content" style="padding-top: 100px;">
				<fieldset>
					<legend><h2>자유게시판</h2></legend>
					<hr>
					<div class="tab_title" style="overflow: hidden;">
						<div style="float: left; margin-top: 15px">
							<ul style="padding: 0; margin: 0;">
								<c:choose>
									<c:when test="${listFind == 1 }">
										<li style="background-color: #171A21;"><a
											href="/boardList?reqPage=1&listFind=1"
											style="color: #ededed; font-weight: bold;">전체게시판</a></li>
										<li><a href="/boardList?reqPage=1&listFind=2"
											style="color: #DDDDDE;">자유게시판</a></li>
										<li><a href="/boardList?reqPage=1&listFind=3"
											style="color: #DDDDDE;">동료 모집</a></li>
									</c:when>
									<c:when test="${listFind == 2 }">
										<li><a href="/boardList?reqPage=1&listFind=1"
											style="color: #DDDDDE;">전체게시판</a></li>
										<li style="background-color: #171A21;"><a
											href="/boardList?reqPage=1&listFind=2"
											style="color: #ededed; font-weight: bold;">자유게시판</a></li>
										<li><a href="/boardList?reqPage=1&listFind=3"
											style="color: #DDDDDE;">동료 모집</a></li>
									</c:when>
									<c:when test="${listFind == 3 }">
										<li><a href="/boardList?reqPage=1&listFind=1"
											style="color: #DDDDDE;">전체게시판</a></li>
										<li><a href="/boardList?reqPage=1&listFind=2"
											style="color: #DDDDDE;">자유게시판</a></li>
										<li style="background-color: #171A21;"><a
											href="/boardList?reqPage=1&listFind=3"
											style="color: #ededed; font-weight: bold;;">동료 모집</a></li>
									</c:when>
								</c:choose>
							</ul>
						</div>
						<div id="searchBox" style="float: right; margin-left:40px; margin-top: 0;">
							<form action="/searchBoard?listFind=${listFind }" method="post">
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
								<input type="text" name="keyword" class="form-control"
									value="${keyword }" style="width: 300px">
								<button type="submit" class="btn btn-primary" >검색</button>
							</form>
						</div>
					</div>
					<table class="table-th table" style="width: 100%; ">
						<tr class="table-thema">
							<th>글종류</th>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							<c:if test="${listFind == 3}">
								<th>모집일</th>
								<th>모집여부</th>
							</c:if>
							<c:forEach items="${list }" var="b" varStatus="i">
								<c:if test="${b.boardLevel ==1 || b.boardLevel==2 }"></c:if>
								<tr class="table-dark" id="list-board">
									<td><c:choose>
											<c:when test="${b.boardLevel==1 }">
												자유
												<input type="hidden" value="${b.boardLevel }">
											</c:when>
											<c:when test="${b.boardLevel==2 }">
												모집
												<input type="hidden" value="${b.boardLevel }">
											</c:when>
										</c:choose></td>
									<td>${start+i.index }</td>
									<td style="text-align: left; width: 40%"><a
										href='/boardView?boardNo=${b.boardNo }' style="color: white;">
											${b.boardTitle }</a> [${b.bdCount }]</td>
									<td>${b.boardWriter }</td>
									<td>${b.regDate }</td>
									<td>${b.readCount }</td>
									<c:if test="${listFind == 3}">
										<td>${b.deadLine }</td>
										<td class="findStatus"
											style="color: #3c6a9b; font-weight: bold;">모집중 <input
											type="hidden" value="${b.deadLine }" class="findDay">
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</tr>
					</table>
					<div style="overflow: hidden;">
					<div id="pageNavi" style="width: 420px; margin-left:450px; float: left;">${pageNavi }</div>
					<c:if test="${not empty sessionScope.m}">
							<div style="float: right; ">
								<a class="btn btn-info writeBtn" href="/boardWriteFrm">글쓰기</a>
							</div>
						</c:if>					
					</div>
				</fieldset>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$(".tab_title a").click(function() {
				var idx = $(this).index();

			})
		});
		var date = new Date();
		var findDay = $(".findDay");
		var today = formatDate(date)
		for (var i = 0; i < 20; i++) {
			var refindDay = findDay.eq(i).val().replaceAll("-", "");
			console.log(refindDay);
			if (refindDay <= today) {
				$(".findStatus").eq(i).html("모집마감");
				$(".findStatus").eq(i).css("color", "#bf2222");
				$(".findStatus").eq(i).css("font-weight", "bold");
				//$(".findStatus").eq(i).parent().hide();

			}
		}
		function formatDate(date) {
			var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
					+ d.getDate(), year = d.getFullYear();
			if (month.length < 2)
				month = '0' + month;
			if (day.length < 2)
				day = '0' + day;
			return [ year, month, day ].join('');
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
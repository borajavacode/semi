<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="/css/memberDefault.css">
	<script>
		/* 1분마다 새로고침 */
		function pagestart() {
			window.setTimeout("pagereload()", 60000);
		}
		function pagereload() {
			location.reload();
		}
	</script>
</head>

<body onload="pagestart();">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/myheader.jsp" />
	<div class="container row text-fff" style="margin: 150px auto 0 auto;float: none">
		<h2 class="text-fff">통계</h2>
		<hr class="text-fff">
		<div class="innserContent1">
			<h3 style="display: inline-block;">회원가입한 회원건</h3><a href="/memberCtl?reqPage=1" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">회원리스트로 이동</a>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.memToday }</td><td>${chart.memWeek }</td><td>${chart.memMonth }</td><td>${chart.totalMem }</td>
				</tr>
			</table>
		</div>
		<div class="innserContent2">
			<h3 style="display: inline-block;">게시물건 </h3><a href="/boardList?reqPage=1&listFind=1" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">게시물로 이동</a>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.boToday }</td><td>${chart.boWeek }</td><td>${chart.boMonth }</td><td>${chart.totalBo }</td>
				</tr>
			</table>
		</div>
		<div class="innserContent3">
			<h3 style="display: inline-block;">예약건</h3><a href="/rvList" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">예약리스트로 이동</a>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.rvToday }</td><td>${chart.rvWeek }</td><td>${chart.rvMonth }</td><td>${chart.totalRv }</td>
				</tr>
			</table>
		</div>
		<div class="innserContent4">
		<div>
			<h3 style="display: inline-block;">공지건</h3><a href="/noticeList?reqPage=1" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">공지로 이동</a>
		</div>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.noToday }</td><td>${chart.noWeek }</td><td>${chart.noMonth }</td><td>${chart.totalNo }</td>
				</tr>
			</table>
		</div>
		<div class="innserContent5">
			<h3 style="display: inline-block;">포토건</h3><a href="/photoList?reqPage=1" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">갤러리로 이동</a>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.phToday }</td><td>${chart.phWeek }</td><td>${chart.phMonth }</td><td>${chart.totalPh }</td>
				</tr>
			</table>
		</div>
		<div class="innserContent5">
			<h3 style="display: inline-block;">문의건</h3><a href="/faqList?reqPage=1" class="btn btn-secondary btn-sm float-end" style="margin-bottom: 10px;">Q&A로 이동</a>
			<table class="table-th table-hover table-sm">
				<tr class="table-thema">
					<th>오늘</th><th>일주일간</th><th>한달간</th><th>총</th>
				</tr>
				<tr class="table-dark">
					<td>${chart.faqToday }</td><td>${chart.faqWeek }</td><td>${chart.faqMonth }</td><td>${chart.totalFaq }</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>

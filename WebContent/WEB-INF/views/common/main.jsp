<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
<link href="/css/ThemaList.css" rel="stylesheet">
<link rel="stylesheet" href="/css/main.css">
<style>
	.container h1{
		text-align: center;
		color: #fff;
	}
	.popThema, .mainNotice, .mainEvent{
		text-align: center;
		margin-top: 30px;
		margin-bottom: 30px;
	}
	#active{
		background : #df723d;
		width : 80px;
		height : 150px;
		text-align: center;
		font-size: 14px;
		line-height : 150px;
		color : #fff;
		border-radius: 10px;
		margin: 10px auto;
	}
</style>
</head>
<body>
	<div class="fullscreen-bg">
		<img src="/img/main/fullscreen-bg2.png">
	</div>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="popThema">
			<h1><img src="/img/main/hand1.png"> 인 기 테 마 <img src="/img/main/hand2.png"></h1>
			<div class="themaWrapper"></div>
			<button style="display: none;" id="popThema"></button>
			<button class="btn btn-my" id="themaListBtn">더 많은 테마 보러가기</button>
		</div>
		<br>
		<div class="mainNotice">
			<h1><img src="/img/main/hand1.png"> 공 지 사 항 <img src="/img/main/hand2.png"></h1>
			<table class="table-th table-sm"style="width:80%; margin: 0 auto; margin-top: 30px; margin-bottom: 30px;">
				<c:forEach items="${list }" var="n" varStatus="i">
					<tr class="table-dark">
						<td style="text-align: left; width:60%; padding-left: 20px;">
							<a href="/noticeView?noticeNo=${n.noticeNo }" style="color:#fff;">
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
			<button class="btn btn-my" id="NoticeBtn">공지사항 더보기</button>
		</div>
		<div class="mainEvent">
			<h1><img src="/img/main/hand1.png"> E V E N T <img src="/img/main/hand2.png"></h1>
			<div class="eventWrapper" style="width: 80%; margin: 0 auto; margin-top: 30px; margin-bottom: 30px;">
				<c:forEach items="${list2 }" var="p" varStatus="i">
					<table class = 'table'>
						<tr class='table-dark' style='vertical-align:middle;'>
							<td rowspan='3' style='vertical-align:middle;'><img style='width:200px;higth:200px;' src='upload/event/${p.mainEvent }'></td>
							<td style='width:65%; text-align: left;'>${p.eventTitle }</td>
							<td class='bo_btn' rowspan='3'><p id='active'>진행중</p></td>
						</tr>
						<tr class='table-dark' style='height:30px; text-align: left;'>
							<td>${p.eventWriter } | ${p.eventReadcount }</td>
						</tr>
						<tr class='table-dark' style='vertical-align:middle;'>
							<td style="text-align: left;"><a href='/eventView?eventNo=${p.eventNo }' style="color:#fff;">자세히보기</a></td>
						</tr>
					</table>
				</c:forEach>
			</div>
			<button class="btn btn-my" id="EventBtn">이벤트 더보기</button>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	$("#themaListBtn").click(function(){
		location.href="/themaList";
	});
	$("#NoticeBtn").click(function(){
		location.href="/noticeList?reqPage=1";
	});
	$("#EventBtn").click(function(){
		location.href="/eventList";
	});
	// 인기 테마 불러오기
	$(function(){
		$.ajax({
			url:"/popThema",
			type:"get",
			success: function(data){
				for(var i=0; i<data.length;i++){
					var t = data[i];
					var html = "<li class='thema'>";
					html += "<a href='/themaView?themaNo="+t.themaNo+"'>";
					html += "<div class='themaImg'>";
					html += "<img src='/img/thema/"+t.filepath+"' style='width:175px; height:175px;'>";
					if(t.themaName.length > 10){
						html += "<h3>"+t.themaName.substring(0,7)+"ㆍㆍㆍ</h3></div>";							
					}else{
						html += "<h3>"+t.themaName+"</h3></div>";
					}
					html += "<h4>"+t.themaAddr+"</h4><div>";
					for(var j=0; j<t.difficult; j++){
						html += "<img src='/img/key.png'>";
					}
					html += "</div></a></li>";
					$(".themaWrapper").append(html);
				}
			}
		});
	});
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
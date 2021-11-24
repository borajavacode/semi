<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>포토갤러리</title>
<style>
	#photoGallery{
		margin-top: 100px;
	}
	.container>.title{
		text-align: center;
	}
	.title p{
		margin: 0;
	}
	#pageNavi{
		display:flex;
		width:420px;
		margin: 0 auto;
		justify-content: center;
		margin-top: 20px;
	}
	.writeBtnWrapper{
		overflow: hidden;
	}
	.writeBtn{
		float: right;
	}
	.photoWrapper{
		background-color: #343a40;
		padding: 30px;
		display: flex;
		overflow: hidden;
		flex-wrap:wrap;
		gap: 1em;
		align-items: center;
		margin: 0 auto;
		justify-content: center;
	}
	.eachPhoto{
		margin-right: 15px;
		width: 30%;
		flex-basis:auto;
		flex-grow:1;
		flex-shrink:0;
		min-width:380px;
		max-width:380px;
		min-height: 290px;
		max-height: 290px;
		overflow:hidden;
		text-align: left;
	}
	.eachPhoto:nth-child(3n){
		margin-right: 0px;
	}
	.eachPhoto img{
		width:100%;
	}
	.eachPhoto p{
		margin: 0;
	}
	.photo{
		text-align: center;
		height: 200px;
		overflow: hidden;
		display: flex;
		align-items: center;
	}
	.photoText{
		margin-top:10px;
	}
	.photoText:hover{
		cursor: pointer;
	}
	.orderWrapper{
		overflow: hidden;
		margin-bottom: 10px;
	}
	.orderBox{
		float: left;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" id="photoGallery">
		<div class="title">
			<h1 style="color: #fff">PHOTO GALLERY</h1>
			<p>KH ESCAPE 탈출러들의 기록장입니다</p>		
		</div>
		<div class="orderWrapper">
			<div class="orderBox">
				<a class="btn btn-primary orderBtn" href="/photoList?reqPage=1">최신순</a>
				<a class="btn btn-primary orderBtn" href="/photoOrder?type=1">댓글순</a>
				<a class="btn btn-primary orderBtn" href="/photoOrder?type=2">조회순</a>
			</div>
			<div class="writeBtnWrapper">
				<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1 }">
					<a class="btn btn-primary writeBtn" href="/photoWriteFrm">글쓰기</a>					
				</c:if>
			</div>		
		</div>
		<div class="photoWrapper">
				<c:forEach items="${list }" var="p" varStatus="i">
					<div class="eachPhoto">
						<div class="photo" onclick="location.href='photoView?photoNo=${p.photoNo}';">
								<img src="/upload/photo/${p.photoFilepath }">
						</div>
						<div class="photoText" onclick="location.href='photoView?photoNo=${p.photoNo}';">
							<h4>${p.photoTitle } [${p.pcCount }]</h4>
							<p><b>작성자</b> ${p.photoWriter} | <b>작성일</b> ${p.regDate } | <b>조회수</b>  ${p.readCount}</p>
							<p>${p.photoContentBr }</p>					
						</div>
					</div>
   				</c:forEach>
		</div>
			<div id="pageNavi">${pageNavi }</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/css/myheader.css">
<div style="margin-top:75px; position:fixed;" >
<div class="mynav" style="background-color:#fff; text-align:center; height:40px; line-height:40px;" >
<ul>
<li></li>
<c:choose>
	<c:when test="${sessionScope.m.memberLevel eq 1 }">
		<li><a href="/adminPage">마이페이지</a></li>
		<li><a href="/memberCtl?reqPage=1">회원관리</a></li>
		<li><a href="/rvList">예약확인 및 취소</a></li>
		<li><a href="/faqList?reqPage=1">문의답변</a></li>
		<li><a href="/adminChart">통계</a></li>
	</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test="${sessionScope.m.memberLevel eq 2 }">
			<li><a href="/myPage">마이페이지</a></li>
			<li><a href="/myInfo">내 정보관리</a></li>
			<li><a href="/myRv">예약내역</a></li>
		</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
<li></li>
<li></li>
<li></li>
<li></li>
</ul>
</div>
</div>
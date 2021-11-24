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
input[type=checkbox] {
	zoom: 1.5;
}
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/myheader.jsp"/>
	<div class="container" style="margin-top: 150px;">
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel ==1 }">
			<h2 class="text-fff">회원관리 페이지</h2>
			<hr class="text-fff">
			<div id="searchBox">
				<form class="d-flex" action="/searchMember" method="post">
        			<div class="form-group">
					<c:choose>
						<c:when test="not empty type">
						<select class="form-select" id="exampleSelect2" style="width: 200px;">
							<c:choose>
								<c:when test="${type eq 'id' }">
									<option value="id" selected>id</option>
        							<option value="phone">phone</option>
								</c:when>
								<c:otherwise>
									<option value="id">id</option>
        							<option value="phone" selected>phone</option>
								</c:otherwise>
							</c:choose>
						</select>
						</c:when>
						<c:otherwise>
						<select class="form-select" id="type" name="type" style="width: 200px;">
       		 				<option value="id">id</option>
        					<option value="phone">phone</option>
				      	</select>
						</c:otherwise>
					</c:choose>
   					</div>
        			<input class="form-control me-sm-2" type="text" placeholder="Search" name="keyword" value="${keyword }">
    	    		<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
		      	</form>
			</div>
			<br>
			<div style="margin: 5px;display: inline-block;">
				<button class="btn btn-danger chkDelBtn" style="margin-bottom: 10px;">선택회원삭제</button>
			</div>
			<table class="table-th table-hover table-sm" style="width:100%">
				<tr class="table-thema">
					<th><input type="checkbox" class="chk" onclick="allCheck();" id="allChk"></th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>지역</th>
					<th>관리</th>
				</tr>
				<c:forEach items="${list }" var="m" varStatus="i">
					<tr class="table-dark">
						<td><input type="checkbox" class="chk"></td>
						<%-- <td>${i.count }</td> --%>
						<td>${m.memberNo }</td>
						<td>${m.memberId }</td>
						<td>${m.memberName }</td>
						<td>${m.phone }</td>
						<td>${m.email }</td>
						<td>${m.addr }</td>
						<td>
							<button class="btn btn-outline-danger btn-sm delBtn">회원삭제</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<div id="pageNavi" style="float: none;margin: 0 auto;">${pageNavi }</div>
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script type="text/javascript">
		$(".delBtn").click(function() {
			var memberNo = $(this).parent().parent().children().eq(1).html();
			console.log(memberNo);
			if (confirm("삭제하시겠습니까?")) {
				location.href = "/adminDeleteOne?memberNo=" + memberNo;
			}
		});
		$(".chkDelBtn").click(function() {
			var inputs = $(".chk:checked");
			var num = new Array(); //회원번호 저장할 배열
			inputs.each(function(idx, item) {
				var memberNo = $(item).parent().next().html();
				num.push(memberNo);
			});
			console.log(num);
			if (confirm("삭제하시겠습니까?")) {
				location.href = "/adminChkDelete?num=" + num.join("/");
			}
		});
		function allCheck() {
			if ($("#allChk").is(":checked")) {
				$("input[type=checkbox]").prop("checked", true);
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
		}
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
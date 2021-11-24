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
	<div class="container"  style="padding-top: 15px;margin-top:90px;">
		<fieldset>
			<h3>공지사항</h3>
			<table class="table" id="noticeView" style="margin-top: 50px;">
				<tr class="table-thema">
					<th colspan="3" style="text-align: center; width : 90%"><h4>${n.noticeTitle }</h4></th>
					<td style="text-align: right; width : 10%">${n.noticeWriter }</td>
				</tr>
				<tr class="table-dark">
					<th colspan="4" style="text-align: left;">${n.noticeContent }</th>
				</tr>
				<tr class="table-dark">
					<th colspan="2">첨부파일</th>
					<th colspan="2">
						<c:if test="${not empty n.noticeFilename }">
							<img src="/img/file.png" style="color:white;">
							<a href="/fileDown?noticeNo=${n.noticeNo }">${n.noticeFilename }</a>
						</c:if>
					</th>
				</tr>
				<tr>
					<th colspan="4" style="text-align: center;">
						<button class="btn btn-info" onclick="list();">목록</button>
						<c:if test="${not empty sessionScope.m && sessionScope.m.memberId  eq n.noticeWriter}">
							<a href="javascript:void(0)" onclick="updateEvent(this,'${n.noticeNo }');" class="btn btn-dark">수정하기</a>
							<a href="javascript:void(0)" onclick="deleteEvent(this,'${n.noticeNo }');" class="btn btn-dark">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<%--댓글작성 --%>
		<c:if test="${not empty sessionScope.m }">
			<div class="inputCommentBox">
				<form action="/noticeInsertComment" method="post">
					<ul>
						<li style="width: 100%;">
							<input type="hidden" name="ncWriter" value="${sessionScope.m.memberId }">
							<input type="hidden" name="ncReg" value="${n.noticeNo }">
							<textarea name="ncContent" class="form-control" style="width: 100%;"></textarea>
						</li>
						<li>
							<button type="submit" class="btn btn-dark btn-lg">등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>
		<%--댓글 출력 --%>
		<div class="commentBox" style="color:#343a40; margin: 0 auto;">
			<c:forEach items="${list }" var="nc">
					<ul class="comments">
						<li>
							<p>${nc.ncWriter }</p>
							<p>${nc.ncDate }</p>
						</li>
						<li>
							<p>${nc.ncContentBr}</p>
							<textarea name="ncContent" class="form-control" style="display:none;">${nc.ncContent }</textarea>
							<p class="commentsBtn">
							<c:if test="${not empty sessionScope.m }">
								<c:if test="${sessionScope.m.memberId eq nc.ncWriter }">
									<a href="javascript:void(0)" onclick="modifyComment(this,'${nc.ncNo }','${n.noticeNo }');" style="color:#343a40;">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'${nc.ncNo }','${n.noticeNo }');" style="color:#343a40;">삭제</a>
								</c:if>
							</c:if>
							</p>					
						</li>
					</ul>		
			</c:forEach>			
		</div>
	<script>
		function modifyComment(obj,ncNo,noticeNo){
			$(obj).parent().prev().show();
			$(obj).parent().prev().prev().hide();
			$(obj).html('수정완료');
			$(obj).attr("onclick","modifyComplete(this,'"+ncNo+"','"+noticeNo+"');");
			$(obj).next().html('취소');
			$(obj).next().attr("onclick","modifyCancel(this,'"+ncNo+"','"+noticeNo+"');");
			$(obj).next().next().hide();
		}
		function modifyCancel(obj,ncNo,noticeNo){
			$(obj).parent().prev().hide();
			$(obj).parent().prev().prev().show();
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick","modifyComment(this,'"+ncNo+"','"+noticeNo+"');");
			$(obj).html("삭제");
			$(obj).attr("onclick","deleteComment(this,'"+ncNo+"','"+noticeNo+"');");
			$(obj).next().show();
		}
		function modifyComplete(obj,ncNo,noticeNo){
			var form = $("<form action='/noticeUpdateComment' method='post'></form>");
			form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));
			form.append($("<input type='text' name='noticeNo' value='"+noticeNo+"'>"));
			form.append($(obj).parent().prev());
			$("body").append(form);
			form.submit();
			
		}
		function deleteComment(obj,ncNo,noticeNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/noticeDeleteComment?ncNo="+ncNo+"&noticeNo="+noticeNo;
			}
		}
		function deleteEvent(obj,noticeNo){
			if(confirm("글을 삭제하시겠습니까?")){
				location.href="/noticeDelete?noticeNo="+noticeNo;
			}
		}
		function updateEvent(obj,noticeNo){
			if(confirm("글을 수정하시겠습니까?")){
				location.href="/noticeUpdateFrm?noticeNo="+noticeNo;
			}
		}
		function list(){
			location.href="/noticeList?reqPage=1";
		}
	</script>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	<script src="/js/main.js"></script>
</body>
</html>
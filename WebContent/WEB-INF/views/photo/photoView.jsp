<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>게시글 보기</title>
<style>
	.container>fieldset{
		color:#fff;
		margin-top: 70px;
	}
	#photoView th{
		width:10%;
	}
	#photoView td{
		text-align: left;
	}
	#photoView img{
		margin-bottom: 10px;
	}
	.inputCommentBox, .commentBox{
		background-color: #343a40;
		padding: 30px 30px 0px 30px;
	}
	.inputCommentBox ul{
		list-style-type: none;
		padding : 0;
		display: flex;
	}
	.inputCommentBox ul>li:first-child {
		width:90%;
		margin-right: 10px;
	}
	.inputCommentBox ul>li:last-child {
		width:10%;
		text-align: center;
		display: flex;	
		justify-content: center;
		align-items: center;
	}
	.comments{
		list-style-type: none;
		padding: 0px 0px 30px 0px;
		display: flex;
		background-color: #343a40;
		flex-flow:column;
		justify-content: space-between;	
	}
	.comments:first-child{
		padding: 30px;
	}
	.comments a{
		color: #d3d3d3;
	}
	.comments p{
		margin-bottom: 0;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<fieldset>
			<legend>포토갤러리</legend>
			<table class="table" id="photoView" style="width:100%;">
				<tr class="table-thema">
					<th>글제목</th>
					<th colspan="3" style="text-align: left;">${p.photoTitle }</th>
				</tr>
				<tr class="table-thema">
					<th>작성자</th>
					<td>${p.photoWriter }</td>
					<th>작성일</th>
					<td>${p.regDate }</td>
				</tr>
				<tr class="table-dark">
					<th>내용</th>
					<td colspan="3">
						<img src="/upload/photo/${p.photoFilepath }" width="80%"><br>
						${p.photoContentBr }
					</td>
				</tr>
				<tr class="table-dark">
					<th colspan="4">
						<button class="btn btn-primary" onclick="photoList();">글목록</button>
						<c:if test='${not empty sessionScope.m && sessionScope.m.memberId eq p.photoWriter }'>
		   					<a href="/photoUpdateFrm?photoNo=${p.photoNo}" class="btn btn-primary">수정하기</a>
		   					<a href="javascript:void(0)" class="btn btn-primary" onclick="deletePhoto(this,'${p.photoNo}');">삭제하기</a>
	   					</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		
		<!-- 댓글 쓰기 -->
		<c:if test="${not empty sessionScope.m }">
			<div class="inputCommentBox">
				<form action="/photoInsertComment" method="post">
					<fieldset>
						<legend>댓글쓰기</legend>
							<ul>
								<li>
									<input type="hidden" name="pcWriter" value="${sessionScope.m.memberId }">
									<input type="hidden" name="pcRef" value="${p.photoNo }">
									<textarea name="pcContent" class="form-control" style="height: 88px;"></textarea>								
								</li>
								<li>
									<button type="submit" class="btn btn-primary btn-lg btn-block">댓글 등록</button>										
								</li>
							</ul>
					</fieldset>
				</form>
			</div>
		</c:if>
		
		<!-- 댓글 출력 -->
		<div class="commentBox">
			<fieldset>
				<legend>댓글</legend>
				<c:forEach items="${list }" var="pc" varStatus="i">
					<ul class="comments" style="margin-bottom: 0;">
						<li>
							<p>${pc.pcContentBr }</p>
							<textarea name="pcContent" class="form-control" style="display: none;">${pc.pcContent }</textarea>
						</li>
						<li>
							<c:choose>
								<c:when test="${not empty sessionScope.m && sessionScope.m.memberId eq pc.pcWriter }">
									<p style="color: #d3d3d3; margin-bottom: 10px;">
										${pc.pcWriter } | ${pc.pcDate } |
										<a href="javascript:void(0)" onclick="modifyComment(this,'${pc.pcNo}','${p.photoNo}');">수정</a> |
										<a href="javascript:void(0)" onclick="deleteComment(this,'${pc.pcNo}','${p.photoNo}');">삭제</a>	
									</p>
								</c:when>
								<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1 }">
									<p style="color: #d3d3d3; margin-bottom: 10px;">
										${pc.pcWriter } | ${pc.pcDate } |
										<a href="javascript:void(0)" onclick="deleteComment(this,'${pc.pcNo}','${p.photoNo}');">삭제</a>	
									</p>
								</c:when>
								<c:otherwise>
									<p style="color: #d3d3d3; margin-bottom: 10px;">${pc.pcWriter } | ${pc.pcDate }</p>							
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</c:forEach>
			</fieldset>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		function photoList(){
			location.href="/photoList?reqPage=1";
		}
		
		function deletePhoto(obj,photoNo){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href="/photoDelete?photoNo="+photoNo;
			}
		}
		function modifyComment(obj,pcNo,photoNo){
			$(obj).parent().parent().prev().children().first().hide();
			$(obj).parent().parent().prev().children().last().show();
			$(obj).html("수정 완료");
			$(obj).attr("onclick","modifyComplete(this,'"+pcNo+"','"+photoNo+"');");
			$(obj).next().html("취소");
			$(obj).next().attr("onclick","modifyCancel(this,'"+pcNo+"','"+photoNo+"');");
			$(obj).next().next().hide();
		}
		function modifyCancel(obj,pcNo,photoNo){
			$(obj).parent().parent().prev().children().first().show();
			$(obj).parent().parent().prev().children().last().hide();
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick","modifyComment(this,'"+pcNo+"','"+photoNo+"');");
			$(obj).html("삭제");
			$(obj).attr("onclick","modifyComplete(this,'"+pcNo+"','"+photoNo+"');");
			$(obj).next().show();
		}
		function modifyComplete(obj,pcNo,photoNo){
			var form=$("<form action='/photoUpdateComment' method='post'></form>");
			form.append($("<input type='text' name='pcNo' value='"+pcNo+"'>"));
			form.append($("<input type='text' name='photoNo' value='"+photoNo+"'>"));
			form.append($(obj).parent().parent().prev().children().last());
			$("body").append(form);
			form.submit();
		}
		function deleteComment(obj,pcNo,photoNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/photoDeleteComment?pcNo="+pcNo+"&photoNo="+photoNo;
			}
		}
	</script>
</body>
</html>
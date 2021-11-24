<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<h3 style="margin-left:200px;">이벤트</h3><br>
			<table class="table" id="eventView" style="width:70%; margin:0 auto;">
				<tr class="table-thema">
					<th colspan="3" style="text-align: center; width : 90%"><h4>${e.eventTitle }</h4></th>
					<td style="text-align: right; width : 10%">${e.eventWriter }</td>
				</tr>
				<tr class="table-dark">
					<th colspan="4" style="text-align: center;">${e.eventContent }</th>
				</tr>
				<tr>
					<th colspan="4" style="text-align: center;">
						<button class="btn btn-info" onclick="list();">목록</button>
						<c:if test="${not empty sessionScope.m && sessionScope.m.memberId  eq e.eventWriter}">
								<a href="javascript:void(0)" onclick="updateEvent(this,'${e.eventNo }');" class="btn btn-dark">수정하기</a>
								<a href="javascript:void(0)" onclick="deleteEvent(this,'${e.eventNo }');" class="btn btn-dark">삭제하기</a>
							</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<%--댓글작성 --%>
		<c:if test="${not empty sessionScope.m }">
			<div class="inputCommentBox" style="width:70%; margin:0 auto;" >
				<form action="/eventInsertComment" method="post">
					<ul>
						<li style="width: 100%;">
							<input type="hidden" name="ecWriter" value="${sessionScope.m.memberId }">
							<input type="hidden" name="ecReg" value="${e.eventNo }">
							<textarea name="ecContent" class="form-control" style="width: 100%;"></textarea>
						</li>
						<li>
							<button type="submit" class="btn btn-dark btn-lg">등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>
		<%--댓글 출력 --%>
		<div class="commentBox" style="color:#343a40; margin: 0 auto; width:70%;" >
			<c:forEach items="${list }" var="ec">
					<ul class="comments">
						<li>
							<p>${ec.ecWriter }</p>
							<p>${ec.ecDate }</p>
						</li>
						<li>
							<p>${ec.ecContentBr}</p>
							<textarea name="ecContent" class="form-control" style="display:none;">${ec.ecContent }</textarea>
							<p class="commentsBtn">
							<c:if test="${not empty sessionScope.m }">
								<c:if test="${sessionScope.m.memberId eq ec.ecWriter }">
									<a href="javascript:void(0)" onclick="modifyComment(this,'${ec.ecNo }','${e.eventNo }');" style="color:#fff;">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'${ec.ecNo }','${e.eventNo }');" style="color:#fff;margin-right:10px;">삭제</a>
								</c:if>
							</c:if>
							</p>					
						</li>
					</ul>		
			</c:forEach>			
		</div>
	<script>
		function modifyComment(obj,ecNo,eventNo){
			$(obj).parent().prev().show();
			$(obj).parent().prev().prev().hide();
			$(obj).html('수정완료');
			$(obj).attr("onclick","modifyComplete(this,'"+ecNo+"','"+eventNo+"');");
			$(obj).next().html('취소');
			$(obj).next().attr("onclick","modifyCancel(this,'"+ecNo+"','"+eventNo+"');");
			$(obj).next().next().hide();
		}
		function modifyCancel(obj,ecNo,eventNo){
			$(obj).parent().prev().hide();
			$(obj).parent().prev().prev().show();
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick","modifyComment(this,'"+ecNo+"','"+eventNo+"');");
			$(obj).html("삭제");
			$(obj).attr("onclick","deleteComment(this,'"+ecNo+"','"+eventNo+"');");
			$(obj).next().show();
		}
		function modifyComplete(obj,ecNo,eventNo){
			var form = $("<form action='/eventUpdateComment' method='post'></form>");
			form.append($("<input type='text' name='ecNo' value='"+ecNo+"'>"));
			form.append($("<input type='text' name='eventNo' value='"+eventNo+"'>"));
			form.append($(obj).parent().prev());
			$("body").append(form);
			form.submit();
			
		}
		function deleteComment(obj,ecNo,eventNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/eventDeleteComment?ecNo="+ecNo+"&eventNo="+eventNo;
			}
		}
		function deleteEvent(obj,eventNo){
			if(confirm("글을 삭제하시겠습니까?")){
				location.href="/eventDelete?eventNo="+eventNo;
			}
		}
		function updateEvent(obj,eventNo){
			if(confirm("글을 수정하시겠습니까?")){
				location.href="/eventUpdateFrm?eventNo="+eventNo;
			}
		}
		function list(){
			location.href="/eventList";
		}
	</script>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	<script src="/js/main.js"></script>
</body>
</html>
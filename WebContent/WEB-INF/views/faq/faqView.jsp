<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/fontawesome/css/all.css">
<script type="text/javascript" src="/fontawesome/js/all.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<style>
.table-title {
	border-right: 1px soild #ededed;
	color: white;
}

.inputCommentBox ul {
	list-style-type: none;
	padding: 0;
	display: flex;
}

.inputCommentBox textarea[name=commentContent] {
	height: 100%;
	resize: none;
}

.inputCommentBox ul>li:first-child, .inputCommentBox ul>li:last-child {
	width: 15%;
	text-align: center;
	display: flex;
	justify-content: center;
	align-items: center;
}

.inputCommentBox ul>li:nth-child(2) {
	width: 70%;
}

.comments, .recomments {
	list-style-type: none;
	padding: 0;
	display: flex;
}

.comments>li>p, .recomments>li>p {
	margin: 0;
}

.comments>li:first-child{
   display: flex;
   flex-flow:column;
   justify-content: flex-start;
   align-items: center;
   width:15%;
   padding : 5px 0px 5px 0px;
   background-color: #070c11;
}

.comments>li:last-child{
   padding : 5px 0px 5px 10px;
   width : 85%;
   display:flex;
   flex-flow:column;
   justify-content: space-between;   
   
}

.commentsBtn {
	text-align: right;
}

textarea.form-control {
	margin-top: 8px;
	height: 80%;
	resize: none;
}
.th-title{
	width: 100px;
}
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container"
		style="margin-top:80px ;padding-top: 15px;">
		<fieldset>
			<legend><h2>Q&A게시판</h2></legend>
			<hr>
			<table class="table" id=faqView style="width: 100%">
				<tr class="table-active">
					<th class="table-title th-title" style="background-color: #070C11;">제목</th>
					<th class="table-title" colspan="3" style="background-color: #070C11;">${f.faqTitle }</th>
				</tr>
				<tr class="table-active">
					<th class="table-title th-title" style="background-color: #070C11;">작성자</th>
					<th class="table-title" style="background-color: #070C11;">${f.faqWriter }</th>
					<th class="table-title" style="background-color: #070C11;">작성일</th>
					<th class="table-title" style="background-color: #070C11;">${f.regDate }</th>
				</tr>
				<tr class="table-active">
					<th class="table-title th-title" style="background-color: #343A40;">내용</th>
					<th class="table-title" colspan="3" style="text-align: left; background-color: #343A40;">${f.faqContent }</th>
				</tr>
				<tr class="table-active">
					<th colspan="4" style="text-align: center;">
						<a href="/faqList?reqPage=1" class="btn btn-info">Q&A 돌아가기</a>
						<c:if
							test="${not empty sessionScope.m && sessionScope.m.memberId eq f.faqWriter}">
							<a href="/faqUpdateFrm?faqNo=${f.faqNo }" class="btn btn-info">수정하기</a>
							<a href="/faqDelete?faqNo=${f.faqNo }" class="btn btn-info">삭제하기</a>
						</c:if>
						<c:if test="${sessionScope.m.memberLevel==1 && sessionScope.m.memberId != f.faqWriter}">
							<a href="/faqDelete?faqNo=${f.faqNo }" class="btn btn-info">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<c:if test="${(not empty sessionScope.m && (sessionScope.m.memberId eq f.faqWriter)) ||(not empty sessionScope.m && (sessionScope.m.memberLevel==1))}">
			<div class="inputCommentBox">
				<form action="/insertCommentFaq" method="post">
					<ul>
						<li>
						</li>
						<li>
							<input type="hidden" name="fcWriter" value="${sessionScope.m.memberId }"> 
							<input type="hidden" name=faqRef value="${f.faqNo }"> 
									<c:choose>
								<c:when test="${sessionScope.m.memberLevel==1 }">
									<input type="hidden" name = fcLevel value="1">
								</c:when>
								<c:when test="${sessionScope.m.memberLevel==2 }">
									<input type="hidden" name = fcLevel value="2">
								</c:when>
						</c:choose>
							<textarea name="fcContent" class="form-control"></textarea>						
						</li>	 
						<li>
							<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>
		<div class="commentBox">
			<c:forEach items="${list }" var="fc" varStatus="i">
				<ul class="comments">
					<c:choose>
						<c:when test="${fc.fcLevel==1}">
							<li>
								<p>${fc.fcWriter }</p>
								<p>${fc.fcDate }</p>
								</li>
						</c:when>
						<c:when test="${fc.fcLevel==2}">
							<li>
								<p>${fc.fcWriter }</p>
								<p>${fc.fcDate }</p></li>
						</c:when>
					</c:choose>
					<li style="background-color: #343A40;">
						<p>${fc.fcContentBr }</p>
						<textarea name="fcContent" class="form-control" style="display: none;">${fc.fcContent }</textarea>
						<p class="commentsBtn">
						<c:if test="${sessionScope.m.memberId eq fc.fcWriter}">
							<a href="javascript:void(0)" onclick="modifyComment(this,'${fc.faqCoNo}','${f.faqNo }')" style="color: white;">수정</a>
							<a href="javascript:void(0)" onclick="deleteComment(this,'${fc.faqCoNo}','${f.faqNo }')" style="color: white;">삭제</a>
						</c:if>
						<c:if test="${sessionScope.m.memberLevel==1 && sessionScope.m.memberId != fc.fcWriter}">
						<a href="javascript:void(0)" onclick="deleteComment(this,'${fc.faqCoNo}','${f.faqNo }')" style="color: white;">삭제</a>
						</c:if>
						</p>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
	<script>
	function modifyComment(obj,faqCoNo,faqNo) {
		//textarea 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		//수정>> 수정완료
		$(obj).html('수정완료');
		//수정완료 이후 함수속성 변경
		$(obj).attr("onclick","modifyComplete(this,'"+faqCoNo+"','"+faqNo+"');");
		//삭제 >> 취소
		$(obj).next().html('취소');
		//취소속성 함수로 변환
		$(obj).next().attr("onclick","modifyCancel(this,'"+faqCoNo+"','"+faqNo+"');");
		//답글달기 버튼 숨기기
		$(obj).next().next().hide();
	}
	//취소버튼(위와 반대)
	function modifyCancel(obj,faqCoNo,faqNo) {
		//textarea 숨김
		$(obj).parent().prev().hide();
		//기존본문내용을 화면에 다시표현
		$(obj).parent().prev().prev().show();
		//수정완료 >> 수정
		$(obj).prev().html("수정");
		//수정완료의 속성 변경
		$(obj).prev().attr("onclick","modifyComment(this,'"+faqCoNo+"','"+faqNo+"')");
		//취소>> 삭제
		$(obj).html('삭제');
		//취소에서 삭제로 속성변경
		$(obj).attr("onclick","deleteComment(this,'"+faqCoNo+"','"+faqNo+"')");
		//답글달기버튼 화면에 표현
		$(obj).next().show();
		
	}
	function modifyComplete(obj,faqCoNo,faqNo) {
		//새로운 form태그를 생성
		var form=$("<form action='/updateCommentFaq' method='post'></form>");
		//form안에 수정댓글번호 설정
		form.append($("<input type='text' name='faqCoNo' value='"+faqCoNo+"'>"));
		//form안에 공지사항번호 설정
		form.append($("<input type='text' name='faqNo' value='"+faqNo+"'>"));
		//수정한 댓글 내용을 설정(수정완료버튼 부모요소 전)
		form.append($(obj).parent().prev());
		//전송할 form 태그를 현재 페이지에 추가
		$("body").append(form);
		//form태그 전송
		form.submit();
	}
	function deleteComment(obj,faqCoNo,faqNo) {
		if(confirm("댓글을 삭제하시겠습니까?")){
			//댓글 고유식별번호 ncNo for 삭제 쿼리를 위해/삭제이후 상세보기 페이지로 다시 들어오기 위해서 noticeNo
			location.href="/deleteCommentFc?faqCoNo="+faqCoNo+ "&faqNo="+faqNo;
		}
	}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
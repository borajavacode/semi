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
<style>
.table-title {
	border-right: 1px soild #ededed;
	color:  white;

}
.table>tr{
	background-color: #343A40;
}
.inputCommentBox ul{
	list-style-type: none;
	padding : 0;
	display: flex;
}
.inputCommentBox textarea[name=commentContent]{
	height:100%;
	resize: none;
}
.inputCommentBox ul>li:first-child,.inputCommentBox ul>li:last-child {
	width:15%;
	text-align: center;
	display: flex;	
	justify-content: center;
	align-items: center;
}
.comments,.recomments{
	list-style-type: none;
	padding : 0;
	display: flex;
	flex-wrap: wrap;  
}
.comments>li>p,.recomments>li>p{
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
.commentsBtn{	
	text-align: right;	
}
textarea.form-control{
	margin-top: 8px;
	height:80%;
	resize:none;
}
.th-title{
	width: 100px;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container" style="padding-top: 15px;margin-top:80px;">
		<c:choose>
			<c:when test="${b.boardLevel == 1 }">
				<fieldset>
					<legend><h2>자유게시판-${b.boardTitle }</h2></legend>
					<hr>
					<table class="table" id="boardView" style="width: 100% ">
						<tr class="table-dark" >
							<th class="table-title th-title" style="background-color: #070C11;">제목</th>
							<th colspan="3" class="table-title" style="background-color: #070C11;">${b.boardTitle }</th>
						</tr>
						<tr class="table-dark">
							<th class="table-title th-title" style="background-color: #070C11;">작성자</th>
							<th class="table-title" style="background-color: #070C11;">${b.boardWriter }</th>
							<th class="table-title" style="background-color: #070C11;">작성일</th>
							<th class="table-title " style="background-color: #070C11;">${b.regDate }</th>
						</tr>
						<tr class="table-dark">
							<th class="table-title th-title" style="background-color: #070C11;">첨부파일</th>
							<th class="table-title" style="background-color: #070C11;"><c:if test="${not empty b.boardFilename }">
									<img src="/img/file.png" width="16px">
									<a href="/fileDown?boardNo=${b.boardNo}" style="color: white;">${b.boardFilename}</a>
								</c:if></th>
							<th class="table-title" style="background-color: #070C11;">조회수</th>
							<th class="table-title" style="background-color: #070C11;">${b.readCount }</th>
						</tr>
						<tr class="table-active">
							<th class="table-title th-title" style="background-color: #343A40;">내용</th>
							<th class="table-title" colspan="3" style="text-align: left; background-color: #343A40;" >${b.boardContent }
							</th>
						</tr>
						<tr class="table-active">
							<th colspan="4" style="text-align: center;">
								<a href="/boardList?reqPage=1&listFind=2"
										class="btn btn-info">자유게시판 목록</a>
								<c:if
									test="${not empty sessionScope.m && sessionScope.m.memberId eq b.boardWriter  }">
									<a href="/boardUpdateFrm?boardNo=${b.boardNo }"
										class="btn btn-info">수정하기</a>
									<a href="/boardDelete?boardNo=${b.boardNo }"
										class="btn btn-info">삭제하기</a>
								</c:if>
								<c:if test="${sessionScope.m.memberLevel == 1 && sessionScope.m.memberId != b.boardWriter}">
									<a href="/boardDelete?boardNo=${b.boardNo }"
										class="btn btn-info">삭제하기</a>
								</c:if>
							</th>
						</tr>
					</table>
				</fieldset>
			</c:when>
			<c:when test="${b.boardLevel == 2 }">
				<fieldset>
					<legend>동료모집</legend>
					<hr>
					<table class="table" id="boardView" style="width: 100%">
						<tr class="table-active">
							<th class="table-title th-title" style="background-color: #070C11;">제목</th>
							<th colspan="3" class="table-title" style="background-color: #070C11;">${b.boardTitle }</th>
						</tr>
						<tr class="table-dark">
							<th class="table-title th-title" style="background-color: #070C11;">작성자</th>
							<th class="table-title" style="background-color: #070C11;">${b.boardWriter }</th>
							<th class="table-title" style="background-color: #070C11;">작성일</th>
							<th class="table-title" style="background-color: #070C11;">${b.regDate }</th>
						</tr>
						<tr class="table-dark">
							<th class="table-title th-title" style="background-color: #070C11;">모집일</th>
							<th class="table-title" style="background-color: #070C11;">${b.deadLine }</th>
							<th class="table-title" style="background-color: #070C11;">조회수</th>
							<th class="table-title" style="background-color: #070C11;">${b.readCount }</th>
						</tr>
						<tr class="table-active">
							<th class="table-title th-title" style="background-color: #343A40;">내용</th>
							<th colspan="3" style="text-align: left; background-color: #343A40" class="table-title">${b.boardContent }
							</th>
						</tr>
						<tr class="table-active">
							<th colspan="4" style="text-align: center;" class="table-title">
								<a href="/boardList?reqPage=1&listFind=3"
										class="btn btn-info">동료모집 목록</a>
								<c:if
									test="${not empty sessionScope.m && sessionScope.m.memberId eq b.boardWriter  }">
									<a href="/boardUpdateFrm?boardNo=${b.boardNo }"
										class="btn btn-info">수정하기</a>
									<a href="/boardDelete?boardNo=${b.boardNo }"
										class="btn btn-info">삭제하기</a>
								</c:if>
								<c:if test="${sessionScope.m.memberLevel == 1 && sessionScope.m.memberId != b.boardWriter}">
									<a href="/boardDelete?boardNo=${b.boardNo }"
										class="btn btn-info">삭제하기</a>
								</c:if>
							</th>
						</tr>
					</table>
					</fieldset>
			</c:when>
		</c:choose>
				<c:if test="${not empty sessionScope.m }">
					<div class="inputCommentBox">
					<form action="/insertCommentBd" method="post">
						<ul>
							<li style="width: 100%;">
							<input type="hidden" name="bdWriter" value="${sessionScope.m.memberId }">
							<input type="hidden" name="boardRef" value="${b.boardNo }">
							<textarea name="bdContent" class="form-control" style="width: 100%;"></textarea>							
							</li>
							<li>
								<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
							</li>
						</ul>
					</form>
					</div>
				</c:if>
				<div class="commentBox">
					<c:forEach items="${list }" var="bd" varStatus="i">
						<ul class="comments" style="background-color: #343A40;">
							<li>
								<p>${bd.bdWriter }</p>
								<p>${bd.bdDate }</p>
							</li>
							<li>
								<p>${bd.bdContentBr }</p>
								<textarea name="bdContent" class="form-control" style="display: none;">${bd.bdContent }</textarea>
								<p class="commentsBtn">
								<c:if test="${sessionScope.m.memberId eq bd.bdWriter}">
									<a href="javascript:void(0)" onclick="modifyComment(this,'${bd.bdNo }','${b.boardNo }')" style="color: white;">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'${bd.bdNo }','${b.boardNo }')" style="color: white;margin-right:10px;">삭제</a>
								</c:if>
								<c:if test="${sessionScope.m.memberLevel == 1 && sessionScope.m.memberId != bd.bdWriter}">
									<a href="javascript:void(0)" onclick="deleteComment(this,'${bd.bdNo }','${b.boardNo }')" style="color: white;margin-right:10px;">삭제</a>
								</c:if>
								</p>
							</li>
						</ul>
					</c:forEach>
				</div>
	</div>
	<script>
	function modifyComment(obj,bdNo,boardNo) {
		//textarea 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		//수정>> 수정완료
		$(obj).html('수정완료');
		//수정완료 이후 함수속성 변경
		$(obj).attr("onclick","modifyComplete(this,'"+bdNo+"','"+boardNo+"');");
		//삭제 >> 취소
		$(obj).next().html('취소');
		//취소속성 함수로 변환
		$(obj).next().attr("onclick","modifyCancel(this,'"+bdNo+"','"+boardNo+"');");
		//답글달기 버튼 숨기기
		$(obj).next().next().hide();
	}
	//취소버튼(위와 반대)
	function modifyCancel(obj,bdNo,boardNo) {
		//textarea 숨김
		$(obj).parent().prev().hide();
		//기존본문내용을 화면에 다시표현
		$(obj).parent().prev().prev().show();
		//수정완료 >> 수정
		$(obj).prev().html("수정");
		//수정완료의 속성 변경
		$(obj).prev().attr("onclick","modifyComment(this,'"+bdNo+"','"+boardNo+"')");
		//취소>> 삭제
		$(obj).html('삭제');
		//취소에서 삭제로 속성변경
		$(obj).attr("onclick","deleteComment(this,'"+bdNo+"','"+boardNo+"')");
		//답글달기버튼 화면에 표현
		$(obj).next().show();
		
	}
	function modifyComplete(obj,bdNo,boardNo) {
		//새로운 form태그를 생성
		var form=$("<form action='/updateCommentBd' method='post'></form>");
		//form안에 수정댓글번호 설정
		form.append($("<input type='text' name='bdNo' value='"+bdNo+"'>"));
		//form안에 공지사항번호 설정
		form.append($("<input type='text' name='boardNo' value='"+boardNo+"'>"));
		//수정한 댓글 내용을 설정(수정완료버튼 부모요소 전)
		form.append($(obj).parent().prev());
		//전송할 form 태그를 현재 페이지에 추가
		$("body").append(form);
		//form태그 전송
		form.submit();
	}
	function deleteComment(obj,bdNo,boardNo) {
		if(confirm("댓글을 삭제하시겠습니까?")){
			//댓글 고유식별번호 ncNo for 삭제 쿼리를 위해/삭제이후 상세보기 페이지로 다시 들어오기 위해서 noticeNo
			location.href="/deleteCommentBd?bdNo="+bdNo+ "&boardNo="+boardNo;
		}
	}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
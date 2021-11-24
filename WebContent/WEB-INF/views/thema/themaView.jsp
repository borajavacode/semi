<%@page import="thema.vo.Thema"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%
    	Thema t = (Thema)request.getAttribute("t");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${t.themaName }</title>
    <link href="css/ThemaDefault.css" rel="stylesheet">
    <link href="css/ThemaView.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!--상단 이미지/제목-->
    <div class="imgBox">
        <img src="/img/kh escape.png">
        <h1>Thema</h1>
    </div>
    <div class="container center">
        <div class="title">
            <img src="/img/thema/${t.filepath }"><br><br>
            <h1>${t.themaName }</h1><br><br>
            <h4>난이도 : </h4>
            <%for(int i=0; i<t.getDifficult(); i++){ %>
            	<img src="/img/key.png">
            <%} %>
        </div>
        <div class="location">
            <h4>위치 : ${t.themaAddr }</h4>
        </div>
        <div class="info">
           <p>${t.themaContentBr }</p>
        </div>
        <div class="btnBox">
            <a href="/reservation?tm=${t.themaName }&pt=${t.themaAddr}" class="btn btn-info">예약하기</a>
            <br>
            <c:if test="${sessionScope.m!=null && sessionScope.m.memberLevel == 1 }">
            <a href="/updateThemaFrm?themaNo=${t.themaNo }" class="btn btn-outline-danger">수정하기</a>
            <a href="javascript:void(0)" onclick="deleteThema('${t.themaNo}')" class="btn btn-outline-danger">삭제하기</a>
            </c:if>
        </div>
        <!-- 댓글 입력 -->
        <c:if test="${sessionScope.m != null }">
        	<div class="inputCommentBox">
				<form action="/insertCommentTh" method="post">
					<input type="hidden" name="tcWriter" value="${sessionScope.m.memberId }">
					<input type="hidden" name="themaRef" value="${t.themaNo }">
					<textarea name="tcContent" class="form-control" style="width: 90%;resize: none;"></textarea>
					<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
				</form>
			</div>
        </c:if>
		<!-- 댓글 출력 -->
		<div class="commentBox">
			<c:forEach items="${list }" var="tc" varStatus="i">
				<ul class="comments">
					<li>
						<p>${tc.tcWriter }</p>
						<p>${tc.tcDate }</p>
					</li>
					<li>
							<p>${tc.tcContentBr }</p>
							<textarea name="tcContent" class="form-control" style="display: none;">${tc.tcContent }</textarea>
							<p class="commentsBtn">
								<c:if test="${sessionScope.m != null }">
									<c:choose>
										<c:when test="${sessionScope.m.memberId == tc.tcWriter}">
											<a href="javascript:void(0)" onclick="modifyComment(this,'${tc.tcNo }','${t.themaNo }');">수정</a>
											<a href="javascript:void(0)" onclick="deleteComment(this,'${tc.tcNo }','${t.themaNo }');">삭제</a>										
										</c:when>
										<c:when test="${sessionScope.m.memberLevel == 1 }">
											<a href="javascript:void(0)" onclick="deleteComment(this,'${tc.tcNo }','${t.themaNo }');">삭제</a>
										</c:when>
									</c:choose>
								</c:if>
							</p>
					</li>
				</ul>
			</c:forEach>
		</div>
    </div>
    <script>
    function deleteThema(themaNo) {
    	if(confirm("테마를 삭제하시겠습니까?")){
			location.href="/deleteThema?themaNo="+themaNo;
		}
	}
    
    function modifyComment(obj,tcNo,themaNo) {
		//textarea를 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		//수정 -> 수정완료
		$(obj).html('수정완료');
		$(obj).attr("onclick", "modifyComplete(this, '"+tcNo+"', '"+themaNo+"');");
		//삭제 -> 취소
		$(obj).next().html('취소');
		$(obj).next().attr("onclick", "modifyCancel(this, '"+tcNo+"', '"+themaNo+"');");
		//답글달기 버튼 숨기기
		$(obj).next().next().hide();
	}
	function modifyCancel(obj,tcNo,themaNo) {
		//textarea 숨김
		$(obj).parent().prev().hide();
		//기존 본문내용을 화면에 다시 표현
		$(obj).parent().prev().prev().show();
		//수정완료 -> 수정
		$(obj).prev().html('수정');
		$(obj).prev().attr("onclick", "modifyComment(this, '"+tcNo+"', '"+themaNo+"');");
		//취소 -> 삭제
		$(obj).html('삭제');
		$(obj).attr("onclick", "deleteCommentTh(this, '"+tcNo+"', '"+themaNo+"');");
		//답글달기 버튼 보이기
		$(obj).next().show();
	}
	function modifyComplete(obj,tcNo,themaNo){
		var form = $("<form action='/updateCommentTh' method='post'></form>");
		//form안에 수정댓글 번호 설정
		form.append($("<input type='text' name='tcNo' value='"+tcNo+"'>"));
		//form에 공지사항 번호 설정
		form.append($("<input type='text' name='themaNo' value='"+themaNo+"'>"));
		//수정한 댓글 내용을 설정
		form.append($(obj).parent().prev());
		//전송할 form태그를 현재 페이지에 추가
		$("body").append(form);
		//form태그 전송
		form.submit();
		
	}
	function deleteComment(obj,tcNo,themaNo){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/deleteCommentTh?tcNo="+tcNo+"&themaNo="+themaNo;
		}
	}
    </script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="/js/main.js"></script>
</body>
</html>
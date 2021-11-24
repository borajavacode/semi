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
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<!-- noev css -->
	<link rel="stylesheet" href="/css/noev.css">
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container" style="padding-top: 15px;margin-top:90px;">
		<form action="/noticeUpdate" method="post" enctype="multipart/form-data">
			<input type="hidden" name="noticeNo" value="${n.noticeNo }">
			<fieldset>
				<legend>공지사항 수정</legend>
				<table class="table" style="width:100%;">
					<tr class="table-thema">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="noticeTitle" class="form-control" value="${n.noticeTitle }">
						</td>
					</tr>
					<tr class="table-thema">
						<th>작성자</th>
						<td>
							${n.noticeWriter }
						</td>
						<th>첨부파일</th>
						<td style="text-align:left;">
							<input type="hidden" name="status" value="1">
							<c:choose>
								<c:when test="${not empty n.noticeFilename }">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile">${n.noticeFilename }</span>
								<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">
								삭제
								</button>
								<input type="file" name="upfile" style="display:none;">
								<input type="hidden" name="oldFilename" value="${n.noticeFilename }">
								<input type="hidden" name="oldFilepath" value="${n.noticeFilepath }">
								</c:when>
								<c:otherwise>
								<input type="file" name="upfile">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="table-dark" >
						<th>내용</th>
						<td colspan="3" style="text-align: left;">
							<textarea id="noticeContent" name="noticeContent" class="form-control">${n.noticeContent }</textarea>
						</td>
					</tr>
					<tr>
						<th colspan="4">
							<button type="submit" class="btn btn-dark btn-block">공지사항수정</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<script>
		$("#delBtn").click(function() {
			$(".delFile").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		});
	</script>
	<script>
	$(function() {
		$("#noticeContent").summernote({
			height : 400,
			minHeight : null,
			maxHeight : null,
			lang : "ko-KR",
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					for (var i = 0; i < files.length; i++) {
						uploadImage(files[i], this);
					}
				}
			}
		});
	});
	function uploadImage(file,editor){
		data = new FormData();
		data.append("file",file);
		$.ajax({
			url : "/uploadImage",
			type : "post",
			data : data,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			success : function(data){
				$(editor).summernote("insertImage",data);
			}
		});
	}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
	<script src="/js/main.js"></script>
</body>
</html>
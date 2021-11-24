<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<!-- noev css -->
	<link rel="stylesheet" href="/css/noev.css">
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container"  style="padding-top: 15px;margin-top:90px;">
		<form action="/noticeWrite" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>공지사항 작성</legend>
				<table class="table" style="width: 100%;">
					<tr class="table-thema">
						<th>제목</th>
						<td colspan="3"><input type="text" name="noticeTitle"
							class="form-control"></td>
					</tr>
					<tr class="table-thema">
						<th>작성자</th>
						<td>${sessionScope.m.memberId } <input type="hidden"
							name="noticeWriter" value="${sessionScope.m.memberId }">
						</td>
						<th>첨부파일</th>
						<td style="text-align: left;"><input type="file" name="upfile"></td>
					</tr>
					<tr class="table-dark">
						<th>내용</th>
						<td colspan="3" style="text-align: left;">
							<textarea id="noticeContent" name="noticeContent" class="form-control"></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">공지사항등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
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
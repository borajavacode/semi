<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.th-title{
	width: 100px;
}
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container" style="background-color: #353535; padding-top: 15px; margin-top: 80px;">
		<form action="/boardUpdate" method="post"
			enctype="multipart/form-data">
			<c:choose>
				<c:when test="${b.boardLevel == 1}">
					<fieldset>
						<legend>자유게시판 수정</legend>
						<table class="table" style="width: 100%">
							<tr class="table-dark">
								<th class ="th-title">제목</th>
								<td colspan="3"><input type="text" name="boardTitle"
									class="form-control" value="${b.boardTitle}"></td>
							</tr>
							<tr class="table-dark">
								<th class ="th-title">작성자</th>
								<td>${b.boardWriter }</td>
								<th>첨부파일</th>
								<td style="text-align: left;"><input type="hidden"
									name="status" value="1"> <c:choose>
										<c:when test="${not empty b.boardFilename}">
											<img src="/img/file.png" width="16px" class="delFile">
											<span class="delFile">${b.boardFilename }</span>
											<button type="button" id="delBtn"
												class="btn btn-primary btn-sm delFile">삭제</button>
											<input type="file" name="upfile" style="display: none;">
											<input type="hidden" name="oldFilename"
												value="${b.boardFilename }">
											<input type="hidden" name="oldFilepath"
												value="${b.boardFilepath }">
										</c:when>
										<c:otherwise>
											<input type="file" name="upfile">
										</c:otherwise>
									</c:choose></td>
							</tr>
							<tr class="table-dark">
								<th class ="th-title">내용</th>
								<td colspan="3" style="text-align: left;"><textarea
										id="boardContent2" name="boardContent" class="form-control">${b.boardContent }</textarea>
								</td>
							</tr>
							<tr class="table-active">
								<th colspan="4">
									<button type="submit" class="btn btn-danger btn-block" style="width: 80%; background-color: #353535">자유게시판수정</button>
								</th>
							</tr>
						</table>
					</fieldset>
					<input type="hidden" name="boardNo" value="${b.boardNo }">
				</c:when>
				<c:when test="${b.boardLevel == 2 }">
					<fieldset>
						<legend>동료모집 수정</legend>
						<table class="table" style="width: 100%">
							<tr class="table-dark">
								<th class ="th-title">제목</th>
								<td colspan="3"><input type="text" name="boardTitle"
									class="form-control" value="${b.boardTitle}"></td>
							</tr>
							<tr class="table-dark">
								<th class ="th-title">작성자</th>
								<td>${b.boardWriter }</td>
								<th>모집일</th>
								<td>※수정시 날짜 다시 설정해주세요!! <input type="date" id="datepick" name="deadLine"
									required="required" >
									<input type="hidden"
									name="status" value="1">
									</td>
							</tr>
							<tr class="table-dark">
								<th class ="th-title">내용</th>
								<td colspan="3" style="text-align: left;"><textarea
										id="boardContent" name="boardContent" class="form-control">${b.boardContent }</textarea>
								</td>
							</tr>
							<tr class="table-dark">
								<th colspan="4">
									<button type="submit" class="btn btn-danger btn-block" id="confirm" style="width: 80%; background-color: #111111">동료모집 수정</button>
								</th>
							</tr>
						</table>
					</fieldset>
					<input type="hidden" name="boardNo" value="${b.boardNo }">
				</c:when>
			</c:choose>
		</form>
	</div>
	<script>
		$("#confirm").click(function () {
			
		});
		$("#delBtn").click(function() {
			$(".delFile").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		});
		$(function() {
			$("#boardContent").summernote({
				height : 400,
				lang : "ko-KR",
				callbacks : {
					onImageUpload : function(files) {
						for (var i = 0; i < files.length; i++) {
							boardUploadImage(files[i], this);
						}
					}
				}
			});
		});
		$(function() {
			$("#boardContent2").summernote({
				height : 400,
				lang : "ko-KR",
				callbacks : {
					onImageUpload : function(files) {
						for (var i = 0; i < files.length; i++) {
							boardUploadImage(files[i], this);
						}
					}
				}
			});
		});
		function boardUploadImage(file, editor) {
			var form = new FormData();
			form.append("file", file);
			$.ajax({
				url : "/boardUploadImage",
				type : "post",
				data : form,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				success : function(data) {
					$(editor).summernote("insertImage", data);
				}
			});
		}
		//최소날짜 정하기
		var dateElement = document.getElementById('datepick');
		var date = new Date();
		dateElement.value = formatDate(date);
		dateElement.setAttribute("min", dateElement.value);
		//오늘날짜+1
		function formatDate(date) {
			var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
					+ (d.getDate() + 1), year = d.getFullYear();
			if (month.length < 2)
				month = '0' + month;
			if (day.length < 2)
				day = '0' + day;
			return [ year, month, day ].join('-');
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
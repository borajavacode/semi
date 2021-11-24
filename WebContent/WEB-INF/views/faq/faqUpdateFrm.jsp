<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	color: #111111;
}

#searchBox {
	display: inline-block;
	width: 50%;
	margin: 0;
}

select[name=type] {
	display: inline-block;
	width: 30%
}

input[name=keyword] {
	display: inline-block;
	width: 50%
}

.sub-banner {
	widows: 100%
}

.sub-title {
	background-size: cover;
	text-align: center;
}

.tab_title li {
	list-style: none;
	float: left;
	width: 120px;
	padding: 10px 15px;
	text-align: center;
	border: 1px solid #ededed;
}

.tab_content {
	clear: both;
}
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
	<div class="container"
		style="background-color: #353535; padding-top: 15px; margin-top: 80px;">
		<form action="/faqUpdate" method="post">
			<fieldset>
				<legend>질문하기 수정</legend>
				<table class="table" style="width: 100%;">
					<tr class="table-dark">
						<th class ="th-title">제목</th>
						<td colspan="3"><input type="text" name="faqTitle"
							class="form-control" value="${f.faqTitle }"></td>
					</tr>
					<tr class="table-dark">
						<th class ="th-title">작성자</th>
						<td>${sessionScope.m.memberId }<input type="hidden"
							name="faqWriter" value="${sessionScope.m.memberId }"></td>
						<th>공개여부</th>
						<td><c:choose>
								<c:when test="${f.faqStatus == 1 }">
					공개 <input type="radio" id="faqStatus" name="faqStatus" value="1"
										checked>
					비공개<input type="radio" id="faqStatus" name="faqStatus" value="2">
								</c:when>
								<c:when test="${f.faqStatus == 2 }">
					공개 <input type="radio" id="faqStatus" name="faqStatus" value="1">
					비공개<input type="radio" id="faqStatus" name="faqStatus" value="2"
										checked>
								</c:when>
							</c:choose></td>
					</tr>
					<tr class="table-dark">
						<th class ="th-title">내용</th>
						<td colspan="3" style="text-align: left;"><textarea
								id="faqContent" name="faqContent" class="form-control"
								style="background-color: gray;">${f.faqContent }</textarea></td>
					</tr>
					<tr>
						<th colspan="4" style="text-align: center;">
							<button type="submit" class="btn btn-danger btn-block"
								style="width: 90%; background-color: #111111">질문하기</button>
						</th>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="faqNo" value="${f.faqNo }">
		</form>
	</div>
	<script>
		$(function() {
			$("#faqContent").summernote({
				height : 400,
				lang : "ko-KR",
				callbacks : {
					onImageUpload : function(files) {
						for (var i = 0; i < files.length; i++) {
							faqUploadImage(files[i], this);
						}
					}
				}
			});
		});
		function faqUploadImage(file, editor) {
			//form과 같은 효과를 내는 객체생성
			var form = new FormData();
			form.append("file", file); //네임,value 효과
			$.ajax({
				url : "/faqUploadImage",
				type : "post",
				data : form, //넘겨줄 데이터
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				success : function(data) {
					//결과를 받은 업로드 경로를 이용해서 에디터에 이미지 추가(img src부분이 저장한 경로로 들어감)
					$(editor).summernote("insertImage", data);
				}
			});
			//1.processData : 기본값이 true(넘겨줄 데이터를 전부 String (문자열로 바꿔서 전송)){key1 : value1 , key2 : value2, key3 : value3} >> 이미지를 넘기기엔 String으로 보내기엔 파일 전송시 용량이 너무큼
			//파일 전송시 String이 이아닌 파일 형태로 전송하기위해 기본설정 제거(false)
			//2.contentType : 기본값 "application/x-www-form-urlencoded;charset=UTF-8"
			//파일 전송 시>> enctype="multipart/form-data"로 변환하기 위해 기본값 제거(데이터에 따라 자동적으로 변환(false))
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
</body>
</html>
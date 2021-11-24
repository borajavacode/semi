<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#selectBox>div{
	display:none;
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
	<div class="container" style="background-color: #343A40; padding-top: 15px; margin-top:80px">
		<div>
			<select class="form-control" id=type style="border: 1px solid #ededed; background-color: ">
				<option value="#">작성할 유형을 선택하세요</option>
				<option value=1>자유게시판</option>
				<option value=2>동료모집</option>
			</select>
		</div>
		<div id="selectBox">
			<div class="option1">
				<form action="/boardWrite" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="boardLevel" value="1">
					<table class="table" style="width: 100%;">
						<tr class="table-dark">
							<th class ="th-title">제목</th>
							<td colspan="3"><input type="text" name="boardTitle"
								class="form-control"></td>
						</tr>
						<tr class="table-dark">
							<th class ="th-title">작성자</th>
							<td>${sessionScope.m.memberId }<input type="hidden"
								name="boardWriter" value="${sessionScope.m.memberId }">
							</td>
							<th>첨부파일</th>
							<td style="text-align: left;"><input type="file"
								name="upfile"></td>
						</tr>
						<tr class="table-dark">
							<th class ="th-title">내용</th>
							<td colspan="3" style="text-align: left;" ><textarea
									id="boardContent" name="boardContent" class="form-control" style="background-color: #ededed;"></textarea>
							</td>

						</tr>
						<tr class="table-dark">
							<th colspan="4" style="text-align: center;">
								<button type="submit" class="btn btn-dark btn-block"
									style="width: 80%; margin: 0 auto; background-color: #111111">자유게시판등록</button>
							</th>
						</tr>
					</table>
				</form>
			</div>
			<div class="option2">
				<form action="/boardWrite" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="boardLevel" value="2">
					<table class="table" style="width: 100%;">
						<tr class="table-dark">
							<th>제목</th>
							<td colspan="3"><input type="text" name="boardTitle"
								class="form-control"></td>
						</tr>
						<tr class="table-dark">
							<th>작성자</th>
							<td>${sessionScope.m.memberId }<input type="hidden"
								name="boardWriter" value="${sessionScope.m.memberId }">
							</td>
							<th>모집날짜</th>
							<td style="text-align: left;"><input type="date" id="datepick" name="deadLine" required="required">
							</td>
						</tr>
						<tr class="table-dark">
							<th>내용</th>
							<td colspan="3" style="text-align: left;"><textarea
									id="boardContent2" name="boardContent" class="form-control "></textarea>
							</td>
						</tr>
						<tr class="table-dark">
							<th colspan="4" style="text-align: center;">
								<button type="submit" class="btn btn-dark btn-block"
									style="width: 80%; margin: 0 auto; background-color: #111111">동료모집등록</button>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/js/main.js"></script>
	<script>
		//최소날짜 정하기
    	var dateElement = document.getElementById('datepick');
   		var date = new Date();
    	dateElement.value = formatDate(date);
    	console.log(dateElement.value);
    	dateElement.setAttribute("min", dateElement.value);
    	//오늘날짜+1
    	function formatDate(date) { 
    		var d = new Date(date), month = '' + (d.getMonth() + 1), day = '' + (d.getDate()+1), year = d.getFullYear(); 
    		if (month.length < 2) month = '0' + month; 
    		if (day.length < 2) day = '0' + day; 
    		return [year, month, day].join('-'); 
    		}
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
		$("#type").change(function() {
			var state = $("#type option:selected").val();
				console.log(state);
				if(state== 1){
					$(".option1").show();
				}else{
					$(".option1").hide();
				}
				if(state==2){
					$(".option2").show();
				}else{
					$(".option2").hide();
				}
		});
		
	</script>
	
</body>
</html>
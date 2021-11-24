<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>포토갤러리 - 글쓰기</title>
<style>
.container form{
	color:#fff;
	margin-top: 70px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<form action="/photoWrite" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>포토갤러리 - 게시글 작성</legend>
				<table class="table" style="width:100%">
					<tr class="table-thema">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="photoTitle" class="form-control" placeholder="제목을 입력하세요">
						</td>
					</tr>
					<tr class="table-thema">
						<th>작성자</th>
						<td>
							${sessionScope.m.memberId }
							<input type="hidden" name="photoWriter" value="${sessionScope.m.memberId }">
						</td>
						<th>첨부파일</th>
						<td style="text-align: left;">
							<input type="file" name="upfile" id="upfile" onchange="loadImg(this);" accept=".jpg, .jpeg, .png, .gif" required>
						</td>
					</tr>
					<tr class="table-thema">
						<th>이미지<br>미리보기</th>
						<td colspan="3">
							<div class="img-viewer">
								<img id="img-view" width="800px">
							</div>
						</td>
					</tr>
					<tr class="table-dark">
						<th>내용</th>
						<td colspan="3">
							<textarea name="photoContent" class="form-control" placeholder="내용을 입력하세요" style="height: 300px;" required></textarea>
						</td>
					</tr>
					<tr class="table-thema">
						<td colspan="4">
							<button type="submit" class="btn btn-primary">등록하기</button>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		function loadImg(obj){
			$("#img-view").attr("src","");
			var files=obj.files;
			if(files.length!=0){
				var reader=new FileReader();
				reader.readAsDataURL(files[0]);
				reader.onload=function(e){
					$("#img-view").attr("src",e.target.result);
				}
			}else{
				$("#img-view").attr("src","");
			}
		}
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
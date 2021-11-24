<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>포토갤러리 - 글수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<form action="/photoUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="photoNo" value="${p.photoNo }">
			<fieldset>
				<legend>포토갤러리 - 글수정</legend>
				<table class="table" style="width: 100%">
					<tr class="table-thema">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="photoTitle" class="form-control" value="${p.photoTitle }">
						</td>
					</tr>
					<tr class="table-thema">
						<th>작성자</th>
						<td>${p.photoWriter }</td>
						<th>첨부파일</th>
						<td style="text-align: left;">
							<input type="hidden" name="status" value="1">
							<c:choose>
								<c:when test="${not empty p.photoFilename}">
									<img src="/img/icon/file.png" width="16px" class="delFile">
									<span class="delFile">${p.photoFilename }</span>
									<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
									<input type="file" name="upfile" onchange="loadImg(this);" accept=".jpg, .jpeg, .png, .gif" style="display:none;">
		   							<input type="hidden" name="oldFilename" value="${p.photoFilename }">
		   							<input type="hidden" name="oldFilepath" value="${p.photoFilepath }"> 
								</c:when>
								<c:otherwise>
									<input type="file" name="upfile" onchange="loadImg(this);" accept=".jpg, .jpeg, .png, .gif">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="table-thema">
						<th>이미지<br>미리보기</th>
						<td colspan="3">
							<div class="img-viewer">
								<img id="img-view" width="800px"><br>
								<img src="/upload/photo/${p.photoFilepath }" id="img-x" width="800px">
							</div>
						</td>
					</tr>
					<tr class="table-dark">
						<th>내용</th>
						<td colspan="3">
							<textarea name="photoContent" class="form-control" style="height: 300px;">${p.photoContent }</textarea>
						</td>
					</tr>
					<tr class="table-thema">
						<td colspan="4">
							<button type="submit" class="btn btn-primary">수정하기</button>
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
		
		$("#delBtn").click(function(){
			$(".delFile").hide();
			$(this).next().show();
			$("#img-x").attr("src","");
			$("[name=status]").val(2);
			$("[name=upfile]").attr("required" , true);
		});
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
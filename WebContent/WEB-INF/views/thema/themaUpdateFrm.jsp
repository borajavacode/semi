<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테마 수정</title>
    <link href="css/ThemaDefault.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/insertThema.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body style="background-color: #bdbdbd;">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container" style="margin-top: 100px;">
        <form action="/updateThema" method="post" enctype="multipart/form-data">
        <input type="hidden" name="themaNo" value="${t.themaNo }">
            <table class="table">
                <tr class="table-thema">
                    <th>테마 이름</th>
                    <td colspan="3">
                        <input type="text" class="form-control" name="themaName" value="${t.themaName }">
                    </td>
                </tr>
                <tr class="table-thema">
                    <th>난이도</th>
                    <td>
                        <div class="count">
                            <button type="button" class="btn btn-dark btn-sm" style="background-color: transparent;">-</button>
                            <input type="text" name="diff" value="${t.difficult }" readonly style="background-color: transparent;color: #fff;">
                            <button type="button" class="btn btn-dark btn-sm" style="background-color: transparent;">+</button>
                        </div>
                    </td>
                    <th>첨부파일</th>
                    <td>
                    <input type="hidden" name="status" value="1">
						<c:choose>
							<c:when test="${t.filepath != null }">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile">${t.filepath }</span>
								<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
								<input type="file" name="upfile" style="display: none;">
								<input type="hidden" name="oldFilepath" value="${t.filepath }">
							</c:when>
							<c:otherwise>
								<input type="file" name="upfile">							
							</c:otherwise>
						</c:choose>
                    </td>
                </tr>
                <tr class="table-thema">
                    <th>위치</th>
                    <td colspan="3">
                        <input type="text" class="form-control" name="themaAddr" value="${t.themaAddr }">
                    </td>
                </tr>
                <tr class="table-dark">
                    <th>설명</th>
                    <td colspan="3">
                        <textarea name="themaContent" class="form-control">${t.themaContent }</textarea>
                    </td>
                </tr>
                <tr class="table-default">
                    <td colspan="4" style="text-align: center;">
                        <button type="submit" class="btn btn-dark" style="width: 500px;">변경하기</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <script>
		$(".count>button").click(function() {
			var currAmount = Number($("[name=diff]").val());
			if($(this).html() == "+"){
                if(currAmount != 5){
                    $("[name=diff]").val(++currAmount);
                }
			}else{
				if(currAmount != 1){
					$("[name=diff]").val(--currAmount);
				}
			}
		});
		$("#delBtn").click(function() {
			$(".delFile").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		});
    </script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="/js/main.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테마 추가</title>
    <link href="css/ThemaDefault.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/insertThema.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body style="background-color: #bdbdbd;">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container" style="margin-top: 100px;">
        <form action="/themaWrite" method="post" enctype="multipart/form-data">
            <table class="table">
                <tr class="table-thema">
                    <th>테마 이름</th>
                    <td colspan="3">
                        <input type="text" class="form-control" name="themaName">
                    </td>
                </tr>
                <tr class="table-thema">
                    <th>난이도</th>
                    <td>
                        <div class="count">
                            <button type="button" class="btn btn-dark btn-sm" style="background-color: transparent;">-</button>
                            <input type="text" name="diff" value="1" readonly style="background-color: transparent;color: #fff;">
                            <button type="button" class="btn btn-dark btn-sm" style="background-color: transparent;">+</button>
                        </div>
                    </td>
                    <th>첨부파일</th>
                    <td>
						<input type="file" name="upfile">			
                    </td>
                </tr>
                <tr class="table-thema">
                    <th>위치</th>
                    <td colspan="3">
                        <input type="text" class="form-control" name="themaAddr">
                    </td>
                </tr>
                <tr class="table-dark">
                    <th>설명</th>
                    <td colspan="3">
                        <textarea name="themaContent" class="form-control"></textarea>
                    </td>
                </tr>
                <tr class="table-default">
                    <td colspan="4" style="text-align: center;">
                        <button type="submit" class="btn btn-dark" style="width: 500px;">등록</button>
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
    </script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="/js/main.js"></script>
</body>
</html>
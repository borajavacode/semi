<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ThemaList</title>
<link href="/css/ThemaDefault.css" rel="stylesheet">
<link href="/css/ThemaList.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!--상단 이미지/제목-->
    <div class="imgBox">
        <img src="/img/kh escape.png">
        <h1>Thema</h1>
    </div>
    <div class="container con">
        <div class="content_head" style="background-color: #090000; padding: 15px 0;">
            <!--추가 버튼 - 관리자-->
            <div class="add">
            <c:if test="${sessionScope.m!=null && sessionScope.m.memberLevel == 1 }">
                <a href="/themaWriteFrm" class="btn btn-outline-danger">테마 추가</a>
            </c:if>
            </div>            	
            <!--검색 박스-->
            <div id="searchBox1" style="padding-right: 20px;">
                <form action="/searchThema" method="post">
                	<select name="type" style="color: #000000;border-radius: 0;">
                		<c:choose>
                			<c:when test="${type == 'loc' }">
                				<option value="thema">테마</option>
                        		<option value="loc" selected>위치</option>
                        		<option value="diff">난이도</option>
                			</c:when>
                			<c:when test="${type == 'diff' }">
                				<option value="thema">테마</option>
                        		<option value="loc">위치</option>
                        		<option value="diff" selected>난이도</option>
                			</c:when>
                			<c:otherwise>
                			    <option value="thema" selected>테마</option>
                        		<option value="loc">위치</option>
                        		<option value="diff">난이도</option>
                			</c:otherwise>
                		</c:choose>
                    </select> 
                    <input type="text" name="keyword" class="form-control" value="${keyword }" style="border-radius: 0;">
                    <button type="submit" class="btn btn-light"><img src="img/search.png"></button>
                </form>
            </div>
        </div>
        <!--테마소개-->
        <div class="content">
            <ul id="themaList">
				
            </ul>
        </div>
        <div class="btnBox">
            <button class="btn btn-danger" id="more-btn" currentCount="0" totalCount="${totalCount }" value="1">더보기</button>
        </div>
    </div>
    <script>
		$("#more-btn").click(function() {
			var start = $(this).val();
			var keyword = $("[name=keyword]").val();
			var type = $("[name=type]").val();
			console.log(type);
			$.ajax({
				url : "/moreThema",
				data : {start:start, keyword:keyword, type:type},
				type : "post",
				success : function(data) {
					for(var i=0; i<data.length;i++){
						var t = data[i];
						var html = "<li class='thema'>";
						html += "<a href='/themaView?themaNo="+t.themaNo+"'>";
						html += "<div class='themaImg'>";
						html += "<img src='/img/thema/"+t.filepath+"' style='width:175px; height:175px;'>";
						if(t.themaName.length > 10){
							html += "<h3>"+t.themaName.substring(0,7)+"ㆍㆍㆍ</h3></div>";							
						}else{
							html += "<h3>"+t.themaName+"</h3></div>";
						}
						html += "<h4>"+t.themaAddr+"</h4><div>";
						for(var j=0; j<t.difficult; j++){
							html += "<img src='/img/key.png'>";
						}
						html += "</div></a></li>";
						$("#themaList").append(html);
					}
					//가지고온 데이터를 화면에 출력하고 다음 요청을 위한 값 변경
					$("#more-btn").val(Number(start)+8);
					//지금까지 읽어온 게시물의 수를 변경(읽어온 배열의 길이만큼 기존값에 더한다.)
					var curr = Number($("#more-btn").attr("currentCount"));
					$("#more-btn").attr("currentCount", curr+data.length);
					//전체 게시물 수
					var totalCount = $("#more-btn").attr("totalCount");
					//변경된 지금까지 읽어온 게시물 수
					var currCount = $("#more-btn").attr("currentCount");
					//지금까지 읽어온 게시물과 전체 게시물수가 같으면 더보기 버튼 비활성화
					if(currCount == totalCount){
						$("#more-btn").css("display", "none");
					}
				}
			});
		});
		$(function() {
			$("#more-btn").click();
		});
	</script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="/js/main.js"></script>
</body>
</html>
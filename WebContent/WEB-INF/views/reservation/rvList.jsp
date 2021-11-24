<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약 내역</title>
<link rel="stylesheet" href="/css/bootstrap.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
@font-face {
	font-family: "g_r";
	src: url(/font/GothicA1-Regular.ttf);
}

body {
	font-family: "g_r";
	color: #111111;
}
.head>button {
	width: 30%;
}
.t2{
height:500px;
overflow: auto;

}
.t2::-webkit-scrollbar {
  width: 6px;
}
.t2::-webkit-scrollbar-track {
  background-color: transparent;
}
.t2::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: gray;
}
.t2::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}


h3{
color:#ffff;
}

</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/myheader.jsp"/>
<div class="container" style="margin-top:150px;">
<h3>예약 관리</h3>
<table class="table table-th table-hover">
<input class="selDate" type="date"><div style="float:right;"><button id="search" style="padding-top:1px;padding-bottom:1px;" type="button" class="btn btn-outline-light">검색</button></div><div style="float:right;"><input id="searchInput" style="" type="text"></div><div style="float:right;"><select style="padding-top: 3px; padding-bottom:3px;" class="form-select" id="type" >
        <option selected>예약번호</option>
        <option>테마</option>
        <option>지점</option>
      </select></div>
<tr class="table-thema" style="text-align:center;">
<th style="">예약번호</th><th>테마명</th><th>지점</th><th>날짜</th><th>시간</th><th>이름</th><th>전화번호</th><th>인원</th><th>예약취소</th>
</tr>
</table>
<div class="table-container t2">
<table class="table table-th table-hover" style="text-align:center;">
<c:forEach items="${rvList }" var="rv">
<tr class="table-dark" style="display:none;">
<th style=" padding-left:50px;" class="no">${rv.rvNo }</th><th class="thema">${rv.rvThema }</th><th class="point">${rv.rvPoint }</th><th class="date">${rv.rvDate }</th><th>${rv.rvTime }</th><th>${rv.rvName }</th><th>${rv.rvPhone }</th><th>${rv.rvPerson }</th><th><button style="padding-top:2px;padding-bottom:2px;" type="button"  class="btn btn-danger delete" >취소하기</button><input type="hidden" value="${rv.rvNo }"></th>
</tr>
</c:forEach>
</table>
</div>
</div>
<br>
<script>
$(function(){
	var selDate="";
	$(".selDate").change(function(){
		selDate = $(this).val();
		var date = $(".date");
		date.parent().hide();
		
		for(var i = 0; i<date.length;i++){
			if(selDate == date.eq(i).html()){
						
				date.eq(i).parent().fadeIn();
			}
		}
		
	});
	$("#search").click(function(){
		var type=$("#type").val();
		var text = $("#searchInput").val();
		$(".no").parent().hide();
		if(selDate==""){
			if(type=="예약번호"){
				for(var i = 0; i<$(".no").length;i++){
					if(text == $(".no").eq(i).html()){
								
						$(".no").eq(i).parent().fadeIn();
					}
				}
				
				
			}else if(type=="테마"){
				for(var i = 0; i<$(".thema").length;i++){
					if(text == $(".thema").eq(i).html()){
								
						$(".thema").eq(i).parent().fadeIn();
					}
				}
				
			}else if(type=="지점"){
				for(var i = 0; i<$(".point").length;i++){
					if(text == $(".point").eq(i).html()){
								
						$(".point").eq(i).parent().fadeIn();
					}
				}
			}
		}else{
			if(type=="예약번호"){
				for(var i = 0; i<$(".no").length;i++){
					if(text == $(".no").eq(i).html()){
								
						$(".no").eq(i).parent().fadeIn();
					}
				}
				
				
			}else if(type=="테마"){
				for(var i = 0; i<$(".thema").length;i++){
					if(text == $(".thema").eq(i).html()&&selDate == $(".date").eq(i).html()){
								
						$(".thema").eq(i).parent().fadeIn();
					}
				}
				
			}else if(type=="지점"){
				for(var i = 0; i<$(".point").length;i++){
					if(text == $(".point").eq(i).html()&&selDate == $(".date").eq(i).html()){
								
						$(".point").eq(i).parent().fadeIn();
					}
				}
			}
			
		}
		
	});
	$(".delete").click(function(){
		
		var rvNo = $(this).next().val();
		if(confirm("취소하시겠습니까?")){
			$
			.ajax({
				url : "/deleteReservation",
				type : "post",
				data : {
					rvNo : rvNo
					
				},
				success : function(data) {

					alert("예약 취소 완료");
					location.href="/rvList";

				},
				error : function() {
					console.log("error");
				}

			});
		}
	});
	
	
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script src="/js/main.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#active{
	background : #df723d;
	width : 80px;
	height : 150px;
	text-align: center;
	font-size: 14px;
	line-height : 150px;
	color : #fff;
	border-radius: 10px;
	margin: 10px auto;
}
#activeFinish{
	background : #888;
	width : 80px;
	height : 150px;
	text-align: center;
	font-size: 14px;
	line-height : 150px;
	color : #fff;
	border-radius: 10px;
	margin: 10px auto;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<!-- noev css -->
	<link rel="stylesheet" href="/css/noev.css">
	<div class="container" style="padding-top: 15px;margin-top:90px;">
	<fieldset>
		<legend><h2>이벤트</h2></legend>
		<c:if test="${sessionScope.m.memberLevel eq 1 }">
			<div style="margin-bottom: 70px;">
				<a href="/eventWriteFrm" class="btn btn-info writeBtn">글쓰기</a>
			</div>
		</c:if>
		<div class="eventWrapper"></div>
		<button class="btn btn-info" currentCount="0" totalCount="${totalCount }" value="1" id="more-btn" style="margin-top:100px;'">더보기</button>
	</fieldset>
	</div>
	<script>
		$("#more-btn").click(function(){
			var start = $(this).val();
			$.ajax({
				url : "/eventMore",
				data : {start : start},
				type : "post",
				success : function(data){
					for(var i=0;i<data.length;i++){
						var p = data[i];
						var html = "";
						html += "<table class = 'table'>";
						html += "<tr class='table-dark'>";
						html += "<td rowspan='3' style='vertical-align:middle;text-align:center;'><img style='width:200px;higth:200px;' src='upload/event/"+p.mainEvent+"'></td>";
						html += "<td style='width:65%'>"+p.eventTitle+"</td>";
						var dateCheck = dateChecked(p.eventFinishDate);
						//오늘날짜랑 비교한 후
						console.log(dateCheck);
						if(dateCheck === true){
							html += "<td class='bo_btn' rowspan='3'><p id='active'>진행중</p></td>";
						}else{
							html += "<td class='bo_btn' rowspan='3'><p id='activeFinish'>완료</p></td>";
						}
						html += "</tr>";
						html += "<tr class='table-dark' style='height:30px;'>";
						html += "<td>"+p.eventWriter+" | "+p.eventReadcount+"</td>";
						html += "</tr>";
						html += "<tr class='table-dark'>";
						html += "<td><a href='/eventView?eventNo="+p.eventNo+"'>자세히보기</a></td>";
						html += "</tr></table>";
						$(".eventWrapper").append(html);
					}
					$("#more-btn").val(Number(start)+5);
					var curr = Number($("#more-btn").attr("currentCount"));
					$("#more-btn").attr("currentCount",curr+data.length);
					var totalCount = $("#more-btn").attr("totalCount");
					var currCount = $("#more-btn").attr("currentCount");
					if(currCount == totalCount){
						$("#more-btn").prop("disabled",true);
					}
					
				}
			});
		});
		$(function(){
			$("#more-btn").click();
		});
		function dateChecked(d1){
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth()+1;
			var day = date.getDate();
			day = day >= 10 ? day : '0' + day;
			var getToday = year+"-"+month+"-"+day;
			var today =getToday.split("-").join("");
			console.log(today);
			var finishDate =d1.split("-").join("");
			console.log(finishDate);
			if(today<=finishDate){
				return true;
			}else{
				return false;
			}
		}		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script src="/js/main.js"></script>
</body>
</html>
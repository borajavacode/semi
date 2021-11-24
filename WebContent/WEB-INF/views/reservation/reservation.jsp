<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약</title>

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

.table-light {
	
}

th {
	text-align: center;
}

td {
	text-align: center;
	overflow: hidden;
}

.margin0 {
	margin: 0 auto;
}

.t1{
height:700px;
overflow:auto;
width:100%;}

.t1::-webkit-scrollbar {
  width: 6px;
}
.t1::-webkit-scrollbar-track {
  background-color: transparent;
}
.t1::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: gray;
}
.t1::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}


.point>label, .slide  {
	display: none;
}
#img1{
width : 100%;}
.tImg{
width: 73px;
}



</style>

</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	

	
	<div class="container">
	<img style="margin-top: 100px; margin-bottom:50px;" id ="img1"src="/img/room1.png">
	
	<br><br>
	<c:if test="${not empty tm  }">
	<input id="tm" type="hidden" value="${tm }">
	<input id="pt" type="hidden" value="${pt }">
	</c:if>
		<div class="head" style="text-align: center; margin-bottom:50px;">
			<button type="button" class="btn btn-danger">step 1 예약하기</button>
			<button type="button" class="btn btn-outline-danger" disabled>step
				2 결제하기</button>
			<button type="button" class="btn btn-outline-danger" disabled>step
				3 예약완료</button>
		</div>
		<br>
		<div class="main">
		<table class="table table-th table-hover  ">
					<tr class="table-thema">
						<th style="text-align:center;">날짜</th>
						<th style="text-align:center;">지점</th>
						<th  style="text-align:center;">테마</th>
						<th style="text-align:center;">시간</th>
					</tr>
					</table>
			<form action="/payment" method="post">
			
			<div class="t1" id="t1">
			
				<table class="table table-hover ">
					
					<tr class="table-dark">
						<td rowspan="2"><div id="datepicker"></div>
							<input name="date" type="hidden"></td>

						

					</tr>
					
					<tr class="table-dark t3">
					
						<td class="point" ><span>날짜를 선택해주세요</span> <c:forEach
								items="${tList }" var="tList" varStatus="i">
								<input type="radio" name="point" id="btnradio${i.index }"
									class="btn-check" value="${tList.themaAddr }">
								<label class="btn btn-outline-secondary"
									for="btnradio${i.index }">${tList.themaAddr }</label>
								<br>
								

							</c:forEach></td>
						<td class="themaImg"></td>
						<td class="thema">지점을 선택해주세요</td>
						<td class="time">테마를 선택해주세요</td>
						
					</tr>


				</table>
				
				</div>

				
				
				<table class="table table-hover slide">
					<tr class="talbe-dark">
						<td><button type="submit" class="btn btn-success">step
								2 결제하기</button></td>
					</tr>
				</table>
			</form>
		</div>

	</div>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script>
	
	

		var selectDate = new Date();
		$(function() {
			
			var datepicker = $("#datepicker").datepicker();

		});
		var date = new Date();
		
		
		
		
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var today = year+"-"+month+"-"+day;
		var maxYear = year;
		var maxMonth = month;
		var maxDay = day+14;
		if(maxDay>31){
			maxDay = day-31+14;
			maxMonth +=1;
			if(maxMonth>12){
				maxMonth=1;
				maxYear+= 1;
			}
			
		}
		
		
		
		var max =maxYear+"-"+maxMonth+"-"+maxDay;
		
		$.datepicker.setDefaults({
			dateFormat : 'yy-mm-dd',
			prevText : '이전 달',
			nextText : '다음 달',
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
					'9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
					'9월', '10월', '11월', '12월' ],
			dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			showMonthAfterYear : true,
			yearSuffix : '년',
			minDate : date,
			maxDate : max

		});
		
		if($("#tm").val()!=null){
			$(window).scrollTop(400);
			var pt = $("#pt").val();
			var tm = $("#tm").val();
			$("input[name=date]").val(today);
			$(".point>span").hide();
			$(".point>label").show();
			
			for(var i = 0; i<$("input[name=point]").length;i++){
				
				var b = "#btnradio"+i;
				if($(b).val()==pt){
					$(b).click();
					
				}
					
			}
			
			
			
			
			
			
			
			var point = pt;
			
			
			var thema = $(".thema");
			thema.html("");
			var themaImg = $(".themaImg");
			
			$
					.ajax({
						url : "/themaLoad",
						type : "post",
						data : {
							point : point
						},
						success : function(data) {
							
							themaImg.html("");
							
							for (var i = 0; i < data.length; i++) {
								var imgHtml = "<img class='tImg' src='/img/thema/"+data[i].filepath+"'><br><br>";
								var html = "<input value='"+data[i].themaName+"' type='radio' class='btn-check' name='thema' id='tradio"+i+"' ><label class='btn btn-outline-secondary' for='tradio"+i+"'>"
										+ data[i].themaName
										+ "</label><br><br><br>";
								themaImg.append(imgHtml);
								var thema = $(".thema");
								thema.append(html);
								
								
								for(var i = 0; i<$("input[name=thema]").length;i++){
									var t = "#tradio"+i;
									
									if($(t).val()==tm){
										$(t).click();
										
										
									}
								}
								
							}

						},
						error : function() {
							console.log("error");
						}

					});
			
			
			
			
			
			
			
			
			
			
			
			
		}

		$(function() {
			var point = "";
			var thema = "";

			$("#datepicker").children().addClass("margin0");
			$("#datepicker").change(function() {
				selectDate = $(this).val();
				$(".slide").fadeOut();
				$("input[name=date]").val(selectDate);
				$(".time").html("테마를 선택해주세요");
				$(".point>span").hide();
				$(".point>label").show();
				$(window).scrollTop(400);
				



			});
			$("input[name=point]")
					.click(
							function() {
								point = $(this).val();
								$(".slide").fadeOut();
								
								var thema = $(".thema");
								var themaImg = $(".themaImg");
								$(".time").html("테마를 선택해주세요");
								$
										.ajax({
											url : "/themaLoad",
											type : "post",
											data : {
												point : point
											},
											success : function(data) {
												console.log(data);
												themaImg.html("");
												thema.html("");
												for (var i = 0; i < data.length; i++) {
													var imgHtml = "<img class='tImg' src='/img/thema/"+data[i].filepath+"'><br><br>";
													var html = "<input value='"+data[i].themaName+"' type='radio' class='btn-check' name='thema' id='tradio"+i+"' ><label class='btn btn-outline-secondary' for='tradio"+i+"'>"
															+ data[i].themaName
															+ "</label><br><br><br>";
													themaImg.append(imgHtml);
													thema.append(html);
												}

											},
											error : function() {
												console.log("error");
											}

										});
							});

		});
		$(document)
				.on(
						'click',
						'input[name=thema]',
						function() {
							
							$(".slide").fadeOut();
							thema = $(this).val();
							var time = $(".time");
							var date = new Date();
							
							
							var year = date.getFullYear();
							var month = date.getMonth()+1;
							var day = date.getDate();
							if(day<10){
								day="0"+day;
							}
							var today = year+"-"+month+"-"+day;
							
							var hour = date.getHours();
							var minute = date.getMinutes();
							
							if($("#tm").val()!=null){
								thema =$("#tm").val();
								selectDate = today;
								$("input[name=date]").val(selectDate);
							}
	
							time.html("");
							$
									.ajax({
										url : "/rvCheck",
										type : "post",
										data : {
											date : selectDate,
											thema : thema
										},
										success : function(data) {

											for (var i = 10; i < 22; i++) {
												var k = 0;
												for (var j = 0; j < data.length; j++) {
													if (i + ":00" == data[j].rvTime) {

														var html = "<input disabled type='radio' value='"+i+":00' class='btn-check' name='time' id='timeradio"+i+"' ><label class='btn btn-outline-warning' for='timeradio"+i+"'>"
																+ i
																+ ":00 예약불가"
																+ "</label><br><br>";
														time.append(html);
														k = 1;

													} 
													

												}
												if (selectDate == today && hour >= i&&k!=1) {
													var html = "<input disabled type='radio' value='"+i+":00' class='btn-check' name='time' id='timeradio"+i+"' ><label class='btn btn-outline-warning' for='timeradio"+i+"'>"
															+ i
															+ ":00 예약불가"
															+ "</label><br><br>";
													time.append(html);
													k = 2;
												}
												if (k != 1 && k!=2) {
													var html = "<input type='radio' value='"+i+":00' class='btn-check' name='time' id='timeradio"+i+"' ><label class='btn btn-outline-secondary' for='timeradio"+i+"'>"
															+ i
															+ ":00 예약가능"
															+ "</label><br><br>";
													time.append(html);
												}

											}
											$("#tm").remove();

										},
										error : function() {
											console.log("error");
										}

									});
						});

		$(document).on('click', 'input[name=time]', function() {
			$(".slide").slideDown();
			$("button[type=submit]").focus();
		});
	  
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>

</html>
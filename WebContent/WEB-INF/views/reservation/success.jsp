<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약 완료</title>
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- 글리치 -->

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
#img1{
width:100%;}
.tImg{
width: 100%;
}
#thImg{
width:250px;
}
.glitch {
  color: white;
  font-size: 40px;
  position: relative;
  width: 450px;
  margin: 0 auto;
}

@keyframes noise-anim {
  0% {
    clip-path: inset(49% 0 50% 0);
  }
  5% {
    clip-path: inset(17% 0 9% 0);
  }
  10% {
    clip-path: inset(43% 0 54% 0);
  }
  15% {
    clip-path: inset(81% 0 11% 0);
  }
  20% {
    clip-path: inset(14% 0 26% 0);
  }
  25% {
    clip-path: inset(88% 0 11% 0);
  }
  30% {
    clip-path: inset(91% 0 2% 0);
  }
  35% {
    clip-path: inset(42% 0 5% 0);
  }
  40% {
    clip-path: inset(84% 0 15% 0);
  }
  45% {
    clip-path: inset(53% 0 20% 0);
  }
  50% {
    clip-path: inset(90% 0 3% 0);
  }
  55% {
    clip-path: inset(20% 0 73% 0);
  }
  60% {
    clip-path: inset(34% 0 34% 0);
  }
  65% {
    clip-path: inset(54% 0 13% 0);
  }
  70% {
    clip-path: inset(100% 0 1% 0);
  }
  75% {
    clip-path: inset(99% 0 2% 0);
  }
  80% {
    clip-path: inset(71% 0 5% 0);
  }
  85% {
    clip-path: inset(93% 0 8% 0);
  }
  90% {
    clip-path: inset(81% 0 17% 0);
  }
  95% {
    clip-path: inset(51% 0 37% 0);
  }
  100% {
    clip-path: inset(73% 0 26% 0);
  }
}
.glitch::after {
  content: attr(data-text);
  position: absolute;
  left: 2px;
  text-shadow: -1px 0 red;
  top: 0;
  color: white;
  background: transparent;
  overflow: hidden;
  animation: noise-anim 2s infinite linear alternate-reverse;
}

@keyframes noise-anim-2 {
  0% {
    clip-path: inset(32% 0 48% 0);
  }
  5% {
    clip-path: inset(82% 0 18% 0);
  }
  10% {
    clip-path: inset(3% 0 46% 0);
  }
  15% {
    clip-path: inset(91% 0 10% 0);
  }
  20% {
    clip-path: inset(93% 0 6% 0);
  }
  25% {
    clip-path: inset(57% 0 43% 0);
  }
  30% {
    clip-path: inset(34% 0 30% 0);
  }
  35% {
    clip-path: inset(36% 0 35% 0);
  }
  40% {
    clip-path: inset(99% 0 1% 0);
  }
  45% {
    clip-path: inset(83% 0 1% 0);
  }
  50% {
    clip-path: inset(46% 0 11% 0);
  }
  55% {
    clip-path: inset(67% 0 32% 0);
  }
  60% {
    clip-path: inset(4% 0 47% 0);
  }
  65% {
    clip-path: inset(3% 0 81% 0);
  }
  70% {
    clip-path: inset(82% 0 8% 0);
  }
  75% {
    clip-path: inset(53% 0 29% 0);
  }
  80% {
    clip-path: inset(26% 0 39% 0);
  }
  85% {
    clip-path: inset(20% 0 1% 0);
  }
  90% {
    clip-path: inset(1% 0 91% 0);
  }
  95% {
    clip-path: inset(44% 0 7% 0);
  }
  100% {
    clip-path: inset(39% 0 36% 0);
  }
}
.glitch::before {
  content: attr(data-text);
  position: absolute;
  left: ;
  text-shadow: 1px 0 blue;
  top: 0;
  color: white;
  background: transparent;
  overflow: hidden;
  animation: noise-anim-2 15s infinite linear alternate-reverse;
}
</style>



</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
body,h2{
color: #ffff;
}</style>
	<div class="container">
	<img style="margin-top:200px;margin-bottom:200px;" id ="img1"src="/img/cpl33.png">
	<br><br>
	<div class="head" style="text-align: center;margin-bottom:50px;">
			<button type="button" class="btn btn-danger" >step 1 예약하기</button>
			<button type="button" class="btn btn-danger" >step
				2 결제하기</button>
			<button type="button" class="btn btn-danger">step
				3 예약완료</button>
		</div>
		<br><br>
		<h3 style="text-align:center;"><div class="glitch" data-text="예약이 완료되었습니다.">예약이 완료되었습니다.</div>
		</h3><div style="float:right;">예약정보 이메일로 받기
		<button type="button" class="btn btn-dark" onclick="mailSend();">클릭</button></div>
		<div class="main">
		<table class="table table-th table-hover" style="">
		
		<tr class="table-thema">
		<th style="width:250px; "id="thImg" rowspan="2"><img style="width : 250px;" id="tImg" src="/img/thema/${filepath }"></th><th>테마명</th><th>예약일</th><th>지점</th><th>시간</th><th>인원</th>
		</tr>
		<tr class="table-dark">
		
		<th>${rv.rvThema }<input id="thema" type="hidden" name="thema" value="${rv.rvThema }"></th>
		<th>${rv.rvDate }<input id="date" type="hidden" name="date"value="${rv.rvDate }"></th>
		<th>${rv.rvPoint }<input id="point" type="hidden" name="point" value="${rv.rvPoint }"></th>
		<th>${rv.rvTime }<input id="time" type="hidden" name="time" value="${rv.rvTime }"></th>
		<th>${rv.rvPerson } <input id="person" type="hidden" name="person" value="${rv.rvPerson }">
      </th>
		</tr>
		</table>
		
		<table class="table table-th table-hover">
		
		<tr class="table-active">
		
		<th>
		<input type="hidden" id="email" value="${sessionScope.m.email }">
		<input type="hidden" id="name" value="${sessionScope.m.memberName }">
		<input type="hidden" id="phone" value="${sessionScope.m.phone }">
		</th>
		
		</tr>
		</table>
		
		<br>
		
		</div>
	</div>
	<script>
	$(function(){
		
	});
	function mailSend(){
		$.ajax({
			url : "/mailSend", 
			data : {email:$("#email").val(),thema:$("#thema").val(),time:$("#time").val(),point:$("#point").val(),date:$("#date").val(),person:$("#person").val(),phone:$("#phone").val(),name:$("#name").val()}, //서블리에 전송할데이터 msg 키에 msg값
			type : "post", 
			success : function(data){ 
				if(data==0){
					alert("실패");
				}else{
					alert("이메일 발송 완료");
				}
			
			}
		});
	}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>
</html>
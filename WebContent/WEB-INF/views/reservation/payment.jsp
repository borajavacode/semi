<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결제</title>
<link rel="stylesheet" href="/css/bootstrap.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<style>
@font-face {
	font-family: "g_r";
	src: url(/font/GothicA1-Regular.ttf);
}

body {
	font-family: "g_r";
	
}
.head>button {
	width: 30%;
}
.price{
font-size: 20px;}
.hide{
display:none;}
#img1{
width:100%;}
#tImg{

width:100%;
}
#thImg{
width:250px;
}
h3{
color: #ffff;
}
.white{
color: #ffff;
}
</style>
</head>
<body >
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
body,h3{
color: #ffff;
}</style>
	<div class="container">
	<img style="margin-top: 100px; margin-bottom:50px;" id ="img1"src="/img/room1.png">
	<br><br>
	<div class="head" style="text-align: center; margin-bottom:50px;">
			<button type="button" class="btn btn-danger prev" >step 1 예약하기</button>
			<button type="button" class="btn btn-danger" >step
				2 결제하기</button>
			<button type="button" class="btn btn-outline-danger" disabled>step
				3 예약완료</button>
		</div>
		<br>
		<form id="payForm" action="/success" method="post">
		<div class="main">
		<h3>예약 정보 확인</h3>
		
		
		<table class="table table-th table-hover" style="">
		
		<tr class="table-thema">
		<th id="thImg" rowspan="2"><img id="tImg" src="/img/thema/${filepath }"><input name="filepath" type="hidden" value="${filepath }"></th><th>테마명</th><th>예약일</th><th>지점</th><th>시간</th><th>인원</th>
		</tr>
		<tr class="table-dark">
		
		<th>${rv.rvThema }<input type="hidden" name="thema" value="${rv.rvThema }"></th>
		<th>${rv.rvDate }<input type="hidden" name="date"value="${rv.rvDate }"></th>
		<th>${rv.rvPoint }<input type="hidden" name="point" value="${rv.rvPoint }"></th>
		<th>${rv.rvTime }<input type="hidden" name="time" value="${rv.rvTime }">
		</th>
		<th> 
      <select class="form-select" id="select1" name="person">
              <option>1</option>
        <option selected>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <c:if test="${rv.rvPoint =='???' }"><option>456</option>
        </c:if>

      </select></th>
		</tr>
		</table>
		
		<br>
		<h3>결제 정보</h3>
		<table class="table table-th table-hover">
		<tr class="table-thema">
		<th>결제금액</th><th><span id="price" class="price">80000</span>원</th>
		<th>할인금액</th>
		<th><span id="resultDiscount" class="price">6000</span>원</th>
		<th>최종금액</th><th><span id="result" class="price">74000</span>원</th>
		</tr>
		<tr class="table-dark">
		<th >기본 할인</th>
		<th ><span style="color: #bf2222">이벤트 할인</span></th><th colspan="4" ><span id="discount" class="price">6000</span>원</th>
		</tr>
		
		</table>
		
		</div>
		<input type="hidden" id="email" value="${sessionScope.m.email }">
		<input type="hidden" id="name" value="${sessionScope.m.memberName }">
		<input type="hidden" id="phone" value="${sessionScope.m.phone }">
		<br>
		<h3>안내 사항</h3>
		<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link active"  href="#agree1">개인정보 수집 및 이용안내</a>
  </li>
  <li class="nav-item">
    <a style="color:#ffff;"class="nav-link asdf" href="#agree2">결제관련 주의사항 안내</a>
  </li>
  
</ul>
<div id="myTabContent" class="tab-content">
  <div class="tab-pane fade active show" id="content1">
  <br>
    <p> 예약서비스 제공에 관한 결제 정보 등 개인정보 수집<br>

- 이용자가 구매한 재화나 용역의 대금 결제<br>

- 결제 과정 중 본인 식별, 인증, 실명확인 및 이용자가 결제한 거래의 내역을 요청하는 경우 응대 및 확인<br>

- 이용자가 결제한 거래의 취소 또는 환불, 상품 배송 등 전자상거래 관련 서비스 제공</p>
<hr>
<input type="checkbox" value="1" id="agree1"> <label for="agree1">위의 '개인정보보호취급방침'에 동의합니다</label>
  </div>
  <div class="tab-pane fade " id="content2">
  <br>
    <p>1.결제 변경 및 취소는 예약한 게임 시작 시간 24시간 이전에만 가능합니다.<br>

2.게임 시작시간이 지나 도착하거나, 나타나지 않으면 게임에 참여할 수 없고 환불 또한 불가 합니다.<br>

3.19세이상 게임의 경우 신분증 제시를 요청할 수 있습니다.<br>

4.과도한 음주자는 게임전 음주 측정을 실시하여 혈중 알콩농도 0.1% 이상인 경우 입장이 제한 될 수 있습니다.<br>

5.공간이 폐쇄되어 갇히거나 신체가 구속될 수 있으므로 해당 사항에 거부감이 있거나 질환이 있으신 분은 참가가 어렵습니다.<br>

6.KH escape에서 경험한 퀴즈나 퍼즐등 세부적인 내용을 발설하려고 하시는 분은 입장제한대상입니다.<br>

7.당일 예약은 신용카드, 체크카드만 가능합니다. 다른 결제 수단은 지점을 내방 하셔야 합니다.</p>
<hr>
<input type="checkbox" value="1" id="agree2"> <label for="agree2">위의 '결제관련 주의사항'을 모두 확인하였습니다</label>
  </div>
  <table class="table table-hover default" style="text-align:center;">
  <tr>
  <th>
  <button type="button" class="btn btn-danger prev">이전</button>
  <button type="button" class="btn btn-danger" >안내사항에 동의해주세요</button>
  </th>
  </tr>
  </table>
  
  <table class="table table-hover hide" style="text-align:center;">
  <tr>
  <th>
  <button type="button" class="btn btn-success prev">이전</button>
  <button style="" type="submit" class="btn btn-success">결제없이 진행</button>
  <button id="payment" type="button" class="btn btn-success">결제하기</button>
  </th>
  </tr>
  </table>
</div>
		</form>
	</div>
	
	<script>
	$(function(){
		$(".nav-link").click(function(){
			$(".nav-link").removeClass("active");
			$(this).addClass("active");
			$(".nav-link").css("color","#ffff");
			$(".tab-pane").removeClass("active");
			$(".tab-pane").removeClass("show");
			if($(this).html() == "개인정보 수집 및 이용안내"){
				$("#content1").addClass("active");
				$("#content1").addClass("show");
				$(this).css("color","#111111");
				
			}else{
				$("#content2").addClass("active");
				$("#content2").addClass("show");
				$(this).css("color","#111111");
			}
		});
		$("input[type=checkbox]").click(function(){
			var check1= $("#agree1").is(':checked');
			var check2= $("#agree2").is(':checked');
			if(check1){
				$(".asdf").click();
			}
			if(check1 &&check2 ){
				$(".hide").slideDown();
				$(".default").hide();
			}else{
				$(".hide").hide();
				$(".default").slideDown();
				
			}
		});
		$(".prev").click(function(){
			location.href="/reservation";
		});
		$("#select1").change(function(){
			var val = $(this).val();
			$("#discount").html(val*3000);
			var price = val*40000;
			$("#price").html(price);
			var resultDiscount = val*3000;
			$("#resultDiscount").html(resultDiscount);
			$("#result").html(price-resultDiscount);
			
		});
		
		
		
	});
	$("#payment").click(function(){
		var price = $("#result").html();
		var d = new Date();
		var date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
		console.log(date);
		console.log(price);
		IMP.init("imp03655385"); //가맹점 식별코드
		IMP.request_pay({
			merchant_uid : "KH escape"+date, //거래아이디
			name : $("input[name=thema]").val(), //결제이름설정
			amount : $("#result").html(), //결제금액
			buyer_email : $("#email").val(), //구매자이메일
			buyer_name : $("#name").val(),//이름
			buyer_phone : $("#phone").val() //전화번호
			
			
			
			
			
		},function(rsp){
			if(rsp.success){
				alert("결제성공");
				console.log("카드승인번호 : "+rsp.apply_num);
				$("#payForm").submit();
			}else{
				alert("결제실패");
			}
		});
	});
	
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script src="/js/main.js"></script>
</body>

</html>
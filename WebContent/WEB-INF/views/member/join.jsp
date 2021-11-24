<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH escape</title>
<link rel="stylesheet" href="/css/memberDefault.css">
<style>
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-bottom: 50px; width: 600px;">
		<!-- <form action="/join" method="post" name="joinFrm" onsubmit="return finalChk();"> -->
		<form action="/join" method="post" id="joinForm" name="joinFrom" style="margin-top: 100px;">
			<fieldset class="text-fff">
				<legend class="text-center"><h2 class="text-fff">회원가입</h2></legend>
				<hr>
				<div class="innerWrap">
				
				<div class="form-group" style="background-color: #070c11;border-top-left-radius: 10px;border-top-right-radius: 10px;">
					<label class="col-form-label mt-4" for="memberId" >아이디<span id="ajaxCheck" style="font-weight: bold;"></span></label> 
					<input type="text" class="form-control" placeholder="아이디입력(소문자,영어 4~자리)" id="memberId" name="memberId" required >
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="memberPw">비밀번호<span id="ajaxPwChk" style="font-weight: bold;"></span></label> 
					<input type="password" class="form-control" placeholder="영어 소문자, 대문자, 숫자  6~16자리" id="memberPw" name="memberPw" required >
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="pwReChk">비밀번호확인<span id="msgRePwChk" style="font-weight: bold;"></span></label> 
					<input type="password" class="form-control" placeholder="비밀번호를 다시 입력하세요" id="pwReChk"  name="pwReChk" required >
				</div>
				<div class="form-group">
					<label class="col-form-label mt-4" for="memberName">이름<span id="msgNameChk" style="font-weight: bold;"></span></label>
					<input type="text" class="form-control" placeholder="김한국" id="memberName"  name="memberName" required >
				</div>
				<div class="form-group" >
					<label class="col-form-label mt-4" for="phone" style="display: block;">전화번호<span id="msgPhoneChk" style="font-weight: bold;"></span></label> 
					<select style="width:30%; height:50px;" id="ps">
						<option>010</option>
						<option>011</option>
					</select>-<input style="width:33%; height:50px; " type="text" class="pi phone-input" maxlength="4">-<input style="width:33%; height:50px;" type="text" class="pi2 phone-input" maxlength="4">
               	<input id="phone" name="phone"  type="hidden">
				</div>
				<div class="form-group" >
					<label class="col-form-label mt-4" for="email">이메일<span id="msgEmailChk" style="font-weight: bold;"></span></label> 
					<input type="email" class="form-control" placeholder="test@khescape.com" id="email"  name="email" required >
				</div>
				<div class="form-group" >
					<label for="addrSelect" class="form-label mt-4">지역</label> 
					<select class="form-select" id="addrSelect" name="addrSelect">
						<option>서울</option>
						<option>경기</option>
						<option>강원</option>
						<option>경북</option>
						<option>경남</option>
						<option>울산</option>
						<option>인천</option>
						<option>세종</option>
						<option>충북</option>
						<option>충남</option>
						<option>대구</option>
						<option>대전</option>
						<option>전남</option>
						<option>전북</option>
						<option>광주</option>
						<option>부산</option>
						<option>제주</option>
					</select>
				</div>
				</div>
			</fieldset>
				<br>
				<button type="submit" class="btn btn-outline-danger btn-lg" style="width:100%;" id="subBtn" >회원가입</button>
				<br>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		$("[name=memberId]").keyup(function(){
			var memberId = $(this).val();
			var regExp = /[a-z0-9]{4,20}/;
			if(regExp.test(memberId)){
				$.ajax({
					url : "/ajaxIdCheck",
					data : {memberId :memberId},
					type : "post",
					success : function(data){
						if(data == 0){
							$("#ajaxCheck").html("사용 가능한 아이디 입니다.");
							$("#ajaxCheck").css("color","#3c6a9b");
						}else if(data == 1){
							$("#ajaxCheck").html("이미 사용중인 아이디 입니다.");
							$("#ajaxCheck").css("color","#bf2222");
						}
					}
				});
			}else{
				$("#ajaxCheck").html("아이디는 영문+숫자로 4글자 이상입니다.");
				$("#ajaxCheck").css("color","#bf2222");
			}
		});
		$("#memberPw").keyup(function(){
			var memberPw = $(this).val();
			var pwReg = /^[a-zA-Z0-9]{6,20}$/;
			if(!pwReg.test(memberPw)){
				$("#ajaxPwChk").html("소문자, 대문자, 숫자로 6~20자리");
				$("#ajaxPwChk").css("color","#bf2222");
			}else{
				$("#ajaxPwChk").html("사용가능한 비밀번호입니다.");
				$("#ajaxPwChk").css("color","#3c6a9b");
			}
			if( $("#pwReChk").val()!=""){
	            if($("#memberPw").val() !== $("#pwReChk").val()) {
	               $("#msgRePwChk").html("비밀번호가 일치하지 않습니다!!");
	               $("#msgRePwChk").css("color", "#bf2222");
	            }else{
	               $("#msgRePwChk").html("비밀번호가 일치합니다.");
	               $("#msgRePwChk").css("color", "#3c6a9b");
	            }
	         }

		});
		$("#pwReChk").keyup(function(){
			var pw = $("#memberPw").val();
			var rePw = $(this).val();
			var reReg = /^[a-zA-Z0-9]{6,20}$/;
			if(pw == ""){
				alert("비밀번호를 먼저 입력해주세요");
				$("#memberPw").focus();
			}else if(!reReg.test(rePw)){
				$("#msgRePwChk").html("비밀번호를 6자리 이상 입력해주세요");
				$("#msgRePwChk").css("color", "#bf2222");
			}else{
				if($("#memberPw").val() !== $("#pwReChk").val()) {
					$("#msgRePwChk").html("비밀번호가 일치하지 않습니다!!");
					$("#msgRePwChk").css("color", "#bf2222");
				}else{
					$("#msgRePwChk").html("비밀번호가 일치합니다.");
					$("#msgRePwChk").css("color", "#3c6a9b");
				}
			}
		});
		$("#memberName").keyup(function() {
			var name = $("#memberName").val();
			$("#msgNameChk").html("");
			var reg = /^[가-힣]{2,4}$/;
			if (!reg.test(name)) {
				$("#msgNameChk").html("한글 2~4자 입력");
				$("#msgNameChk").css("color", "#bf2222");
			} else {
				$("#msgNameChk").html("입력OK");
				$("#msgNameChk").css("color", "#3c6a9b");
			}
		});
		$(".pi").keyup(function() {
			var num = $(".pi").val();
			console.log(num.length);
			if (num.length == 4) {
				$(".pi2").focus();
			}
		});
		$(".phone-input").keyup(function() {
			var start = $("#ps").val();
			var phone1 = $(".pi").val();
			var phone2 = $(".pi2").val();
			$("#msgPhoneChk").html("");
			var phone = start + "-" + phone1 + "-" + phone2;

			var regphone = /^\d{2,3}-\d{3,4}-\d{4}$/;
			if (!regphone.test(phone)) {
				$("#msgPhoneChk").html(" 올바른 전화번호가 아닙니다");
				$("#msgPhoneChk").css("color", "#bf2222");
			} else {
				$("#msgPhoneChk").html("입력OK");
				$("#msgPhoneChk").css("color", "#3c6a9b");
				$("#phone").val(phone);
			}
		});
		$("#email").keyup(function() {
			var email = $("#email").val();
			$("#msgEmailChk").html("");
			var emailReg = /^[a-zA-Z0-9]{4,20}@/;
			if (!emailReg.test(email)) {
				$("#msgEmailChk").html("test@gkhescape.com형식");
				$("#msgEmailChk").css("color", "#bf2222");
			} else {
				$("#msgEmailChk").html("입력OK");
				$("#msgEmailChk").css("color", "#3c6a9b");
			}
		});
		$("#subBtn").click(function() {
			var span = $("form span");
			for (var i = 0; i < span.length; i++) {
				if (span.eq(i).css("color") == "#3c6a9b") {
					$("#joinFrm").submit();
				}
				if (span.eq(i).css("color") == "#bf2222") {
					alert("양식을 확인해주세요");
					return false;
				}
			}
		});

		$(function() {
			$("#joinForm").submit(function() {
				if ($("#memberPw").val() !== $("#pwReChk").val()) {
					alert("비밀번호가 다릅니다.");
					$("#memberPw").val("").focus();
					$("#pwReChk").val("");
					return false;
				} else if ($("#memberPw").val().length < 6) {
					alert("비밀번호는 6자 이상으로 설정해야 합니다.");
					$("#memberPw").val("").focus();
					return false;
				} else if ($.trim($("#memberPw").val()) !== $("#memberPw").val()
					|| $.trim($("#email").val()) !== $("#email").val()
					|| $.trim($("#memberId").val()) !== $("#memberId").val()) {
					alert("공백은 입력이 불가능합니다.");
					return false;
				} else if ($("#memberName").val().length < 2) {
					alert("이름은 2글자 이상입니다.");
					$("#memberName").val("").focus();
					return false;
				} else if ($("#phone").val().length < 12) {
					alert("010-0000-0000 양식을지켜주세요");
					$("#phone").val("").focus();
					return false;
				} else if ($("#ajaxCheck").html() == "이미 사용중인 아이디 입니다.") {
					alert("아이디를 확인해주세요");
					$("#memberId").val("").focus();
					return false;
				}
			})
		});
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
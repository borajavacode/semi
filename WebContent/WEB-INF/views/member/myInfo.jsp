<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH escape</title>
<link rel="stylesheet" href="/css/memberDefault.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/myheader.jsp"/>
	<div class="container text-fff" style="margin: 150px auto;">
		<h2>내 정보 관리</h2>
		<hr>
		<div class="innerContainer row" style="float: none;margin: 0 auto">
			<form action="/updateMember" method="post">
				<fieldset>
				<br><br>
				<div class="form-group row mb-4">
      				<label for="memberId" class="col-sm-2 col-form-label text-fff">ID</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<input type="text" class="form-control text-fff" id="memberId" name="memberId" value="${sessionScope.m.memberId }"
        				style="background-color: rgba(225,225,225,0.15);" readonly>
      				</div>
    			</div>
				<div class="form-group row mb-4">
      				<label for="memberPw" class="col-sm-2 col-form-label text-fff">PW</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<input type="password" class="form-control" id="memberPw" name="memberPw"value="${sessionScope.m.memberPw }" required>
      				</div>
    			</div>
				<div class="form-group row mb-4">
      				<label for="memberName" class="col-sm-2 col-form-label text-fff">NAME</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<input type="text" readonly class="form-control text-fff" id="memberName" name="memberName" value="${sessionScope.m.memberName }"
        				style="background-color: rgba(225,225,225,0.15);">
      				</div>
    			</div>
				<div class="form-group row mb-4">
      				<label for="phone" class="col-sm-2 col-form-label text-fff">PHONE</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<input type="text" class="form-control " id="phone" name="phone" value="${sessionScope.m.phone }" required>
      				</div>
    			</div>
				<div class="form-group row mb-4">
      				<label for="email" class="col-sm-2 col-form-label text-fff">EMAIL</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<input type="text" class="form-control" id="email" name="email" value="${sessionScope.m.email }" required>
      				</div>
    			</div>
				<div class="form-group row mb-4">
      				<label for="address" class="col-sm-2 col-form-label text-fff">ADDRESS</label>
      				<div class="col-sm-10" style="width: 650px;">
        				<select class="form-select" id="addrSelect" name="addrSelect">
						<option value="서울">서울</option>
						<option value="경기">경기</option>
						<option value="강원">강원</option>
						<option value="경북">경북</option>
						<option value="경남">경남</option>
						<option value="울산">울산</option>
						<option value="인천">인천</option>
						<option value="세종">세종</option>
						<option value="충북">충북</option>
						<option value="충남">충남</option>
						<option value="대구">대구</option>
						<option value="대전">대전</option>
						<option value="전남">전남</option>
						<option value="전북">전북</option>
						<option value="광주">광주</option>
						<option value="부산">부산</option>
						<option value="제주">제주</option>
					</select>
      				</div>
    			</div>
    			<div class="form-gorup text-center mb-4">
					<button type="submit" class="btn btn-outline-info">정보수정</button>
					<a href="/" class="btn btn-outline-warning">메인으로</a>
					<a href="javascript:history.go(-1)" class="btn btn-outline-secondary">뒤로가기</a>
				</div>
				</fieldset>
				<a id="deleteBtn" class="float-end text-secondary" style="font-size: large;cursor: pointer;">회원탈퇴</a>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		$("#deleteBtn").click(function(){
			var memNo = ${sessionScope.m.memberNo };
			if(confirm("정말로 진짜로 삭제하시겠습니까?")){
				location.href="/deleteMember?memberNo="+memNo;	
			}
		});
		$(function(){
			var addr = "${sessionScope.m.addr}";
			$("#addrSelect").find("option").each(function(index){
				if($(this).val()==addr){
					$("#addrSelect").val(addr).prop("selected",true);
				}
			});
		});
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>LOCATION</title>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa44a6fd16513565cb487ad393eb9473&libraries=services"></script>
	<style>
		ul{
			list-style-type: none;
		}
		.container>.title{
			text-align: center;
		}
		.searchArea{
			width: 100%;
			display:flex;
			align-items: center;
			justify-content: center;
		}
		.searchArea>li{
			margin-right: 10px;
		}
		.locationWrapper{
			width: 100%;
			overflow:hidden;
			display: flex;
		}
		.table-area{
			width:46%;
			height: 500px;
			overflow:auto;
			margin-right: 20px; 
			background-color: #343a40;
		}
		.table-area>ul{
			background-color: #343a40;
			margin: 0px;
		}
		.table-area li{
			padding: 20px;
			border-bottom: 1px solid #3e444a;
		}
		.table-area li:last-child{
			border-bottom: none;
		}
		.table-area li>p{
			margin: 0;
		}
		.table-area li:hover{
			background-color: #43494e;
			cursor: pointer;
		}
		.map-area{
			width: 50%;
			float: right;
		}
		.focus{
			background-color: #43494e;
		}
		table{
			margin-top: 30px;
		}
		table th{
			text-align: center;
		}
		.writeBtnWrapper{
			margin-top: 30px;
			text-align: center;
		}
		.modal{
			color:#111111;
		}
		.modal-body > div{
			margin-top:10px;
		}
		.modal-body > div:last-child{
			margin-bottom:10px;
		}
		#location{
			margin-top: 100px;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" id="location">
		<div class="title">
			<h1 style="color: #fff">LOCATION</h1>
			<p>KH ESCAPE에서 새로운 탈출을 경험하세요!</p>		
		</div>
		<div class="searchBox">
			<form action="/searchLoc" method="post">
				<ul class="searchArea">
					<li>지점찾기</li>
					<li>
						<select class="form-select areaInfo" name="areaInfo">
							<option value="0">시/도 선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="대전">대전</option>
							<option value="광주">광주</option>
							<option value="대구">대구</option>
							<option value="울산">울산</option>
							<option value="부산">부산</option>
							<option value="경기">경기</option>
							<option value="강원">강원</option>
							<option value="충청북도">충북</option>
							<option value="충청남도">충남</option>
							<option value="전라북도">전북</option>
							<option value="전라남도">전남</option>
							<option value="경상북도">경북</option>
							<option value="경상남도">경남</option>
							<option value="제주">제주</option>
						</select>				
					</li>
					<li>
						<input type="text" class="form-control" name="keyword1" value="${keyword }">
					</li>
					<li>
						<button type="submit" class="btn btn-primary searchBtn">검색</button>				
					</li>
				</ul>
			</form>
		</div>
		<div class="locationWrapper">
			<div class="table-area allLocation">
			  <ul style="padding: 0;">
			  	<c:forEach items="${list }" var="loc" varStatus="i">
					<li class="table-dark locationInfo" onclick="mapchange('${loc.locAddr }','${loc.locNo }');">
						<h5>${loc.locName }</h5>
						<p>${loc.locAddr }</p>
						<p>${loc.locPhone }</p>
					</li>			  			
				</c:forEach>
			  </ul>
			</div>
			<div class="map-area">
				<div id="map" style="width:100%;height:500px;"></div>
			</div>
		</div>
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1 }">
			<div class="writeBtnWrapper">
				<button class="btn btn-primary" data-toggle="modal" data-target=".insertLocation">지점추가</button>
				<button class="btn btn-primary" data-toggle="modal" data-target=".updateLocation">지점수정</button>
				<button class="btn btn-primary" data-toggle="modal" data-target=".deleteLocation">지점삭제</button>
			</div>
		</c:if>
		
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1 }">
			<div class="modal insertLocation">
		      <div class="modal-dialog" role="document">
		         <div class="modal-content">
		            <div class="modal-header">
		               <h5 class="modal-title">지점추가</h5>
		            </div>
		            <form action="/insertLocation" method="post" name="insertLocationFrm">
		               <div class="modal-body">
		                  <div class="form-group">
		                     <label for="locName">지점명</label>
		                     <input type="text" name="locName" id="locName" class="form-control" placeholder="지점명 입력">
		                  </div>
		                  <div class="form-group">
		                     <label for="locAddr">주소</label>
		                     <input type="text" name="locAddr" id="locAddr" class="form-control" placeholder="지점 주소 입력">
		                  </div>
		                  <div class="form-group">
		                     <label for="locPhone">전화번호</label>
		                     <input type="text" name="locPhone" id="locPhone" class="form-control" placeholder="지점 전화번호 입력">
		                  </div>
		               </div>
		               <div class="modal-footer">
		                  <button type="submit" class="btn btn-primary">지점추가</button>
		                  <button type="button" class="btn btn-secondary cls" data-dismiss="modal" onclick="initInput();">취소</button>            
		               </div>
		            </form>
		         </div>
		      </div>
		   </div>
		   <div class="modal updateLocation">
		      <div class="modal-dialog" role="document">
		         <div class="modal-content">
		            <div class="modal-header">
		               <h5 class="modal-title">지점수정</h5>
		            </div>
		               <div class="modal-body">
		                  <div class="form-group">
		                     <label for="locName">수정할 지점명</label>
		                     <select class="form-select" id="updateLocName" name="type">
		                     	<option value="0">수정할 지점명을 선택하세요.</option>
		                     	<c:forEach items="${list }" var="loc" varStatus="i">
		                     		<option value="${loc.locName }">${loc.locName }</option>
		                     	</c:forEach>
		                     </select>
		                  </div>
		                  <div class="form-group">
		                     <label for="locAddr">주소</label>
		                     <input type="text" name="updateLocAddr" id="updateLocAddr" class="form-control">
		                  </div>
		                  <div class="form-group">
		                     <label for="locPhone">전화번호</label>
		                     <input type="text" name="updateLocPhone" id="updateLocPhone" class="form-control">
		                  </div>
		               </div>
		               <div class="modal-footer">
		                  <button id="updateLocation" class="btn btn-primary">지점수정</button>
		                  <button type="button" class="btn btn-secondary cls updateCancel" data-dismiss="modal">취소</button>            
		               </div>
		         </div>
		      </div>
		   </div>
		   <div class="modal deleteLocation">
		      <div class="modal-dialog" role="document">
		         <div class="modal-content">
		            <div class="modal-header">
		               <h5 class="modal-title">지점삭제</h5>
		            </div>
		               <div class="modal-body">
		                  <div class="form-group">
		                     <label for="locName">삭제할 지점명</label>
		                     <select class="form-select" id="deleteLocName" name="type">
		                     	<option value="0">삭제할 지점명을 선택하세요.</option>
		                     	<c:forEach items="${list }" var="loc" varStatus="i">
		                     		<option value="${loc.locName }">${loc.locName }</option>
		                     	</c:forEach>
		                     </select>
		                  </div>
		               </div>
		               <div class="modal-footer">
		                  <button id="deleteLocation" class="btn btn-primary">지점삭제</button>
		                  <button type="button" class="btn btn-secondary cls" data-dismiss="modal" onclick="initInput();">취소</button>            
		               </div>
		         </div>
		      </div>
		   </div>
		   <script>
		      function initInput(){
		         $("[name=insertLocationFrm] input").val("");
				 $("#deleteLocName option:eq(0)").attr("selected","selected");		
		      }
   			</script>
		</c:if>
		
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	// 지점 수정
	$("#updateLocName").change(function(){
		if(this.value !==""){
			var updateLocName=$(this).find(":selected").val();
			var resultAddr=$(this).parent().next().children().last();
			var resultPhone=$(this).parent().next().next().children().last();
			$.ajax({
				url:"/searchLocInfo",
				data: {updateLocName:updateLocName},
				success:function(data){
					if(data!=null){
						resultAddr.empty();
						resultPhone.empty();
						
						resultAddr.val(data.locAddr);
						resultPhone.val(data.locPhone);
					}
				}
			});				
		}
	});
	$(".updateCancel").click(function(){
		$("#updateLocName option:eq(0)").attr("selected","selected");
		if($("#updateLocName option:selected").val()=="0"){
			$("#updateLocAddr").val("");
	        $("#updateLocPhone").val("");			
		}
	});
	$("#updateLocation").click(function(){
		var updateLocName=$("#updateLocName").find(":selected").val();
		var updateLocAddr=$("#updateLocAddr").val();
		var updateLocPhone=$("#updateLocPhone").val();
		$.ajax({
			url:"/updateLocation",
			data:{updateLocName:updateLocName, updateLocAddr:updateLocAddr, updateLocPhone:updateLocPhone},
			success:function(data){
				if(data>0){
					alert("지점 정보 수정 성공!");
					$(".updateLocation").modal("hide");
					location.href ="/location";
				}else{
					alert("지점 정보 수정 실패");
				}
			}
		});
	});
	$("#deleteLocation").click(function(){
		var deleteLocName=$("#deleteLocName").find(":selected").val();
		$.ajax({
			url:"/deleteLocation",
			data:{deleteLocName:deleteLocName},
			success:function(data){
				if(data>0){
					alert("지점 정보 삭제 성공!");
					$(".deleteLocation").modal("hide");
					location.href ="/location";
				}else{
					alert("지점 정보 삭제 실패");
				}
			}
		});
	});
	
	function mapchange(locAddr,locNo) {		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(locAddr, function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    		
	}

	$(".locationInfo").click(function(){
		$(".focus").eq(0).removeClass("focus");
		$(this).addClass("focus");
	});
	
	$(function(){
		var areaInfo = '${areaInfo}';
		
		if(areaInfo != ""){
			var optionList = $(".areaInfo>option");
			optionList.each(function(index,item){
				console.log($(item).val());
				if($(item).val() == areaInfo){
					$(item).prop("selected",true);
				}
			});
		}
		$(".locationInfo").eq(0).click();
	});
	</script>
	<script src="/js/main.js"></script>
</body>
</html>
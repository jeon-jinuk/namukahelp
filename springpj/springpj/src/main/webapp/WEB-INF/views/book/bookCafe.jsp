<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약하기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- <link rel="stylesheet" href="/css/index.css" /> -->
<link rel="stylesheet" href="/css/book.css" />
</head>
<body>
	<div class="header">
		<h1>BOOK CAFE</h1>
		<span></span>
		<p>스터디그룹에 가입해서 다양한 혜택을 받아보세요</p>
	</div>


	<div id="map" style="width: 80%; height: 350px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=72c882a3a5977aef4e85600fee55002a&libraries=services"></script>

	<script type="text/javascript">
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level : 0.5
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 카페 아이디를 넘기기 위한 변수
		let cafe_num = null
		
		function mapping(addr, name, id) {
			cafe_num = id
			// 주소로 좌표를 검색합니다
			geocoder
					.addressSearch(
							addr,
							function(result, status) {

								// 정상적으로 검색이 완료됐으면 
								if (status === kakao.maps.services.Status.OK) {

									var coords = new kakao.maps.LatLng(
											result[0].y, result[0].x);

									// 결과값으로 받은 위치를 마커로 표시합니다
									var marker = new kakao.maps.Marker({
										map : map,
										position : coords
									});

									// 인포윈도우로 장소에 대한 설명을 표시합니다
									var infowindow = new kakao.maps.InfoWindow(
											{
												content : '<div style="width:150px;text-align:center;padding:6px 0;">'
														+ name + '</div>'
											});
									infowindow.open(map, marker);

									// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
									map.setCenter(coords);
								}
							});
		}
	</script>

	<div class="container">
		<!-- 	<div id="locationField"> -->
		<!-- 		<input id="autocomplete" placeholder="Enter your address" type="text"> -->
		<!-- 	</div> -->

		<!-- 	<input class="field" id="lat" /> -->
		<!-- 	<input class="field" id="lng" /> -->



		<form action="/admin/book/bookCafe" method="get">
			<div class="container">
				<h2>예약 하기</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>지점번호</th>
							<th>지점명</th>
							<th>주소</th>
							<th>지역</th>
							<th>전화번호</th>
							<th>매장사진</th>
							<th>선택</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="cafe">
							<tr>
								<td>${cafe.id}</td>
								<td>${cafe.name}</td>
								<td>${cafe.addr}</td>
								<th>지역</th>
								<td>${cafe.phone}</td>
								<td><button type="button"
										onclick="Swal.fire({
									position: 'center',
									
								})">링크</button></td>
								<td><input type="radio" name="pick" id="radio"
									onclick="mapping('${cafe.addr}','${cafe.name}','${cafe.id }')"></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="form-group text-right">
					<button type="button" class="btn btn-secondary btn-sm" id="submit">전송</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" defer>

		$("#submit").click(function() {
			console.log(cafe_num)
			//location.href = "../book/test?cafeId="+cafe_num;
			location.href = "../book/bookSeat?id=" + cafe_num;
		}) // " "안에있는 주소의 의미??
		// location.href = 뒤에 제대로된 경로를 쓰면 거기다가 id값을 던지는??
	</script>
</body>
</html>
<%@ include file="../include/footer.jsp"%>
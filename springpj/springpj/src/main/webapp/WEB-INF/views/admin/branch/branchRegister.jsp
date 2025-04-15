<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/" />

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="css/index.css" rel="stylesheet" /> -->
<link href="/css/manager_page.css" rel="stylesheet" />
<link href="/css/index.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" />

<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="/css/seat.css" rel="stylesheet" />
</head>
<body>
	<header
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap shadow">
		<a class="logo-icon col-md-3 col-lg-2 me-0 px-3" href="../index">BOOK</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="w-100"></div>
		<div class="navbar-nav">
			<div class="nav-item text-nowrap">
				<a class="" href="/index"><i class="fas fa-home"></i></a> <a
					href="#"><i class="fas fa-bell"></i></a> <a href="/user/user">
					<i class="fas fa-user-circle"></i>
				</a>
			</div>
		</div>
	</header>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/admin/branch/branchList"> <span
								data-feather="home"></span> 지점 관리
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/admin/menu/menuList"> <span data-feather="file"></span>
								메뉴 관리
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/admin/mileage/mileageList"> <span data-feather="users"></span>
								마일리지 관리
						</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/admin/study/studyList"> <span data-feather="home"></span>
								스터디 관리
						</a></li>

						<li class="nav-item"><a class="nav-link"
							href="/admin/user/userList"> <span data-feather="users"></span>
								회원 관리
						</a></li>

					</ul>

					<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
						<span><a href="/logout">로그아웃</a></span> <a class="link-secondary"
							href="/logout" aria-label="Add a new report"> <i
							class="fas fa-angle-right"></i>
						</a>
					</h6>
					<!-- <ul class="nav flex-column mb-2"></ul> -->
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">지점 추가</h1>

				</div>

				<div class="container">

					<form action="/admin/branch/insert" method="post" id="branchForm"
						enctype="multipart/form-data">
						<div class="branch-form">
							<div class="left">
								<div class="form-group">
									<label for="name">지점사진:</label> <input type="file" name="file"
										id="file" /> <label for="file"> <i
										class="fas fa-image"></i>
									</label> <input type="text" readonly class="upload-name"></input>
								</div>
								<br>
								<div class="form-group">
									<label for="name">지점명:</label> <input type="text"
										class="form-control" id="name" placeholder="지점명" name="name">
								</div>

								<div class="form-group">
									<label for="sample6_postcode">우편번호</label>
									<div style="display: flex">
										<input type="text" class="form-control" id="sample6_postcode"
											placeholder="우편번호"> <input type="button"
											onclick="sample6_execDaumPostcode()" value="우편번호 찾기"
											class="btn btn-secondary"
											style="width: 10rem; margin-left: 0.5rem">
									</div>
								</div>
								<div class="form-group">
									<label for="sample6_address">주소</label> <input type="text"
										class="form-control" id="sample6_address" name="addr"
										placeholder="주소"> <input type="text"
										id="sample6_detailAddress" placeholder="상세주소"
										style="display: none;"> <input type="text"
										id="sample6_extraAddress" placeholder="참고항목"
										style="display: none;">
								</div>
								<div class="form-group">
									<label for="phone">전화번호:</label> <input type="text"
										class="form-control" id="phone" placeholder="전화번호"
										name="phone">
								</div>
								<div class="form-group">
									<label for="manager">지점장명:</label> <input type="text"
										class="form-control" id="manager" name="username"
										placeholder="지점장명">
								</div>
							</div>
							<div class="right">
								<h5>좌석 배치도</h5>
								<c:forEach begin="0" end="9" step="1" var="i">
									<c:forEach begin="0" end="9" step="1" var="j">
										<input type="checkbox" id="${i}${j}" name="seatNum"
											value="${i},${j}">
										<label for="${i}${j}"></label>
									</c:forEach>
									<br>
								</c:forEach>
							</div>
						</div>


						<button type="submit" class="submit" id="btn">추가</button>
					</form>
				</div>
			</main>
		</div>
	</div>


</body>
<script src="js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"
	integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"
	integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha"
	crossorigin="anonymous"></script>
<script src="js/dashboard.js"></script>
<script>
	function switchStatus(id) {
		console.log(id);
	}
	$("#file").on("change", function() {
		var fileName = $("#file").val();
		$(".upload-name").val(fileName);
	});
	
	$("#btn").click(()=>{	
		var alpa = Array.from({ length: 26 }, (v, i) => String.fromCharCode(i + 65));
		var array=[]
		var setNumList=[];
		 $("input:checkbox[name=seatNum]:checked").each((i,ival)=>{
			 setNumList.push(ival.defaultValue)
		 })
		 
		 for (var str of setNumList){
			var pos = str.split(',');
		 	array.push(pos[0]);
		 }
		 //"username":$("#manager").val(),"seats" :array    [pos[0], pos[1],alpa[pos[0]]+pos[1] ]
		const data={
				"name" : $("#name").val(),
				"addr" : $(".addr").val(),
				"phone": $("#phone").val(),
				"username":$("#manager").val(),
				"seats" :array
		}
	})
</script>
<script type="text/javascript" src="/js/address.js"></script>
</html>

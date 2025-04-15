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

</head>
<body>
	<%@include file="../include/mheader.jsp" %>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/user/myPage"> <span data-feather="home"></span> 내 정보
						</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="/user/myStudy"> <span data-feather="file"></span> 내 스터디
						</a></li>
						<c:forEach items="${myStudies}" var="study">						
							<li class="nav-item">
							<a class="nav-link" href="/user/myLeaderStudy/${study.id}">
								ㄴ ${study.title }</a>
							</li>
						</c:forEach>
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
					<h1 class="h2">내 스터디</h1>
					<div class="search">
						<input type="text" name="word" />
						<button>
							<i class="fas fa-search"></i>
						</button>
					</div>

				</div>
				<div class="table-responsive">
					<h3>스터디원 목록</h3>
					<table class="table table-striped table-sm join-waiting">
						<thead>
							<tr>
								<th scope="col">프로필</th>
								<th scope="col">닉네임</th>
								<th scope="col">이메일</th>
								<th scope="col">상태</th>
								<th scope="col">강퇴</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${members}" var="member">
								<tr>
									<td><img
										style="width: 50px; height: 50px; margin: auto;"
										src="${member.user.profile}"></td>
									<td>${member.user.nick }</td>
									<td>${member.user.email }</td>
									<td>${member.joinStatus}</td>
									<td><button onclick="declineMember('${member.id}')">강퇴</button></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>			
			</main>
		</div>
	</div>

</body>
<script src="js/bootstrap.bundle.min.js"></script>

<script src="js/dashboard.js"></script>

<script>
function declineMember(id){
if(confirm("정말 강퇴하시겠습니까?")){
	$.ajax({
		type:"post",
		url:"/user/joinerOut",
		data: {"id":id}
	})
	.done(()=>{
		alert("강퇴하였습니다..")
		location.href="/user/myLeaderStudy/${study.id}"
	})
}
}

</script>
</html>
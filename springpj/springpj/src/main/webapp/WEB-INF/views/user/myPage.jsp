<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/mheader.jsp" %>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-3">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/user/myPage"> <span
								data-feather="home"></span> 내 정보
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/myStudy">
								<span data-feather="file"></span> 내 스터디
						</a></li>
						<!-- <li class="nav-item">
                                <a class="nav-link" href="/user/myMileage">
                                    <span data-feather="users"></span>
                                    마일리지
                                </a>
                            </li>   -->
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
					<h1 class="h2">내 정보</h1>



				</div>

				<!--  ------------------------------------------------ -->
				<section class="mysection">
					<div class="myprofile">

						<button
							onclick="location.href='/user/myPageUpdate/${principal.user.username}'" id="myProfileUpdate">
							<i class="fa-solid fa-gear"></i>
						</button>
						<div class="user-profile">
							<img alt="profile" src="${user.profile}">
						</div>


						<h3>${user.nick }</h3>
						<div class="userInfo">
							<p>주소 : ${user.addr }</p>
							<p>이메일 : ${user.email }</p>
							<p>마일리지 : ${user.mileage }</p>
							<p>가입일 : ${user.regdate }</p>
						</div>
					</div>
				</section>
				<!--  ------------------------------------------------ -->


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
</script>
</html>
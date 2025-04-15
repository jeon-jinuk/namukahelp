<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/mheader.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<div class="container-fluid">
	<div class="row">
		<nav id="sidebarMenu"
			class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
			<div class="position-sticky pt-3">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/manager/currentStatus"> <span
							data-feather="home"></span> 매장 현황
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/manager/cafeMenu/cafeMenuList?username='${principal.user.username}'">
							<span data-feather="file"></span> 메뉴 상태 관리
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/manager/bookStatus"> <span data-feather="users"></span>
							예약 상태 관리
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
				<h1 class="h2">매장 현황</h1>
			</div>
			<div class="container graph">
				<div class="row my-3"></div>
				<div class="row my-2">
					<div class="col">
						<div class="card">
							<div class="card-body">
								<canvas id="myChart" height="100"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="counter">
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" pattern="MM월 dd일" var="today" />
				<div class="sold-out-counter today-box">
					<h4>${branch.name}</h4>
					<span>${today}</span>
				</div>
				<div class="today-book-counter">
					<h4>오늘 예약 수</h4>
					<span>${todayCount}</span> <b>개</b>
				</div>
				<div class="book-counter">
					<h4>주 예약 수</h4>
					<span>${weekCount }</span> <b>개</b>
				</div>
				<div class="sell-counter">
					<h4>오늘 매출</h4>
					<span>${todayTotal }</span> <b>원</b>
				</div>
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
$(document).ready(function(){
	var week = []
	var graphCount = []
	
	<c:forEach begin="0" end="6" var="i">
		week.push("${week[i]}")
		graphCount.push(${graphCount[i]})
	</c:forEach>
		
		console.log(week)
		console.log(graphCount)
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
		// 챠트 종류를 선택 
		type : 'line',
		// 챠트를 그릴 데이타 
		data : {
			labels : week,
			datasets : [ {
				label : 'My First dataset',
				backgroundColor : 'transparent',
				borderColor : 'red',
				data : graphCount
			} ]
		},
		// 옵션 
		options : {}
	});
})

</script>
</html>

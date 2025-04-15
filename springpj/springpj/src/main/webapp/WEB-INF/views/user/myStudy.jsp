<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/mheader.jsp"%>

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
						<li class="nav-item"><a class="nav-link"
							href="/user/myLeaderStudy/${study.id}"> ㄴ ${study.title }</a></li>
					</c:forEach>
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
				<h1 class="h2">내 스터디</h1>
				<div class="search">
					<input type="text" name="word" />
					<button>
						<i class="fas fa-search"></i>
					</button>
				</div>

			</div>
			<div class="table-responsive">
				<h3>가입 대기 중인 신청자</h3>
				<table class="table table-striped table-sm join-waiting">
					<thead>
						<tr>
							<th scope="col">프로필</th>
							<th scope="col">닉네임</th>
							<th scope="col">이메일</th>
							<th scope="col">상태</th>
							<th scope="col">가입 스터디</th>
							<th scope="col">수락/거절</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${waitingList}" var="waiter">
							<tr>
								<td><img style="width: 50px; height: 50px; margin: auto;"
									src="${waiter.user.profile}"></td>
								<td>${waiter.user.nick }</td>
								<td>${waiter.user.email }</td>
								<td>${waiter.joinStatus}</td>
								<td>${waiter.study.title }</td>
								<td><button onclick="applyAccept('${waiter.id}')">수락</button>/
									<button onclick="applyDecline('${waiter.id}')">거절</button></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</br> </br>
			<div class="table-responsive">
				<h3>가입한 스터디</h3>
				<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th scope="col">이름</th>
							<th scope="col">바로가기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${joinerList}" var="joiner">
							<tr>
								<td>${joiner.study.title }</td>
								<td>
									<button type="button" class="submit"
										onclick="location.href='/study/${joiner.study.id}'">바로가기</button>
								</td>
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
function applyAccept(id){
if(confirm("정말 수락하시겠습니까?")){
	$.ajax({
		type:"post",
		url:"/user/joinerAccept",
		data: {
			"id":id
			}
	})
	.done(()=>{
		alert("수락되었습니다.")
		location.href="/user/myStudy";
	})
}
}

function applyDecline(id){
if(confirm("정말 거절하시겠습니까?")){
	$.ajax({
		type:"post",
		url:"/user/joinerDecline",
		data: {"id":id}
	})
	.done(()=>{
		alert("거절되었습니다.")
		location.href="/user/myStudy"
	})
}
}
</script>
</html>
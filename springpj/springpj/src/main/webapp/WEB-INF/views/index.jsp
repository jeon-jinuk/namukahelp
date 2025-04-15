<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/home.css" />
<link rel="stylesheet" href="/css/index.css" />
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<div class="main">
		<div class="container">
			<div class="icon"></div>
			<div class="counter">
				<p class="counter-number studyCount"></p>
				<p class="counter-info">
					<b>현재 운영 중</b>인<br />스터디 그룹 수
				</p>
				<p class="counter-number userCount"></p>
				<p class="counter-info">
					<b>현재 참여 중</b>인<br />스터디 멤버
				</p>
			</div>

		</div>
	</div>
	<ul class="container bottom-menu">
		<li><a href="#cafe-system">카페시스템</a></li> |
		<li><a href="#popular-study">추천스터디그룹</a></li> |
		<li><a href="#cafe-interial">카페인테리어</a></li>
	</ul>
	<div id="cafe-system">
		<div class="container">
			<div class="book-system">
				<div class="title">
					<p>북시스템</p>
					<p>
						BOOK<br />SYSTEM
					</p>
				</div>
			</div>
		</div>
	</div>

	<div id="popular-study">
		<div class="container study-items">
			<c:forEach items="${studyList}" var="study">
				<fmt:formatDate value="${study.startDate}" pattern="yyyy.MM.dd"
					var="startdate" />
				<fmt:formatDate value="${study.endDate}" pattern="yyyy.MM.dd"
					var="enddate" />
				<div class="study-card" onclick="location.href='/study/detail?id=${study.id}'">
					<div class="study-profile">
						<img src="${study.profile }" />
						<div class="deco"></div>
					</div>
					<div class="study-content">
						<p class="study-status">진행중</p>
						<p class="study-title">${study.title}</p>
						<span class="study-info">${study.info }</span>
						<div class="study-detail">
							<ul>
								<li>기간 : ${startdate}-${enddate}</li>
								<li>제한인원수 : ${study.limitCount}</li>
							</ul>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="cafe-interial">
		<div class="interial-title">
			<h1>CAFE INTERIAL</h1>
		</div>
		<div id="carouselExampleIndicators"
			class="carousel slide interial-slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach begin="0" end="${branchCount-1}" var="i">
					<c:choose>
						<c:when test="i==0">
							<li data-target="#carouselExampleIndicators"
								data-slide-to="${i }" class="active"></li>
						</c:when>
						<c:otherwise>
							<li data-target="#carouselExampleIndicators"
								data-slide-to="${i }"></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ol>
			<div class="carousel-inner">
				<c:forEach items="${branchList }" var="branch">
					<div class="carousel-item">
						<img class="d-block w-100" src="${branch.profile}"
							alt="${branch.id} slide">
						<div class="carousel-caption d-none d-md-block branch-name">
							<h3>${branch.name }</h3>
						</div>
					</div>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
			<div id="cafe-interial-deco"></div>
		</div>
	</div>
	<%@ include file="include/footer.jsp"%>
	<script>
		$(document).ready(function() {
			var studyCount = ${studyCount};//
			var userCount = ${userCount};//

			countAni(studyCount, "studyCount")
			countAni(userCount, "userCount")

			$(".carousel-inner").children(":first").addClass("active")

		})
		function countAni(countTxt, type) {
			$({
				val : 0
			}).animate({
				val : countTxt
			}, {
				duration : 2000,
				step : function() {
					var num = numberWithCommas(Math.floor(this.val));
					$("." + type).text(num);
				},
				complete : function() {
					var num = numberWithCommas(Math.floor(this.val));
					$("." + type).text(num);
				},
			});
		}

		function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
	</script>
</body>
</html>
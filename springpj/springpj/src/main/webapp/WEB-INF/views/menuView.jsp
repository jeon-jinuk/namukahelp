<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<meta charset="UTF-8">
<title>메뉴 보기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" />
<link rel="stylesheet" href="/css/index.css" />
<link rel="stylesheet" href="/css/menu.css" />
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<div class="header">
		<h1>OUR MENU</h1>
		<span></span>
		<p>저희 카페에 오신 것을 환영합니다</p>
	</div>
	<div class="top"></div>
	<div class="container article">
		<div class="category">
			<ul>
				<li><a href="/menuView"> <label for="total">전체</label></a></li>
				<li><a href="/menuView?type=COFFEE"> <label for="coffee">커피</label></a>
				</li>
				<li><a href="/menuView?type=LATTE"> <label for="latte">라떼</label></a>
				</li>
				<li><a href="/menuView?type=TEA"> <label for="tea">티</label></a>
				</li>
				<li><a href="/menuView?type=ADE"> <label for="ade">에이드</label></a>
				</li>
				<li><a href="/menuView?type=JUICE"> <label for="juice">주스</label></a>
				</li>
				<li><a href="/menuView?type=SMOOTHIE"> <label
						for="smoothie">스무디</label></a></li>
				<li><a href="/menuView?type=DESSERT"> <label for="dessert">디저트</label></a>
				</li>
				<li><a href="/menuView?type=ETC"> <label for="etc">기타</label></a>
				</li>
			</ul>
		</div>

		<article>
			<div class="menu-list">
				<c:forEach items="${list}" var="menu">

					<div class="menu-item">
						<div class="menutype">${menu.menuType}</div>
						<div class="menu-profile">
							<img  src="${menu.profile}" width="50px"
								height="10%">
						</div>
						<p class="menu-title">${menu.name}</p>
						<p class="menu-price">${menu.price}</p>
						<p class="menu-info">${menu.intro}</p>
					</div>

				</c:forEach>
			</div>
		</article>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>

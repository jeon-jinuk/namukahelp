<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/book.css" rel="stylesheet" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="header">
		<h1>BOOK CAFE</h1>
		<span></span>
		<p>스터디그룹에 가입해서 다양한 혜택을 받아보세요</p>
	</div>
	<div class="container">
		<div class="confirm-form"></div>
		<h2>주문이 확인되었습니다</h2>
		<table class="table">
			<tr><th colspan="3">예 약 내 역</th></tr>
			<c:forEach items="${bookMenuList}" var="menu">
				<tr>
					<td></td>
					<td>${menu.cafeMenu.menu.name}</td>
					<td><span>${menu.count}</span>
					<span>${menu.totalPrice }</span></td>
				</tr>
			</c:forEach>
			<tr>
				<th>결 제 금 액</th>
				<td id="total" >${book.total}</td><td></td>
			</tr>
			<tr>
				<th>남 은 마 일</th>
				<td id="mymileage">${book.booker.mileage }</td><td></td>
			</tr>
			<tr>
				<th>좌 석 배 치</th>
				<td><c:forEach items="${bookSeatList}" var="seat">
						<span>${seat.seat.name}</span>
					</c:forEach></td><td></td>
			</tr>

			<tr>
				<th>예 약 카 페</th>
				<td>${book.branch.name}</td><td></td>
			</tr>
		</table>
		<button type="button" id="confirmBtn" onclick="localhost.href='/index'">확인</button>
	</div>
	<script>

	$("#useMileage").on('input',()=>{
		const use =$("#useMileage")
		const my = $("#mymileage")
		const tot = $("#total")
		
		var usemile =Number(use.val())
		var mymile = ${principal.user.mileage }
		var total = ${book.total}
		
		if(mymile-usemile>0){
			my.html(mymile-usemile)
		}else{
			use.val(mymile)
			my.html(0)
		}
		
		if(total-usemile>0){			
			tot.html(total-usemile)
		}else{
			tot.html(0)
			use.html(total-usemile)
		}
	})
</script>
<%@include file="../include/footer.jsp"%>
</body>
</html>
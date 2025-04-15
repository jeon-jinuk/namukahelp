<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/book.css" rel="stylesheet" />
</head>
<body>
	<div class="header">
		<h1>BOOK CAFE</h1>
		<span></span>
		<p>스터디그룹에 가입해서 다양한 혜택을 받아보세요</p>
	</div>
	<div class="container" id="container">
		<form action="/book/bookConfirm" method="post" id="selectMenuForm">
			<input type="hidden" name="studyId" value="${studyId}"> <input
				type="hidden" name="cafeId" value="${cafeId}"> <input
				type="hidden" name="bookdate" value="${dateTime}">
			<c:forEach items="${seatList}" var="seat">
				<input type="hidden" name="seat" value="${seat}">
			</c:forEach>
			<%-- <div class="menuForm">
				<div class="container selectMenu">
					<div class="row cafeMenu">
						<c:forEach items="${cafeMenus}" var="menu">
							<div class="card" style="width: 12rem;"
								onclick="addMenu('${menu.id}','${menu.menu.name}','${menu.menu.price}')">
								<img src="${menu.menu.profile}" class="card-img-top menu_img">
								<div class="card-body">
									<h5 class="card-title">${menu.menu.name}</h5>
									<p class="card-text">${menu.menu.price}</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="orderForm">
					<div id="cafeOrderList"></div>
					<h3>결제 방식</h3>
					<div class="use-mile">
						<p>
							<b>사용가능 마일리지</b>
						</p>
						<p id="mymileage">${principal.user.mileage }</p>
						<p>
							<input type="number" id="useMileage" name="useMile" value="0">
						</p>
					</div>
					<div class="totalForm">
						<input type="text" value="0" id="totalPrice" name="totalPrice">
						<input type="hidden" value="0" id="totalHidden"> <input
							type="submit" id="submitBtn" value="결제">
					</div>
				</div>
			</div> --%>
			<section class="menu-form">
				<div class="selectMenu">
					<div class="row cafeMenu">
						<c:forEach items="${cafeMenus}" var="menu">
							<div class="card" style="width: 12rem;"
								onclick="addMenu('${menu.id}','${menu.menu.name}','${menu.menu.price}')">
								<img src="${menu.menu.profile}" class="card-img-top menu_img">
								<div class="card-body">
									<h5 class="card-title">${menu.menu.name}</h5>
									<p class="card-text">${menu.menu.price}</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
			<section class="order-form">
				<div id="cafeOrderList">
					<h5>주문/결제</h5>
				</div>
				<div class="use-mile">
					<h5>할인 및 마일리지</h5>
					<div class="useable-mile-form">
						<div class="mymile-form">
							<p>보유</p>
							<p id="mymileage">${principal.user.mileage }</p>
						</div>
						<div class="usemile-form">
							<p>사용</p>
							<p>
								<input type="number" name="useMile" id="useMileage" value="0" />
							</p>
						</div>
					</div>
				</div>
				<div class="get-mile-form">
					<h5>마일리지 혜택</h5>
					<div class="get-mile">
						<div class="get-mile-user">
							<p>
								<span>예약 적립</span><input type="text" readonly class="mile"
									id="userNomalMile" value="0" name="userMile">
							</p>
							<!-- <p>
								<span>ㄴ기본적립</span><span class="mile" id="userNomalMile">0마일</span>
							</p> -->
						</div>
						<div class="get-mile-study">
							<p>
								<span>스터디 적립</span><input id="totStudyMile" type="text" readonly class="mile"
									value="0" name="studyMile"/>
							</p>
							<p>
								<span>ㄴ기본적립</span><input type="text" readonly class="mile"
									id="studyNomalMile" value="0">
							</p>
							<p>
								<span>ㄴ좌석수</span><input type="text" readonly class="mile"
									id="studyMemberMile" value="${fn:length(seatList)}" />
							</p>
						</div>
					</div>
				</div>
				<div class="total-form">
					<h5>최종 결제 금액</h5>
					<input type="text" value="0" id="totalPrice" name="totalPrice" />
					<input type="hidden" value="0" id="totalHidden" />
					<h3>원</h3>
				</div>
				<div class="charge-select">
					<h5>결제 수단</h5>
					<div class="charge-list">
						<div class="input-radio">
							<input id="account" type="radio" name="charge" value="account"
								checked class="form-check-input" /> <label for="account">계좌결제</label>
						</div>
						<div class="input-radio">
							<input id="card" type="radio" name="charge" value="card"
								class="form-check-input" /> <label for="card">카드결제</label>
						</div>
						<div class="input-radio">
							<input type="radio" name="charge" value="phone"
								class="form-check-input" /> <label for="phone">휴대폰 결제</label>
						</div>
					</div>
				</div>
				<input type="submit" value="결제" id="submitBtn" />
			</section>

		</form>
	</div>
	<script>
	
	
	
	// 마일리지 계산 함수
	var mileCalcul = () => {
		const use =$("#useMileage")
		const my = $("#mymileage")
		const tot = $("#totalPrice")
		
		var usemile =Number(use.val())
		var mymile = ${principal.user.mileage }
		var total = Number($("#totalHidden").val())
		
		if(mymile-usemile>0){
			my.html(mymile-usemile)
		}else{
			use.val(mymile)
			my.html(0)
		}
		
		if(total-usemile>=0){			//총 결제 금액이 입력된 마일리지 보다 클 때
			tot.val(total-usemile)
		}
		else if(total-usemile>=0 && mymile>=usemile){ //총 결제금액이 입력된 마일리지보다 높고, 입력된 마일리지가 가지고 있는 마일리지보다 클때
			use.val(usemile)
			my.html(0)
			tot.val(total-mymile)
		}
		else if(total>=mymile && total-usemile<0 && mymile>=usemile){ //총 결제금액이 입력된 마일리지보다 작고, 입력된 마일리지가 가지고 있는 마일리지보다 작을때 
			my.html(mymile-usemile)
			tot.val(total-mymile)
			//my.val(mymile-usemile)
		}else if(total>=mymile && total-usemile<0 && mymile<usemile){ //총 결제 금액이 입력된 마일리지 보다 작고, 입력된 마일리지가 가지고 있는 마일리지보다 클때
			use.val(mymile)
			my.html(0)
			tot.val(total-mymile)
		}
		else if(total<mymile && total-usemile<0 && mymile>=usemile){ //총 결제금액이 입력된 마일리지보다 작고, 입력된 마일리지가 가지고 있는 마일리지보다 작을때 
			use.val(total)
			my.html(mymile-total)
			tot.val(0)
			//my.val(mymile-usemile)
		}else if(total<mymile && total-usemile<0 && mymile<usemile){ //총 결제 금액이 입력된 마일리지 보다 작고, 입력된 마일리지가 가지고 있는 마일리지보다 클때
			use.val(total)
			my.html(mymile-total)
			tot.val(0)
		}
	}
	
	
	
	
		function addMenu(id,name,price){
			console.log($("."+id).length)
			if($("."+id).length==0){
			$("#cafeOrderList").append(
					/* `<div id="cafeOrder" class=`+id+`>
					<input type="text" readonly id="menuName"  value="`+name+`">
					<input type="text" readonly id="menuCount" value="1" name="count">
					<input type="hidden" readonly id="menuPrice" value="`+price+`" />
					<input type="text" readonly id="menuTotal" value="`+price+`" name="menuTotal"/>
					<input type="hidden" id="cafeMenuId" name="cafeMenuId" value="`+id+`">
					<div class="update-menu-btn"><a href="javascript:delMenu(`+id+`)" id="delMenu"><i class="fa-solid fa-xmark"></i></a>
					<a href="javascript:addCountMenu(`+id+`)" id="addCountMenu"><i class="fa-solid fa-plus"></i></a>
					<a href="javascript:subCountMenu(`+id+`)" id="subCountMenu"><i class="fa-solid fa-minus"></i></a></div>
				</div>` */
					`<div id="cafeOrder" class="`+id+`">
                    <input
                        type="text"
                        readonly
                        id="menuName"
                        value="`+name+`"
                    />
                    <input
                        type="text"
                        readonly
                        id="menuCount"
                        value="1"
                        name="count"
                    />
                    <input
                        type="hidden"
                        readonly
                        id="menuPrice"
                        value="`+price+`"
                    />
                    <input
                        type="text"
                        readonly
                        id="menuTotal"
                        value="`+price+`"
                        name="menuTotal"
                    />
                    <input
                        type="hidden"
                        id="cafeMenuId"
                        name="cafeMenuId"
                        value="`+id+`"
                    />
                    <div class="update-menu-btn">
                        <a
                            href="javascript:delMenu(`+id+`)"
                            id="delMenu"
                            ><i class="fa-solid fa-xmark"></i
                        ></a>
                        <a
                            href="javascript:addCountMenu(`+id+`)"
                            id="addCountMenu"
                            ><i class="fa-solid fa-plus"></i
                        ></a>
                        <a
                            href="javascript:subCountMenu(`+id+`)"
                            id="subCountMenu"
                            ><i class="fa-solid fa-minus"></i
                        ></a>
                    </div>
                </div>`
				)
				
			var total = $("#totalPrice")
			var totalHidden = $("#totalHidden")
			total.val(Number(totalHidden.val())+Number(price))
			totalHidden.val(total.val()).trigger('change')
			
			mileCalcul()
			}
			
			
		}
		
		function addCountMenu(id){
			var total = $("#totalPrice")
			var totalHidden = $("#totalHidden")
			var count = $("."+id).children('input:eq(1)')
			var price = $("."+id).children('input:eq(2)')
			var totprice = $("."+id).children('input:eq(3)')
			
			count.val(Number(count.val())+1)
			totprice.val(Number(count.val())*Number(price.val()))
			total.val(Number(totalHidden.val())+Number(price.val()))
			
			totalHidden.val(total.val()).trigger('change')
			mileCalcul()
		}
		
		function subCountMenu(id){
			var total = $("#totalPrice")
			var totalHidden= $("#totalHidden")
			var count = $("."+id).children('input:eq(1)')
			var price = $("."+id).children('input:eq(2)')
			var totprice = $("."+id).children('input:eq(3)')
			total.val(Number(totalHidden.val())-Number(count.val()=='1'?'0':price.val()))
			
			count.val(Number(count.val())-1<1?1:Number(count.val())-1)
			totprice.val(Number(count.val())*Number(price.val()))
			
			totalHidden.val(total.val()).trigger('change')
			mileCalcul()
		}
		function delMenu(id){
			var total = $("#totalPrice")
			var totalHidden = $("#totalHidden")
			var totprice = $("."+id).children('input:eq(3)')
			total.val(Number(totalHidden.val())-Number(totprice.val()))
			$("."+id).remove()
			
			totalHidden.val(total.val()).trigger('change')
			mileCalcul()
		}
		
		
		$("#totalHidden").change(()=>{
			const total = $("#totalHidden").val()
			const usermile = $("#userNomalMile")
			const studymile = $("#studyNomalMile")
			const totStudyMile = $("#totStudyMile") 
			const memberCount = Number($("#studyMemberMile").val())
			const getStudyMile = Number(total)*0.1
			const getUserMile = Number(total)*0.05
			
			usermile.val(getUserMile)
			studymile.val(getStudyMile)
			totStudyMile.val(getStudyMile*memberCount)
			
			
		})

		
		$("#useMileage").on('input',mileCalcul)
	</script>
</body>
</html>
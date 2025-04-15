<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" />
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/css/index.css" />
<link href="/css/manager_page.css" rel="stylesheet" />
<link href="/css/dashboard.css" rel="stylesheet" />
</head>
<body>
	<header
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap shadow">
		<a class="logo-icon col-md-3 col-lg-2 me-0 px-3" href="/index">BOOK</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="w-100"></div>
		<div class="navbar-nav">
			<div class="nav-item text-nowrap">
				<a class="" href="/index"> <i class="fas fa-home"></i></a> <a
					href="javascript:popupAlert()" class="position-relative" id="alertIcon"> <i
					class="fas fa-bell"></i>
				</a> <a href="/user/user"> <i class="fas fa-user-circle"></i>
				</a>
			</div>
		</div>
	</header>
	<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"/user/getAlert/"+${principal.user.id}
	})
	.done((resp)=>{
		console.log(resp)
		if(resp!=''){
			$("#alertIcon").append(`
				<span
					class="position-absolute top-0 start-0 translate-middle p-1 bg-danger rounded-circle">
						<span class="visually-hidden">New alerts</span>
				</span>		
			`)
		}
	})
})
		
function popupAlert(){
	$.ajax({
		type:"get",
		url:"/user/getAlert/"+${principal.user.id}
	})
	.done((resp)=>{
		var str=''
		$.each(resp, (key,val)=>{
			str+=`
			<tr class="msg">
				<td>`+val.message+`</td>
				<td><button type="button" onclick="delMessage(`+val.id+`)" id="delMessage">
					<i class="fa-regular fa-x"></i></td>
			</tr>
			`
		})
		Swal.fire({
			position: 'center',
	 		html:`
	 			<div class="alert-title">
					<h3>내 알람 목록</h3>
		 			<div class="alert-count">`+resp.length+`</div>
				</div>
	 			<table class="table table-sm">
	 			`+str+`
	 			</table>
	 			`
		}).then((value)=>{
			
			})
		})
}


function delMessage(id){
	$.ajax({
		type:"get",
		url:"/user/delAlert/"+id
	})
	.done((resp)=>{
		popupAlert();
	})
}
</script>
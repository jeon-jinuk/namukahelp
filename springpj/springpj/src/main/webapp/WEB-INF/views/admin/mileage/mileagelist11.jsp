<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마일리지</title>
</head>
<body>
	<div class="container">

		<h2 style="margin-bottom: 1em">포인트 목록</h2>

		<input type="text" placeholder="검색어 입력">
		<button type="submit" class="btn_mileage_select">검색</button>
		<table id="mileage-list" class="mileage">
			<colgroup>
				<col width="15%" />
				<col width="*" />
				<col width="15%" />
				<col width="20%" />
			</colgroup>

			<tr>
				<th scope="col">회원번호</th>
				<th scope="col">회원명</th>
				<th scope="col">회원id</th>
				<th scope="col">포인트 보유</th>
				<th scope="col">포인트 지급</th>
				<th scope="col">포인트 회수</th>
			</tr>

			<tbody>
				<c:forEach items="${userlist}" var="user">
					<tr>
						<td class="id">${user.id }</td>
						<td class="username">${user.username }</td>
						<td class="nick">${user.nick }</td>
						<td class="mileage">${user.mileage }</td>
						<td>
							<input type="text" class="${user.username}" id="mileage_insert"	name="mileage_insert">
							<input type="button" class="btn_mileage_insert"
							onclick="addMile('${user.username}')" value="지급"></td>
						
						<td>
							<input type="text" id="mileage_delete" name="mileage_delete">
							<input type="button" class="btn_mileage_delete" 
						onclick="delMile('${user.username}')" value="회수"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/admin/userlist" class="btn"></a>
	</div>

	<script> 
		function addMile(username){
		    if(!jQuery.isNumeric($("#mileage_insert").val())){
		            alert("숫자가 아닙니다.")
		            return
		        }
		        $.ajax({
		            type: "post",
		            url: "/admin/addmile",
		            data: {
						"mile": $("#mileage_insert").val(),
						"username" : username
					}
		        }).done(function(){
		           	alert("포인트가 추가되었습니다.")
		           	location.href="/admin/mileagelist"
		    }); 
		}	
		
		function delMile(username){
		    if(!jQuery.isNumeric($("#mileage_delete").val())){
		            alert("숫자가 아닙니다.")
		            return
		        }
		        $.ajax({
		            type: "post",
		            url: "/admin/delmile",
		            data: {
						"mile": $("#mileage_delete").val(),
						"username" : username
					}
		        }).done(function(){
		           	alert("포인트가 삭제되었습니다.")
		           	location.href="/admin/mileagelist"
		    }); 
		}	
	</script>
</body>
</html>
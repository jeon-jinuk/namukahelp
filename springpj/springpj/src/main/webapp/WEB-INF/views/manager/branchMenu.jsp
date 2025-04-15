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
<title>지점별 발주</title>
</head>
<body>
	<h2> 지점별 발주</h2>
	<!--  -->
	<form  action="/manager/branchMenuSelect" method="get" id="fmt" enctype="multipart/form-data">
		<div>
		<input type="hidden" name="branchId" value="${branchId}">
			<c:forEach items="${Type}" var="type">
				<h3>${type}</h3>
				<c:forEach items="${list}" var="menu">
					<c:if test="${menu.menuType == type }">
						
						<div>
							<div><img class="menu_img" src="${menu.menu.profile}" width="50px" height="10%"></div>
							<div>${menu.menu.name}</div>
							<div>${menu.menu.intro}</div>
							<div>${menu.menu.price}</div>
							<input type="checkbox" name="ch" value="${menu.id }"></input>
						</div>
					</c:if>
				</c:forEach>
			</c:forEach>
		</div>
	<button type="button" class="btn btn-primary btn-sm" id="balju" >발주</button>
	<button type="reset" class="btn btn-primary btn-sm" >초기화</button>

	</form>

</body>

<script type="text/javascript">

let ckd = new Array();
function test(num){
	cafe_num = num
}

	$("#balju").click(function() {
/* 		if($("#name").val()==""){
			alert("메뉴명을 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#intro").val()==""){
			alert("설명 입력하세요");
			$("#intro").focus();
			return false;
		}
		if($("#file").val()==""){
			alert("이미지를 첨부하세요");
			$("#file").focus();
			return false;
		}
		if($("#price").val()==""){
			alert("가격을 입력하세요");
			$("#price").focus();
			return false;
		}
		if(!jQuery.isNumeric($("#price").val())) {
			alert("가격을 숫자로 입력하세요.");
			$("#price").focus();
			$('#price').val('');
            return false;
		}
		 */
		$("#fmt").submit();
	});
</script>

</html>
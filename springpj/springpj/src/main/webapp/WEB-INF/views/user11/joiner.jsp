<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<h3>신청대기중인 멤버</h3>
	<table>
		<tr>
			<th>프로필</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>이메일</th>
			<th>상태</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach items="${applyerList }" var="applyer">
			<tr>
				<td><img src="${applyer.user.profile}"></td>
				<td>${applyer.user.username}</td>
				<td>${applyer.user.nick}</td>
				<td>${applyer.user.email}</td>
				<td>${applyer.joinStatus}</td>
				<td><button onclick="applyAccept('${applyer.id}')">수락</button></td>
				<td><button onclick="applyDecline('${applyer.id}')">거절</button></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>

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
		})
	}
}

function applyDecline(id){
	if(confirm("정말 거절하시겠습니까?")){
		$.ajax({
			type:"post",
			url:"/user/joinerDecline",
			data: "id":id
		})
		.done(()=>{
			alert("거절되었습니다.")
		})
	}
}
</script>
</body>
</html>
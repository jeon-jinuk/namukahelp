<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>
<body>
	<table class="user_update">
		<colgroup>
			<col width="15%" />
			<col width="*" />
			<col width="15%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">고유번호</th>
				<th scope="col">아이디</th>
				<th scope="col">닉네임</th>
				<th scope="col">주소</th>
				<th scope="col">이메일</th>
				<th scope="col">마일리지</th>
				<th scope="col">권한</th>
				<th scope="col">가입일</th>
			</tr>
		</thead>
		<tr>
			<td class="id">${user.id }</td>
			<td class="username">${user.username }</td>
			<td class="nick">${user.nick }</td>
			<td class="addr">${user.addr }</td>
			<td class="email">${user.email }</td>
			<td class="mileage">${user.mileage }</td>
			<td class="role">${user.role }</td>
			<td class="regdate">${user.regdate }</td>
		</tr>
		<td colspan="9">조회된 결과가 없습니다</td>
		</tr>
		</tbody>
	</table>
	<form action="/user/userupdate" method="post" id="frm">
		<br><input type="hidden" name="username"	value="${user.username}"> 
			<input type="text" id="nick"name="nick" placeholder="새로운 닉네임정보를 입력하세요" value="${user.nick}"></br>
		<br><input type="text" id="addr" name="addr"	placeholder="새로운 주소를 입력하세요" value="${user.addr}"></br>
		<br><input type="text" id="email" name="email" placeholder="새로운 이메일 정보를 입력하세요"value="${user.email}"></br> 
		<br> 
			<input type="submit" value="나의 정보 수정" onclick="userupdate()" id="userupdate" name="userupdate"> </br>
		<br>
			<button type="button" onclick="userdeletebtn()">나의 정보 삭제</button>
		</br>
	</form>
	<script>
		function userupdate() {
			if ($("#nick").val() == '') {
				alert("닉네임을 확인해주세요")
			}
			else if ($("#addr").val() == '') {
				alert("주소을 확인해주세요")
			}
			else ($("#email").val() == '') {
				alert("이메일을 확인해주세요")
			}
			$("#frm").submit();
		}
		
		function userdeletebtn(){
			console.log("test")
			$.ajax({
				type:"post",
				url:"/user/userdelete",
				data:{
					"username" : "${user.username}"
				}
			})
			.done(function(res){
				if(res=="success"){
					alert("회원삭제가 완료되었습니다");
					location.href="/logout";
				}
			})
		}
	</script>

</body>
</html>
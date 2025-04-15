<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입하세요</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>
<body>

<div class="container">
	<h2>회원가입</h2>
	<form action="#" method="post">
		<div class="row">
			<div class="col">
				<label for="username">아이디:</label> <input type="text" class="form-control"
					id="username" placeholder="Enter username" name="username">
			</div>
			<div class="col align-self-end">
				<button type="button" class="btn btn-primary" id="btnIdCheck2">중복확인</button>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="nick">닉네임:</label> <input type="text" class="form-control"
					id="nick" placeholder="Enter nick" name="nick">
			</div>
			<div class="col align-self-end">
				<button type="button" class="btn btn-primary" id="btnIdCheck">중복확인</button>
			</div>
		</div>
		
		<div class="form-group">
			<label for="pass">비밀번호 :</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password">
		</div>
		<div class="form-group">
			<label for="pass_check">비밀번호 확인 :</label> <input type="password"
				class="form-control" id="pass_check"
				placeholder="Enter password_check" name="pass_check">
		</div>
		<div class="form-group">
			<label for="email">이메일 :</label> <input type="text" class="form-control"
				id="email" placeholder="Enter email" name="email">
		</div>
		<div class="form-group">
			<label for="addr">주소 :</label> <input type="text" class="form-control"
				id="addr" placeholder="Enter addr" name="addr">
		</div>
		
		<div class="form-group">
			<label for="role">권한:</label> 
			<select class="form-control" id="role" name="role">
				<option value="USER">USER(기본)</option>
				<option value="ADMIN">ADMIN</option>
				<option value="MANAGER">MANAGER</option>
			</select>
		</div>
		
		<button type="button" class="btn btn-primary btn-sm" id="btnJoin">회원가입</button>
	</form>
</div>

<script type="text/javascript" src="../js/member.js"></script>


</body>
</html>
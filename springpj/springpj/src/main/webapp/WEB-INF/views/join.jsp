<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입하세요</title>
</head>
<body>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
        />
        <link rel="stylesheet" href="css/index.css" />
        <link rel="stylesheet" href="css/join.css" />
    </head>
    <body>
        <nav>
            <div class="logo"></div>
        </nav>
			<section class="joinform">
				<form action="/joinProc" method="post">
				<h3 onclick="location.href='/index'">BOOK</h3>
				<p>하나의 아이디로 북 서비스를 즐겨보세요</p>
				<div class="input-boxes">
					<div class="input-box">
						<input type="text" name="username" id="username" placeholder="아이디" />
						<button type="button" id="btnIdCheck">중복확인</button>
					</div>
					<div class="input-box">
						<input type="text" name="nick" id="nick" placeholder="닉네임" />
					</div>
			
					<input type="password" name="password" id="password"
						placeholder="비밀번호" />
					<div class="input-box">
						<input type="password" name="pass_check" id="pass_check"
							placeholder="비밀번호 확인" /> <label for="pass_check">
							<i class="fa-solid fa-circle-check"></i>
						</label>
					</div>
					<div class="input-box">
						<input type="text" name="email1" id="email1" placeholder="이메일" />@
						<select name="email2" id="email2">
							<option value="@naver.com">naver.com</option>
							<option value="@google.com">google.com</option>
							<option value="@daum.net">daum.net</option>
						</select>
					</div>
					<div class="input-box">
						<input type="text" id="sample6_postcode" placeholder="우편번호">
						<input type="button" onclick="sample6_execDaumPostcode()"
							value="우편번호 찾기">
					</div>
						<input type="text" id="sample6_address" placeholder="주소"> <input
							type="text" id="sample6_detailAddress" placeholder="상세주소">
						<input type="text" id="sample6_extraAddress" placeholder="참고항목">
					</div>
					<div class="input-box">
						<select class="form-control role" id="role" name="role">
							<option value="ROLE_USER" selected>사용자</option>
							<option value="ROLE_MANAGER">매니저</option>
						</select>
					</div>
					<button type="button" value="회원가입" id="btnJoin">회원가입</button>
					<button type="reset">초기화</button>
				</form>
	</section>
</body>
	<script type="text/javascript" src="../js/member.js"></script>
    <script type="text/javascript" src="../js/address.js"></script>

	

</html>
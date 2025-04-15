<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<!DOCTYPE html>

<html lang="en">
<head>
	<script>
		function logo() {
			location.href = '/index'
		}
	</script>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Document</title>
	<link rel="stylesheet" href="css/index.css" />
	<link rel="stylesheet" href="css/login.css" />
</head>
<body>
        <nav>
            <div class="logo"></div>
        </nav>
        <section class="loginform">
            <form action="/loginProc" method="post">
                <h3 onclick="location.href = '/index'">BOOK</h3>
                <p>하나의 아이디로 북 서비스를 즐겨보세요</p>
                <input
                    type="text"
                    name="username"
                    id="username"
                    placeholder="아이디"
                />
                <input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="비밀번호"
                />
                <input type="submit" value="로그인" id="btnLongin"/>
                <button type="button" class="btn btn-secondary btn-sm" id="join">회원가입</button>
            </form>
        </section>
    </body>
    
<script type="text/javascript" src="/js/member.js"></script>
<script type="text/javascript">
	$("#join").click(function() {

		location.href = "/join"

	});

</script>
    
</html>



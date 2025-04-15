<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="principal" />
		</sec:authorize>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
        />
        <link rel="stylesheet" href="/css/index.css" />
        <link rel="stylesheet" href="/css/manager.css" />
    </head>
    <body>
        <article>
            <h1>BOOK</h1>
            <p>안녕하세요, ${principal.user.username}매니저님!</p> <!-- 이름 못불러옴 -->
            <a href="currentStatus">지점 현황 바로가기</a>
            <a href="cafeMenu/cafeMenuList?username=${principal.user.username}">메뉴 관리 바로가기</a>
            <a href="bookStatus">예약 관리 바로가기</a>
            <a href="/index">홈 바로가기</a>
        </article>
    </body>
</html>
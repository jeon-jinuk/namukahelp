<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>유저 목록</h2>
	<table class="user_page">
	<colgroup>
	                <col width="15%"/>
	                <col width="*"/>
	                <col width="15%"/>
	                <col width="20%"/>
	            </colgroup>
	            <thead>
	            <tr>
		            <th scope="col"> 고유번호</th>
		            <th scope="col"> 아이디</th>
		            <th scope="col"> 닉네임</th>
		            <th scope="col"> 주소</th>
		            <th scope="col"> 이메일</th>
		            <th scope="col"> 마일리지</th>
		            <th scope="col"> 권한</th>
		            <th scope="col"> 가입일</th>
	            </tr>
	            </thead>
	            <tr>
	                <th class="id"> ???</th>
		           
	            </tr>
	            <tbody>
	        <tr>
	       
	            	<td colspan="9">조회된 결과가 없습니다</td>
	      
	         </tr>
	            </tbody>
	   </table>
	   <a href="/user/userpage" class="btn">마이페이지</a>
	   <li class="nav-item"><a class="nav-link" href="/user/userupdate">업데이트</a></li>
	   <li class="nav-item"><a class="nav-link" href="/user/usermileage">마일리지</a></li>
	   
	</div>
</body>
</html>
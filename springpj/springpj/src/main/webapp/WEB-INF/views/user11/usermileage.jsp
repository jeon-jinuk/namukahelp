<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저환영합니다</title>
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
		            
		            <th scope="col"> 마일리지</th>
		            
		        </tr>
	            </thead>
	            <tbody>
 	          
	            <tr> 
	            	
 	            	<td class="mileage">${user.mileage }</td> 
	            	
 	            	</tr> 
	         
	            	<td colspan="9">조회된 결과가 없습니다</td> 
            </tr> 
	            </tbody>
	   </table>
	   <a href="/user/userpage" class="btn"></a>
	   <li class="nav-item"><a class="nav-link" href="/user/userpage">마일리지</a></li>
	   
	</div>
</body>
</html>
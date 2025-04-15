<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
<form action="/views/user/login" method="post">

<div class="container">
  <h2>${user.id} 고유번호</h2>
  
    <div class="form-group">
      <label for="id">고유번호</label>
      <input type="text" class="form-control" id="id" name="id" value="${user.id}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="username">이름</label>
      <input type="text" class="form-control" id="username" name="username" value="${user.username}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="nick">닉네임</label>
      <input type="text" class="form-control" id="nick"name="nick" value="${user.nick}" readonly="readonly">
    </div>
  </div>
  
    <div class="form-group">
      <label for="email">이메일</label>
      <textarea class="form-control" rows="5" id="email" name="email" readonly="readonly">${user.email}</textarea>
    </div>
    <div class="form-group">
      <label for="addr">주소</label>
      <input type="text" class="form-control" id="addr"name="addr" value="${user.addr}" readonly="readonly">
    </div>
   
    <div class="form-group">
      <label for="role">권한</label>
      <input type="text" class="form-control" id="role"name="role" value="${user.role}" readonly="readonly">
    </div>
    
    <div class="form-group text-right">
  <!--  <c:if test="${sUser.username==board.writer}"> -->  
      <button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정하기</button>
      <button type="button" class="btn btn-secondary btn-sm" id="btnDelete">삭제하기</button>
  <!--   </c:if>  -->  
      <button type="button" class="btn btn-secondary btn-sm" id="btnList">목록보기</button>
    </div>
   
   
<script type="text/javascript">
var init = function(){
	
	$.ajax({
		type:"get",
		url:"/views/user/${user.id}",
		dataType:"JSON",
		contentType:"application/json;charset=utf-8",
	})
	.done(function(resp){
		//alert("resp"+resp)
		var str = "<table class='table table-hover mt-3'>";
		$.each(resp,function(key,val){
			str += "<tr>"
			str += "<td>" + val.id + "</td>"
			str += "<td>" + val.username + "</td>"
			str += "<td>" + val.rick + "</td>"
			
			str += "</tr>"
		})
		str += "</table>"
		$("#replyResult").html(str);
	})
};

init();

</script>   
 
</div>
	<input type="button" name="MyMileage" id="MyMileage">
	<input type="button" name="UserData" id="UserData"> 
</body>
</html>

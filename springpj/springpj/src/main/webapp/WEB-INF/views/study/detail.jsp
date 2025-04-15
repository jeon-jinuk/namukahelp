<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" />
<link rel="stylesheet" href="/css/index.css" />
<link rel="stylesheet" href="/css/study_detail.css" />
</head>
<body>

	<div class="container">

		<article>
			<div class="study-item">
				<div class="study-status">진행중</div>
				<div class="study-profile">
					<img src="${study.profile }" />
				</div>
				<div class="content">
					<p class="study_title">제목:${study.title}</p>
					<p class="study_info">소개:${study.info }</p>
					<ul>
						<li>지역 : ${study.local }</li>
						<li>스터디장 : ${study.leader.username }</li>
						<li>기간 : <fmt:formatDate value="${study.startDate}" />-<fmt:formatDate
								value="${study.endDate}" /></li>
						<li>제한인원 : ${study.limitCount }</li>
					</ul>
					<div class="likes">
						<i id="like" class="fa-regular fa-heart"></i> <label for="like">10</label>
					</div>
				</div>
				<c:if test="${principal.user.id != study.leader.id }">
					<button id="apply">신청하기</button>
					<!-- <button type="button" id="applyBtn">참여 신청</button> -->
				</c:if>
			</div>
			<div class="study_content">
				<h1>${study.title }</h1>
				<div class="study-content-detail">${study.content }</div>
				<div class="controller">
					<c:if test="${principal.user.id == study.leader.id }">
						<button class="delete">삭제</button>
						<button class="modify">수정</button>
					</c:if>
					<button class="list" onclick="location.href='/study/list'">목록</button>
				</div>
			</div>

		</article>

	</div>
</body>

<script type="text/javascript">
	$("#apply").click(()=>{
		if(${empty principal}){
			alert("로그인이 필요합니다.")
			return
		}
		
		if(confirm("${study.title}에 신청하시나요? \n 스터디 장에게 기본적인 정보가 제공될 수 있습니다."))
		{
			$.ajax({
				type:"post",
				url:"/study/apply",
				data:{
					"studyId":'${study.id}',
					"userId":'${principal.user.id}'
				}
			}).done((resp)=>{
				if(resp=="success"){
					alert("신청이 완료되었습니다.")
				}else if(resp=="fulled"){
					alert("인원이 다 찼습니다.")
				}else if(resp=="aleady"){
					alert("이미 신청한 스터디입니다.")
				}
			})
		}
	})
</script>
</html>
<%@ include file="../include/footer.jsp"%>
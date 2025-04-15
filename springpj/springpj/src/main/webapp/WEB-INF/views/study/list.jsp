<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="/css/index.css" />
<link rel="stylesheet" href="/css/study_list.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" />

</head>
<body>
	<div class="header">
		<h1>STUDY GROUP</h1>
		<span></span>
		<p>스터디그룹에 가입해서 다양한 혜택을 받아보세요</p>
	</div>

	<div class="container">

		<div id="my-study">
			<div class="my-studyies">
				<c:forEach items="${joins}" var="join">
					<div class="my-study" onclick="myStudyEnter('${join.study.id}')">
						<img src="${join.study.profile }" />
						<p class="study-title">${join.study.title }</p>
					</div>
				</c:forEach>
			</div>
			<div class="insert-my-study">
				<button type="button" id="btnMkStudy">
					<i class="fa-solid fa-plus"></i>
				</button>
			</div>
		</div>

		<div id="search">
			<form action="/study/list" method="get" class="search">
				<select name="field" id="field">
					<option value="title">제목</option>

				</select> <input type="text" name="word" placeholder="검색어를 입력하세요">
				<!-- <button type="submit">검색</button> -->
				<button>
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>


		<article>
			<div class="order">
				<select name="order" id="order">
					<option value="likes">인기순</option>
				</select>
			</div>
			<div class="study-items">
				<c:forEach var="study" items="${studies}">
					<fmt:formatDate value="${study.startDate}" pattern="yyyy.MM.dd"
						var="start" />
					<fmt:formatDate value="${study.endDate}" pattern="yyyy.MM.dd"
						var="end" />
					<div class="study-item" id="study${study.id}">
						<div class="study-status">진행중</div>
						<div class="study-profile">
						<img src="${study.profile }"
							onclick="location.href='/study/detail?id=${study.id}'">
						</div>
						<div class="content">
							<p class="study-title">${study.title}</p>
							<p class="study-info">${study.info }</p>
							<ul>
								<li>지역 : 부산</li>
								<li>기간 : ${start}-${end}</li>
								<li>제한인원 : ${study.limitCount }</li>
							</ul>
							<div class="likes-${study.id}">
								<button type="button" id="like" onclick="clickLike(${study.id})">
									<i id="likeIcon" class="fa-regular fa-heart"></i>
									<c:forEach items="${likes}" var="like">
										<c:choose>
											<c:when test="${like.study.id==study.id}">
												<i id="likeIcon" class="fa-solid fa-heart"></i>
											</c:when>
										</c:choose>
									</c:forEach>
									<span id="likeCount">${study.likes }</span>
								</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<ul class="paging">
			
				<c:if test="${prev==true}">
					<li class="prev"><a class="fas fa-angle-left"
							href="list?page=${startPage-pageSize}&field=${field}&word=${word}">
							</a></li>
				</c:if>
				
				<c:forEach begin="${startPage}" end="${endPage}" var="i">
					<li><a href="list?page=${i}">${i+1}</a></li>
				</c:forEach>
				
				<c:if test="${next==true}">
					<li class="next"><a class="fas fa-angle-right"
							href="list?page=${endPage+1}&field=${field}&word=${word}"></a></li>
				</c:if>
			</ul>
		</article>
	</div>

	<script type="text/javascript">

		
	
	
		function clickLike(id){
			var userId = ''
			if(${empty principal}){
				alert("로그인이 필요한 서비스입니다.")
				return;
			}else{
				userId = ${principal.user.id}
				$.ajax({
					type:"post",
					url:"/study/checkLike",
					data:{
						"studyId": id,
						"userId": userId
					}
				}).done((res)=>{
					const count = Number($("#likeCount").html())
					if(res=="success"){					
						alert("좋아요를 눌렀습니다!")
						location.href="/study/list"

					}else{
						alert("좋아요를 취소했습니다")
						location.href="/study/list"
					}
				})
			}
		}
		
		$("#btnMkStudy").click(()=>{
			if(${empty principal}){
				alert("로그인이 필요한 서비스입니다.")
				location.href="/login"
				return
			}
			location.href="/study/register"
		})
		
		
		function myStudyEnter(id){
			$.ajax({
				type:"post",
				url:"/study/confirm",
				data:{
					"id": id
				}
			}).done((res)=>{
				if(res=="success"){
					location.href="/study/"+id					
				}else{
					alert("가입된 멤버가 아닙니다.")
				}
			})
		}
	</script>
</body>
</html>
<%@ include file="../include/footer.jsp"%>
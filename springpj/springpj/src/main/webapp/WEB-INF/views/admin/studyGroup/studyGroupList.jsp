<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 관리</title>
</head>
<body>
	<div class="container">
		<h2>스터디 관리</h2>
		<table class="table table-hover" border="1">
			<thead>
				<tr>
					<th>스터디번호</th>
					<th>스터디명</th>
					<th>지역</th>
					<th>시작일</th>
					<th>마감일</th>
					<th>인원</th>
					<th>스터디리더</th>
					<th>마일리지</th>
					<th>좋아요</th>
<!-- 					<th>수정</th> -->
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="studyGroup">
					<tr>
						<td>${studyGroup.id}</td>
						<td>${studyGroup.title}</td>
						<td><%-- ${studyGroup.local} --%>지역</td>
						<td>${studyGroup.startDate}</td>
						<td>${studyGroup.endDate}</td>
						<td><%-- ${studyGroup.}/ --%>${studyGroup.limitCount}</td>
						<td>${studyGroup.leader}</td>
						<td>${studyGroup.mileage}</td>
						<td>${studyGroup.likes}</td>
<!-- 						<td><button type="button" class="btn btn-secondary btn-sm" -->
<!-- 								id="update" -->
<%-- 								onclick="location.href='/admin/studyGroup/studyGroupUpdateAdmin/${studyGroup.id}'">수정</button></td> --%>
						<td><button type="button" class="btn btn-secondary btn-sm"
								id="delete"
								onclick="location.href='/admin/studyGroup/delete/${studyGroup.id}'">삭제</button></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
<!-- 		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="register">추가</button>
		</div> -->
	</div>

</body>

<script type="text/javascript">
	$("#register").click(function() {
		location.href = "../branch/branchRegister"
	});
</script>
</body>
</html>
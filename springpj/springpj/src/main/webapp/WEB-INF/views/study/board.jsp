<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${study.title}</title>
<link
	href="https://cdn.jsdelivr.net/npm/froala-editor@latest/css/froala_editor.pkgd.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/froala-editor@latest/js/froala_editor.pkgd.min.js"></script>
<link>
<link rel="stylesheet" href="/css/board.css" />
</head>

<body>
	<div class="container">
		<div class="board-header">
			<section class="study-item">
				<div class="study-profile">
					<img src="${study.profile}">
				</div>
				<div class="content">
					<p class="study-title">${study.title}</p>
					<c:choose>
					<c:when test="${principal.user.id==study.leader.id}">
						<button type="button" onclick="location.href='/user/myLeaderStudy/${study.id}'" >
							<i class="fa-solid fa-gear"></i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" onclick="outStudy()">
							<i class="fa-solid fa-arrow-right-from-bracket"></i>
						</button>
					</c:otherwise>
				</c:choose>
					<p class="study-info">${study.info}</p>
					<p class="study-date">
						<fmt:formatDate value="${study.startDate}" pattern="yyyy-MM-dd" />
						-
						<fmt:formatDate value="${study.endDate}" pattern="yyyy-MM-dd" />
					<p class="study-joined">${joinedCount}/${study.limitCount}</p>
					<p class="study-mile">
						현재 <b>${study.mileage}</b> 마일리지 적립 중 🏃‍♂️
					</p>

				</div>
			</section>
			<div class="table-responsive">
				<h3>예약 일정</h3>
				<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th scope="col">예약번호</th>
							<th scope="col">예약일자</th>
							<th scope="col">주최자</th>
							<th scope="col">장소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookList}" var="book">
							<tr>
								<td>${book.id}</td>
								<td>${book.bookDate}</td>
								<td>${book.booker.nick}</td>
								<td>${book.branch.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<form action="/study/board/register" method="post" id="frm">
			<input type="hidden" name="studyId" value="${study.id}">
<!-- 			<textarea id="content" name="content" style="display: none"></textarea> -->
			<div id="content"></div>
			<button type="button" id="btnInsert">작성하기</button>
		</form>

		<div id="board-list"></div>
		<!-- container -->
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
<script>
var editor = new FroalaEditor("div#content", {
    height:150,
    imageUploadURL:'/study/board/imgupload',
    imageUploadParam:'file',
    imageUploadMethod:'post',
    imageAllowedTypes:['jpeg','jpg','png'],

}, function () {
    
});


$("#btnInsert").click(() => {
	if(editor.html.get()==''){
		alert("내용을 입력해주세요")
		return
	}
	$.ajax({
		type:"post",
		url:"/study/board/register",
		data:{
			"study":${study.id},
			"writer":"${principal.user.username}",
			"content": editor.html.get()
		}
	}).done((resp)=>{
		alert("등록이 완료되었습니다.")
		editor.html.set('')
		init();
// 		location.href="/study/board/"+"${study.id}"
	})
	
});
	const init= ()=>{
		$.ajax({
			type: "get",
			url : "/study/board/${study.id}",
			dataType:"JSON",
			cotentType: "application/json; charset=utf-8",
			success:function(resp){
				var str=''
				$.each(resp, (key,val)=>{
				str += `<div class="board-item" id="`+val.id+`">
                <div class="board-header">
                    <div class="profile">
                        <img src="`+val.profile+`" />
                        <div>
                            <p class="writer">`+val.writer+`</p>
                            <p class="date">`+val.regdate+`</p>
                            
                        </div>
                    </div>`
                    if("${principal.user.username}"==val.writer){
	                    str+=`<div class="board-btn">
	                        <button class="board-delete" onclick="deleteBoard(`+val.id+`)">삭제</button>
	                    </div>`
					}
                   str+=`
                </div>
                <div class="board-content" id="board-content-`+val.id+`">
                    `+val.content+`
                </div>
                <div class="more-reply">
                	<button type="button" id="moreReply" onclick="moreReply(`+val.id+`)">
	                    <span id="reply-count">댓글 `+val.replyCount+`</span>
	                    <i class="fa-solid fa-chevron-down"></i>
                    </button>
                    <div id="reply-list-`+val.id+`" style="display:none"></div>
                </div>
                <div class="board-footer">
                    <input
                        type="text"
                        name="reply"
                        id="reply`+val.id+`"
                        placeholder="내용을 입력해 주세요"
                    />
                    <button id="replyBtn" onclick="insertReply(`+val.id+`)">
                        <i class="fa-solid fa-circle-plus"></i>
                    </button>
                </div>
            </div>
        </div>`	
				})
				$("#board-list").html(str);	
			},
			error:function(request, status, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		})
	}
	
	init();
	
	function deleteBoard(id){
		$.ajax({
			type:"delete",
			url:"/study/board/"+id,
		}).done((resp)=>{
			alert("게시글 삭제 완료");
			init();
		}).fail(()=>{
			alert("게시글 삭제 실패")
		})
	}
	
	
	function insertReply(id){
		if(${empty principal}){
			alert("로그인이 필요합니다.")
			return;
		}
		if ($("#reply"+id).val()==''){
			alert("내용을 입력해주세요")
			return
		}
		var data={
				"board":id,
				"replyer" :"${principal.user.username}",
				"content" : $("#reply"+id).val()
		}
		
		$.ajax({
			type:"post",
			url:"/study/reply/register",
			data: data
		}).done((resp)=>{
			alert("댓글을 성공적으로 작성했습니다.");
			init()
			location.href="#"+id
		}).fail(()=>{
			alert("댓글 추가 실패")
		})
	}
	
	
	function fdel(rno){
		$.ajax({
			type:"delete",
			url:"/study/reply/"+rno,
		}).done((resp)=>{
			alert("댓글 삭제 완료");
			init();
		}).fail(()=>{
			alert("댓글 삭제 실패")
		})
	}
	
	
	function moreReply(id){
		$.ajax({
			type: "get",
			url : "/study/reply/"+id,
			dataType:"JSON",
			cotentType: "application/json; charset=utf-8",
		}).done((resp)=>{
			var str=''
			$.each(resp, (key,val)=>{
				str+=`
					<div class="reply-item">
						<div class="reply-profile">
							<img src="`+val.profile+`"/>
						</div>
						<div>
							<p class="reply-replyer">`+val.replyer+`</p>
							<p class="reply-content">`+val.content+`</p>
							<p class="reply-regdate">`+val.regdate+`</p>
						</div>
						<div>
						`
						if("${principal.user.username}"==val.replyer){
			            	str+=`<button class="reply-delete" onclick="fdel(`+val.id+`)">삭제</button>`
						}
						str+=`
						</div>
					</div>
				`
			})
			$("#reply-list-"+id).html(str);	
			var list = document.querySelector("#reply-list-"+id)
			if(list.style.display=="none"){
				list.style.display="block"
			}else{
				list.style.display="none"
			}
		})
	}
	
	function outStudy(){
		if(confirm("정말로 스터디를 나가시겠습니까?")){
			$.ajax({
				type: "post",
				url : "/study/outStudy",
				data:{
					"joinerId":${joiner.id}
				}
			}).done((resp)=>{
				alert("탈퇴가 완료되었습니다.")
				location.href="/study/list"
			})
		}
	}
</script>

</html>
/**
 * 
 */
 
$(document).ready(function(){
	//alert("aaaaaaaaaaaaaaa");
	//var exp = /^[0-9]{3}[0-9]{4}[0-9]{4}$/
	
	$("#btnJoin").click(function(){
		if($("#username").val()==""){
			alert("아이디를 입력하세요");
			$("#username").focus();
			return false;
		}
		if($("#nick").val()==""){
			alert("닉네임을 입력하세요");
			$("#nick").focus();
			return false;
		}
		if($("#password").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#password").focus();
            return false;
		}
		if($("#pass_check").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#pass_check").focus();
            return false;
		}
		if($("#password").val() != $("#pass_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pass_check").focus();
            return false;
		}
		
		if($("#email").val() == "") {
			alert("이메일을 입력하세요");
			$("#email").focus();
            return false;
		}
		if($("#addr").val() == "") {
			alert("주소를 입력하세요");
			$("#addr").focus();
            return false;
		}
		if($("#role").val() == "") {
			alert("role를 입력하세요");
			$("#role").focus();
            return false;
		}
		
		var data={
			"username":$("#username").val(),
			"nick":$("#nick").val(),
			"password":$("#password").val(),
			"email":$("#email").val(),
			"addr":$("#addr").val(),
			"role":$("#role").val()
		}
		
		$.ajax({
			type:"post",
			url:"/user/join",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(res){
			if(res=="success"){
				alert("회원가입을 축하합니다");
				location.href="/user/login";
			}else if(res=="fail"){
				alert("아이디 중복확인하세요");
				$("#username").val("")
			}
		})
		//$("#frm").submit();
		
	});
});

	$("#btnLogin").click(function() {

		$.ajax({
			type : "post",
			url : "/user/login",
			data : {
				"username" : $("#username").val(),
				"password" : $("#password").val()
			}
		}).done(function(resp) {
			if (resp == "no") {
				alert("회원이 아닙니다. 회원가입하세요");
				location.href = "/join"
			} else if (resp == "success") {
				alert("로그인 성공");
				location.href = "../"
			} else {
				alert("비밀번호를 확인하세요");
			}
		})
	})
	
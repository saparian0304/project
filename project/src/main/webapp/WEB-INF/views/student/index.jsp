<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function insert() {
	var param = {
			"id" : $("#id").val(),
			"name" : $("#name").val(),
			"age" : $("#age").val()
	};
	$.ajax({
		url: "/project/student/insert",
		method: "post",
		data : JSON.stringify(param),
		dataType: "json",
		contentType:"application/json",
		success: function(res) {
			console.log(res);
			if (res['_id']) {
				alert('정상적으로 등록되었습니다.');
			}
		},
		error: function(res){
			console.log(res);
		}
	});
}
</script>
</head>
<body>
<h1>MongoDB 연동</h1>
아이디 : <input type="text" id="id"><br>
이름 : <input type="text" id="name"><br>
나이 : <input type="text" id="age"><br>
<input type="button" value="저장" onclick="insert();">
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>이메일 찾기</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    	function findEmail() {
    		if ($('#name').val() == ''){
    			alert('이름을 입력해주세요');
    			$('#name').focus();
    			return false;
    		}
    		if ($('#hp').val() == ''){
    			alert('전화번호를 입력해주세요');
    			$('#hp').focus();
    			return false;
    		}
    		
    		// ajax 조회
    		$.ajax({
    			url : "findEmail.do",
    			method : "post",
    			data : {
    				name : $('#name').val(),
    				hp : $('#hp').val()
    			},
    			success : function(res) {
    				if (res.trim() == '') {
						var str = "이메일을 찾을 수 없습니다.";
    				} else {
	    				var str = '해당 정보로 가입된 이메일은 "' + res.trim() + '"입니다.';
    				}
	    				$('#msg').html(str);
    			}
    		})
    		
    		return false;
    	}
    </script>
</head>
<body>
    																	<!-- return을 안쓰면 false를 리턴해도 전송이 됨 -->	
        <form action="login.do" method="post" id="loginFrm1" name="loginFrm1" onsubmit="return findEmail();"><!-- header에서 id="board"이미 사용중이라서 board2로 함 -->
            <div class="sub">
                <div class="size">
                    <h3 class="sub_title">이메일 찾기</h3>
                    
                    <div class="member">
                        <div class="box">
                            <fieldset class="login_form">
                                <ul>
                                    <li><input type="text" id="name" name="name" placeholder="이름"></li>
                                    <li><input type="text" id="hp" name="hp" placeholder="전화번호"></li>
                                    <li id="msg"></li>
                                </ul>
                                <div class="login_btn"><input type="submit" value="이메일 찾기" alt="이메일 찾기" /></div>
                            </fieldset>
                            <div class="btnSet clear">
                                <div>
                                    <a href="join.do" class="btn">회원가입</a> 
                                    <a href="findPwd.do" class="btn">비밀번호 찾기</a>
                                </div>
                            </div>
                        </div>
                    </div>
        
                </div>
            </div>
        </form>
        
</body>
</html>
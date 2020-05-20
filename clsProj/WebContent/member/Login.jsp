<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인크레파스 로그인</title>
<link rel="stylesheet" href="../css/w3.css"/>
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<link rel="stylesheet" href="../css/login.css"/>
<script type="text/javascript" src="../js/login.js"></script>
<style>

</style>
<script type="text/javascript">
	$(document).ready(function(){
		// 1. 데이터 읽고
		$('#btn2').click(function(){
			var sid = $('#id').val();
			var spw = $('#pw').val();
			$('#fid').val(sid);
			$('#fpw').val(spw);
			// 2. 유효성 검사하고
			if(!sid){
				$('#id').focus();
				alert('아이디 안씀');
				return;
			}
			if(!spw){
				$('#pw').focus();
				alert('비번 안씀');
				return;
			}
			
			$('#frm').attr('method', 'POST');
			$('#frm').attr('action', '/clsProj/member/loginProc.cls');	
			// 원래는 jsp파일을 직접 부르지 않고 웹어플리케이션을 통해서 뷰를 부름
			$('#frm').submit();
			
		});

		<%
			String sid = (String)session.getAttribute("SID");
		%>
		// 로그인 여부에 따른 처리
		var sid = '<%= sid %>';
		if(sid != 'null' && sid.length != 0){
			$('#loginWin').css('display', 'none');
			$('#msg').html(sid);
			$('#msgWin').css('display', '');			
		}
		
		$('#hbtn').click(function(){
			$(location).attr('href', '/clsProj/main.cls');
		});
	});
</script>
</head>
<body>
	<form id="frm" name="frm">
		<input type="hidden" name="id" id="fid">
		<input type="hidden" name="pw" id="fpw">
	</form>
	<div class="w3-content mxw" id="loginWin">
		<h3 class="w3-padding w3-blue w3-center w3-card-4 w3-round-large">Login</h3>
		<div class="w3-col w3-padding w3-card-4 w3-round-large w3-border w3-margin-top">
			<label for="id" class="w3-col m2 w3-right-align w3-padding-16">I D : </label>
			<div class="w3-col m10 w3-padding">
				<input type="text" class="w3-col w3-input w3-border" id="id" name="id">
			</div>
			<label for="pw" class="w3-col m2 w3-right-align w3-padding-16">P W : </label>
			<div class="w3-col m10 w3-padding">
				<input type="password" class="w3-col w3-input w3-border" id="pw" name="pw">
			</div>
			<!-- 
			<label for="repw" class="w3-col m2 w3-right-align w3-padding-16 repw">PW Check : </label>
			<div class="w3-col m10 w3-padding repw">
				<input type="password" class="w3-col w3-input w3-border" id="repw" name="repw">
			</div>
			<div class="w3-col w3-center">
				<span id="pwmsg"></span>
			</div>
			 -->
		</div>
			<!-- 
		<div class="w3-col w3-margin-top w3-padding w3-card w3-border dnone" id="dl">
			<h3 id="txt"></h3>
		</div>
			 -->
		<div class="w3-col w3-margin-top">
			<span class="w3-half w3-red w3-button w3-padding w3-center" id="hbtn">Home</span>
			<span class="w3-half w3-blue w3-button w3-padding w3-center" id="btn2">Login</span>
		</div>
		<div class="w3-third" id="msgWin" style="display: none;">
			<h2 class="w3-col m4 w3-margin-top w3-card-4" style="padding-top: 80px; height: 250px;" id="msg"></h2>
			<h2 class="w3-col m8 w3-margin-top w3-card-4" style="padding-top: 80px; height: 250px;"> 님은 이미 로그인했습니다</h2>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/user.css">
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sbtn').click(function(){
			var str = $('#body').val();
			// 로그인이 안 된 경우는 로그인 폼으로 보낸다
			if(${SID eq null}) {
				alert('로그인 창으로 전환됩니다');
				$(location).attr('href', '/clsProj/member/login.cls');
			}
			if(!str || str.length > 50) {
				// 입력내용이 없거나 입력내용의 길이가 50을 넘어가면 뒤는 잘라버린다
				$('#body').val(str.substr(0, 50));
				return;
			}
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<div class="w3-col">
			<h2 class="w3-indigo w3-center w3-card">방명록 작성</h2>
			<div class="w3-col w3-padding w3-card">
				<div class="w3-col">
					<label for="id" class="w3-col m2"><h5>아이디 : </h5></label>
					<h5 class="w3-col m8" id="id">${SID }</h5>
				</div>
				<form class="w3-col w3-margin-bottom" method="post" action="/gBoard/gBoardProc.cls" id="frm">
					<input type="hidden" id="sid" name="id" value="${SID }">
					<label for="id" class="w3-col"><h5>내용 : </h5></label>
					<textarea class="w3-col w3-border" name="body" id="body" cols="50" rows="5" style="resize: none;"></textarea>
				</form>
			</div>
			<div class="w3-col">
				<div class="w3-half w3-green w3-button" id="hbtn">home</div>
				<div class="w3-half w3-blue w3-button" id="sbtn">submit</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/clsProj/css/w3.css">
<link rel="stylesheet" href="/clsProj/css/user.css">
<script type="text/javascript" src="/clsProj/js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#cbtn').click(function(){
		$(location).attr('href', '/clsProj/reBoard/reBoardList.cls');
	});
});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<div class="w3-col">
			<h2 class="w3-blue w3-center" >댓글 작성</h2>
			<div class="w3-col w3-padding w3-card">
				<div class="w3-col">
					<label for="body" class="w3-col m2">글내용 :</label>
					<textarea class="w3-col w3-border" name="body" id="body"></textarea>
				</div>
				<div class="w3-right-align">
				<div class="w3-button w3-blue w3-margin-top">저장</div>
				<div class="w3-button w3-red w3-margin-top" id="cbtn">취소</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/user.css">
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sbtn').click(function(){
			var str = $('#body').val();
			if(str != null){
				$('#frm').submit();
			} else {
				alert('내용입력하세요');
			}
			
		});
		
		$('#login').click(function(){
			$(location).attr('href', '/clsProj/member/login.cls');
		})
		$('#logout').click(function(){
			$(location).attr('href', '/clsProj/member/logoutProc.cls');
		})
		
		$('#home').click(function(){
			$(location).attr('href', '/clsProj/main.cls');
		})
	});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<div class="w3-col">
			<h2 class="w3-blue w3-center w3-padding w3-margin-bottom">방명록 리스트</h2>
			<div class="w3-col">
				<c:if test="${empty SID }">
					<div class="w3-col m1 w3-right w3-button w3-blue" id="login">Login</div>
				</c:if>
				<c:if test="${not empty SID }">
					<div class="w3-col m1 w3-right w3-button w3-red" id="logout">Logout</div>
				</c:if>
				<div class="w3-col m1 w3-left w3-button w3-green" id="home">Home</div>
			</div>
			<c:if test="${CNT ne 1 }">
				<form class="w3-col w3-border" id="frm" method="post" action="./gBoardProc.cls">
					<div class="w3-col w-100 m2 pd-10">
						<img class="w-auto" style="height: 70px; margin: 10px 10px 0px 10px;" src="/clsProj/img/${AVT }" />
					</div>
					<div class="w3-rest">
						<div class="w3-col w3-border">
							<h5 class="w3-half w3-left-align" style="margin: 0px; padding-left: 0px;">${SID }</h5>
							<div class="w3-half w3-right-align w3-button" id="sbtn" >submit</div>
						</div>
						<div class="w3-col w3-border">
							<textarea id="body" class="w3-half w3-padding" cols="55" rows="3" style="resize: none"></textarea>
						</div>
					</div>
				</form>
			</c:if>
			
			<%-- <c:if test="${not empty LIST }"> --%>
				<c:forEach var="vo" items="${LIST }">
				<div class="w3-col w3-border" id="${vo.gno }">
					<div class="w3-col w-100 m2 pd-10">
						<img class="w-auto" style="height: 70px; margin: 10px 10px 0px 10px;" src="/clsProj/img/${AVT }" />
					</div>
					<div class="w3-rest">
						<div class="w3-col w3-border">
							<h5 class="w3-half w3-left-align" style="margin: 0px; padding-left: 0px;">${vo.id }</h5>
							<h5 class="w3-half w3-right-align" style="margin: 0px; padding-right: 0px;">${vo.sDate }</h5>
						</div>
						<div class="w3-col w3-border">
							<h5 class="w3-half w3-padding">${vo.body }</h5>
						</div>
					</div>
				</div>
				</c:forEach>
			<%-- </c:if> --%>
			
			<c:if test="${empty LIST }">
				<div class="w3-col w3-border w3-card" id="gmsg">
					<h3 class="w3-padding w3-center">아직 작성된 글이 없습니다</h3>
				</div>
			</c:if>
		</div>
	</div>
	
</body>
</html>
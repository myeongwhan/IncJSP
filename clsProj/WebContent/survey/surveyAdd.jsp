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
	$(function(){
		$('#hbtn').click(function(){
			$(location).attr('href', '/clsProj/main.cls');
		});
		
		$('.frm2').css('display', 'none');
		
		$('#addIbtn').click(function(){
			// 데이터 유효성 검사
			var stitle = $('#title').val();
			var sstart = $('#start').val();	// yyyy-MM-dd
			var send = $('#end').val();
			if(!stitle || !sstart || !send){
				return;
			}
			
			$.ajax({
				url: "/clsProj/survey/surveyInfoProc.ck",
				type: "POST",
				dataType: "json",
				data: {
					"title": stitle,
					"start": sstart,
					"end": send
				},
				success: function(obj){
					// obj:
					// 현재 비동기통신이 실행될 때 데이터타입이 json인데
					// service()가 실행될 때 json문서를 담을 변수
					var cnt = obj.cnt;
					var sino = obj.sino;
					$('#sino').val(sino);
					if(cnt == 1){
						$('.frm1').css('display', 'none');
						$('.frm2').css('display', '');
					}
				},
				error: function(){
					alert('!!통신에러!!');
				}
			});
		});
		
		$('#addQA').click(function(){
			var sino = $('#sino').val();
			var sq = $('#sq').val();
			var sa1 = $('#sa1').val();
			var sa2 = $('#sa2').val();
			var sa3 = $('#sa3').val();
			var sa4 = $('#sa4').val();
			if(!sino || !sq || sa1 || sa2 || sa3 || sa4){
				return;
			}
			
			$.ajax({
				url: "/clsProj/survey/surveyQAProc.ck",
				type: "POST",
				dataType: "json",
				data: {
					"sino": sino,
					"sq": sq,
					"sa1": sa1,
					"sa2": sa2,
					"sa3": sa3,
					"sa4": sa4
				},
				success: function(obj){
					var cnt = obj.cnt;
					if(cnt == 1){
						$('#sq').val('');
						$('#sa1').val('');
						$('#sa2').val('');
						$('#sa3').val('');
						$('#sa4').val('');
					}
				},
				error: function(){
					alert('##통신에러##');
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<h2 class="w3-indigo w3-card w3-padding w3-center w3-margin-bottom">설문 정보 입력</h2>
		<form method="POST" action="/clsProj/survey/surveyInfoProc.cls" class="w3-col w3-card w3-padding frm1" id="frm1">
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="title"><h5>설문 주제: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="title" name="title">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="start"><h5>설문 시작 시간: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="date" id="start" name="start">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="end"><h5>설문 종료 시간: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="date" id="end" name="end">
				</div>
			</div>
		</form>
		<div class="w3-col w3-border w3-card w3-margin-top frm1">
			<div class="w3-half w3-button w3-green" id="hbtn">home</div>
			<div class="w3-half w3-button w3-blue" id="addIbtn">설문정보등록</div>
		</div>
		<!-- 설문 문항 등록 -->
		<h4 class="w3-center w3-lime w3-padding w3-margin-bottom w3-card frm2" >* ${TITLE }</h4>
		<form method="POST" action="/clsProj/survey/surveyInfoProc.cls" class="w3-col w3-card w3-padding frm2" id="frm2">
			<input type="hidden" name="sino" id="sino" value="${SINO }">
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="sq"><h5>설문 문항: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="sq" name="sq">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="sa1"><h5>보기 1: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="sa1" name="sa1">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="sa2"><h5>보기 2: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="sa2" name="sa2">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="sa3"><h5>보기 3: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="sa3" name="sa3">
				</div>
			</div>
			<div class="w3-row">
				<label class="w3-col w-150 w3-right-align" for="sa4"><h5>보기 4: </h5></label>
				<div class="w3-rest pd-left-10">
					<input class="w3-input w3-border" type="text" id="sa4" name="sa4">
				</div>
			</div>
		</form>
		<div class="w3-col w3-border w3-card w3-margin-top frm2">
			<div class="w3-half w3-button w3-green" id="hbtn">home</div>
			<div class="w3-half w3-button w3-blue" id="addQA">설문정보등록</div>
		</div>
	</div>
</body>
</html>
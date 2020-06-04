$(document).ready(function(){
	// 정규식
	$('#idck').click(function(){
		alert('아직 안만듦');
	});
	
	// 비번일치
	document.getElementById('pwck').keyup = function(){
		var pw = document.getElementById('pw').value;
		var pwck = document.getElementById('pwck').value;
		if(pw == pwck){
			document.getElementById('pwckshow').HTML('일치');
			document.getElementById('pwckshow').setAttribute('color', 'blue');
		} else{
			document.getElementById('pwckshow').HTML('다름');
			document.getElementById('pwckshow').setAttribute('color', 'red');
		}
	}
	$('#pwck').keyup(function(){
		var pw = $('#pw').val();
		var pwck = $('#pwck').val();
		if(pw == pwck){
			$('#pwckshow').html('일치');
			$('#pwckshow').css('color', 'blue');
		} else{
			$('#pwckshow').html('다름');			
			$('#pwckshow').css('color', 'red');
		}
	});
	
	// 프사 미리보기
	$('#profile').change(function(e){
		var img = URL.createObjectURL(e.target.files[0]);
//		document.getElementById('img').setAttribute('src', img);
		$('#img').attr('src', img);
	});
	
	// 성별 선택해서 아바타 선택창 띄우기
	$('#M').click(function(){
//		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar1.png"><img class="w3-padding imgsize" src="../img/img_avatar2.png"><img class="w3-padding imgsize" src="../img/img_avatar3.png">'
		$('#genbox').html('<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label><div class="w3-col m9 rel"><input type="radio" id="avt1" name="avtgen" class="w3-radio"><label for="avt1"><img class=" w3-button imgsize" src="../img/img_avatar1.png"></label><input type="radio" id="avt2" name="avtgen" class="w3-radio"><label for="avt2"><img class=" w3-button imgsize" src="../img/img_avatar2.png"></label><input type="radio" id="avt3" name="avtgen" class="w3-radio"><label for="avt3"><img class=" w3-button imgsize" src="../img/img_avatar3.png"></label></div>');
	});
	$('#W').click(function(){
//		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar4.png"><img class="w3-padding imgsize" src="../img/img_avatar5.png"><img class="w3-padding imgsize" src="../img/img_avatar6.png">'
		$('#genbox').html('<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label><div class="w3-col m9 rel"><input type="radio" id="avt4" name="avtgen" class="w3-radio"><label for="avt4"><img class=" w3-button imgsize" src="../img/img_avatar4.png"></label><input type="radio" id="avt5" name="avtgen" class="w3-radio"><label for="avt5"><img class=" w3-button imgsize" src="../img/img_avatar5.png"></label><input type="radio" id="avt6" name="avtgen" class="w3-radio"><label for="avt6"><img class=" w3-button imgsize" src="../img/img_avatar6.png"></label></div>');
	});
	
	// 년월일
	for(let i=1900; i<=2020; i++){
		$('#year').append('<option>' + i + '</option>');	
	}
	for(let i=1; i<=12; i++){
		$('#month').append('<option>' + i + '</option>');	
	}
	for(let i=1; i<=31; i++){
		$('#day').append('<option>' + i + '</option>');	
	}
	
	// 가입/취소얼럿
	$('#sign').click(function(){
		alert('가입완료 서밋 안만듦');
	});
	$('#cancel').click(function(){
		alert('홈으로돌아감 홈링크안만듦');
	});
	
});

	// 비번일치
	document.getElementById('pwck').onkeyup = function(){
		var pw = document.getElementById('pw').value;
		var pwck = document.getElementById('pwck').value;
		if(pw == pwck){
			document.getElementById('pwckshow').innerHTML = '일치';
			document.getElementById('pwckshow').setAttribute('color', 'blue');
		} else{
			document.getElementById('pwckshow').innerHTML = '다름';
			document.getElementById('pwckshow').setAttribute('color', 'red');
		}
	}
	
	// 프사 미리보기
	document.getElementById('profile').onchange = function(e){
		var img = URL.createObjectURL(e.target.files[0]);
		document.getElementById('img').setAttribute('src', img);
	}
	
	// 성별 선택해서 아바타 선택창 띄우기
	document.getElementById('M').onclick = function(){
		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar1.png"><img class="w3-padding imgsize" src="../img/img_avatar2.png"><img class="w3-padding imgsize" src="../img/img_avatar3.png">'
	}
	document.getElementById('W').onclick = function(){
		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar4.png"><img class="w3-padding imgsize" src="../img/img_avatar5.png"><img class="w3-padding imgsize" src="../img/img_avatar6.png">'
	}
	
	// 년월일
	for(let i=1900; i<=2020; i++){
		document.getElementById('year').append('<option>' + i + '</option>');
	}
	for(let i=1; i<=12; i++){
		document.getElementById('month').append('<option>' + i + '</option>');
	}
	for(let i=1; i<=31; i++){
		document.getElementById('day').append('<option>' + i + '</option>');
	}
	
	// 가입/취소얼럿
	document.getElementById('sign').onclick = function(){
		document.getElementById('frm').submit();
	}
	document.getElementById('cancel').onclick = function(){
		location.setAttribute('href', '/clsProj/main.cls');
	}
	
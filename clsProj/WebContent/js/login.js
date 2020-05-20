$(document).ready(function(){
	$('#id').blur(function(){
		// input태그의 내용 읽어오고
		var sid = $('#id').val();
		// 읽은 내용 h3태그에 넣어주고
		$('#txt').text(sid);
		// 상위태그(#dl) 보이게 하고
		$('#dl').stop().slideToggle();
	});
	
	$('#pw, #repw').keyup(function(){
		// 1. 데이터 읽고 변수에 담기
		var spw = $('#pw').val();
		var repw = $('#repw').val();
		// 2. 데이터 비교
		if(spw == repw){
		// 3. 출력
			$('#pwmsg').text('*** 비밀번호가 일치합니다 ***');
			// 태그의 스타일 속성을 변경 또는 읽는 함수 css()
			$('#pwmsg').css('color', 'blue');
//			$('#pwmsg').addClass('w3-blue');
			$('.repw').css('display', 'none');
		} else {
			$('.repw').css('display', '');
			$('#pwmsg').text('*** 비밀번호가 일치하지 않습니다 ***');
			$('#pwmsg').css('color', 'red');
//			$('#pwmsg').addClass('w3-red');
		}
	});
	
	// 문제] 버튼에 마우스가 올려지면 버튼의 색상을
	//		red -> orange, blue -> aqua 로 변경하는 이벤트를 추가하세요
	//		단, w3.css 클래스는 사용하지 않는 것으로
	
//	$('#btn1').mouseenter(function(){
//		$('#btn1').css('background-color', 'orange');
//		$('#btn1').removeClass('w3-red');
//	});
//	$('#btn2').mouseenter(function(){
//		$('#btn2').css('background-color', 'aqua');
//		$('#btn2').removeClass('w3-blue');
//	});
	
	$('.w3-button').mouseenter(function(){
		var sid = $(this).attr('id');
		if(sid == 'btn1'){
			$(this).removeClass('w3-button');
			$(this).removeClass('w3-red');
			$(this).css('background-color', 'orange');
		} else {
			$(this).removeClass('w3-button');
			$(this).removeClass('w3-blue');
			$(this).css('background-color', 'aqua');
		}
	});
	$('.w3-button').mouseleave(function(){
		$(this).addClass('w3-button');
		var sid = $(this).attr('id');
		if(sid == 'btn1'){
			$(this).addClass('w3-red');
		} else {
			$(this).addClass('w3-blue');
		}
	});
	
	// btn1을 클릭하면 아이디가 id인 태그에
	// w3-input 클래스가 적용되어있는지 확인하자
	$('#btn1').click(function(){
		var bool = $('#id').hasClass('w3-input');
		alert('w3-input : ' + bool);
	});
	
	/*$('#btn2').click(function(){
		alert('###########################');
//		location.href = 'day02.txt';	// 자바스크립트
//		$(location).attr('href', 'day02.txt');	// jquery
		
		// 문제] 이 버튼이 클릭되면 아이디와 비밀번호를
		//		result1.html 페이지에 출력되게 하세요
		
		// 1. 데이터 읽고
		var sid = $('#id').val();
		var spw = $('#pw').val();
		// 2. 데이터 채워주고
		$('#fid').val(sid);
		$('#fpw').val(spw);
		// 3. 폼태그 전송하고
//		$('#frm').submit();
	});*/
	
});
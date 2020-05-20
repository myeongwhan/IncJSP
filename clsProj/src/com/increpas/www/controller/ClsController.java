package com.increpas.www.controller;


/**
 * 이 클래스는 디스패치 컨트롤러에서 요청을 처리할 때 사용할 클래스들의
 * 다형성 구현을 위해서 만든 인터페이스
 * 
 * @author	이명환
 * @since	2020/05/11
 */
import javax.servlet.http.*;

// servlet 클래스를 상속받지 않았음. 이대로는 맵핑 불가
public interface ClsController {
	String exec(HttpServletRequest req, HttpServletResponse resp);
}

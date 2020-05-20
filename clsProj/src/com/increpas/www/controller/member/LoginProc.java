package com.increpas.www.controller.member;

import java.io.IOException;

/**
 *  이 클래스는 로그인 처리 요청을 받아서 로그인 처리를 할 클래스이다
 *  
 *  우리는 로그인 처리를 세션에 아이디를 기억시키는 것으로 한다
 *  
 * @author	이명환
 * @since	2020/05/06
 */
import javax.servlet.http.*;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.MemberDAO;

public class LoginProc implements ClsController {
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 1. 데이터 받고	<== 파라미터로 전달된 데이터 받아서 변수에 담고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		// 2. 데이터베이스 작업에 데이터 넘기고
		MemberDAO mDao = new MemberDAO();
		// 3. 결과 받고
		int cnt = mDao.getCnt(sid, spw);
		// 4. 결과에 따라서 처리하고
		/*
		 	결과가 어떻게 나오든지
		 	결국 페이지는 새로운 요청이 발생을 해서 띄워져야 할 것이다
		 */
		String view = "";
		if(cnt == 0) {
//			System.out.println(cnt);
			// 이 경우는 정보에 맞는 회원이 없다는 이야기이므로
			// 다시 로그인 페이지로 이동한다
			view = "/clsProj/member/login.cls";
		} else {
			view = "/clsProj/main.cls";
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
		}
		
		req.setAttribute("isRedirect", true);
		return view;
	}
}

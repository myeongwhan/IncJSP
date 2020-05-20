package com.increpas.www.controller.member;

import javax.servlet.*;
import javax.servlet.http.*;

import com.increpas.www.controller.ClsController;

public class JoinForm implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/member/join.jsp";
		// 로그인이 되어있는지 확인하고 처리한다
//		HttpSession session = req.getSession();
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid != null) {
			req.setAttribute("isRedirect", true);
			view = "/clsProj/main.cls";
		}
		return view;
	}

}

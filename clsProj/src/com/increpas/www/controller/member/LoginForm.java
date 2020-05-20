package com.increpas.www.controller.member;

import java.io.IOException;

import javax.servlet.http.*;

import com.increpas.www.controller.*;
import com.increpas.www.dao.MemberDAO;

public class LoginForm implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/member/Login.jsp";
		
		return view;
	}

}

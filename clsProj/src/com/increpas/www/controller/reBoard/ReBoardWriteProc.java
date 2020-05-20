package com.increpas.www.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.ReBoardDAO;

public class ReBoardWriteProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/reBoard/reBoardRplRedirect.jsp";
		
		String sid = req.getParameter("id");
		String body = req.getParameter("body");
		
		@SuppressWarnings("null")
//		int upno = (Integer) null;
		
		ReBoardDAO rDAO = new ReBoardDAO();
		int cnt = rDAO.insertReply(sid, body, (Integer) null);
		
		req.setAttribute("isRedirect", true);
		
		return view;
	}

}

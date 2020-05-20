package com.increpas.www.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.ReBoardDAO;

public class ReBoardRplProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/reBoard/reBoardRplRedirect.jsp";
		
		// 데이터 받고
		String sid = req.getParameter("id");
		String body = req.getParameter("body");
		String supno = req.getParameter("upno");
		
		// 데이터 변환하고
		int upno = 0;
		
		try {
			upno = Integer.parseInt(supno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ReBoardDAO rDAO = new ReBoardDAO();
		int cnt = rDAO.insertReply(sid, body, upno);
		
		// 결과에 따라 처리하고
		/*
		if(cnt == 1) {	// 성공한 경우
			// 뷰를 두 개 받게 되는데, 하나만 받기 위해 속성값 지정하기
			req.setAttribute("isSuccess", "Y");
		} else {
			req.setAttribute("isSuccess", "N");
			req.setAttribute("upno", upno);
		}
		*/
		req.setAttribute("isRedirect", true);
		return view;
	}

}

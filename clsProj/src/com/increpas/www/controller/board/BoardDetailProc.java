package com.increpas.www.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.BoardDAO;
import com.increpas.www.util.PageUtil;
import com.increpas.www.vo.BoardVO;

public class BoardDetailProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/board/boardRelay.jsp";
		if((String) req.getSession().getAttribute("SID") == null) {
			req.setAttribute("isRedirect", true);
			view = "/clsProj/member/login.cls";
			return view;
		}
		
		// 파라미터 받고
		String sno = req.getParameter("bno");
		String strpage = req.getParameter("nowPage");
		// 데이터 가공하고
		int bno = 0;
		try {
			bno = Integer.parseInt(sno);
		} catch(Exception e) {}
		
		// db작업
		BoardDAO bDAO = new BoardDAO();
		BoardVO bVO = bDAO.getContent(bno);
//		System.out.println("bno = " + bVO.getBno());
		
		// 데이터 화면에 심고
		req.setAttribute("nowPage", strpage);
		req.setAttribute("DATA", bVO);
		
		return view;
	}

}

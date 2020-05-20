package com.increpas.www.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.ReBoardDAO;

public class ReBoardDel implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/reBoard/reBoard.cls";
		String reno = req.getParameter("reno");
//		System.out.println(reno);
		int dreno = -1;
		try{
			dreno = Integer.parseInt(reno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		ReBoardDAO rDAO = new ReBoardDAO();
		rDAO.delList(dreno);
		
		req.setAttribute("isRedirect", true);
		return view;
	}

}

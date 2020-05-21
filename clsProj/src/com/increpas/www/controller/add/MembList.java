package com.increpas.www.controller.add;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.AddDAO;
import com.increpas.www.vo.MemberVO;

public class MembList implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/add/memberList.jsp";
		
		AddDAO aDAO = new AddDAO();
		ArrayList<MemberVO> list = aDAO.getList();
		req.setAttribute("LIST", list);
		
		return view;
	}

}

package com.increpas.www.controller.ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.EmpDAO;
import com.increpas.www.vo.EmpVO;

public class EmpList implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/ajax/empList.jsp";
		// 데이터베이스에서 조회해서 데이터 가져온다
		EmpDAO eDAO = new EmpDAO();
		ArrayList<EmpVO> list = eDAO.getList();
		ArrayList<String> color = getColorList();
		
		// 데이터 뷰에 보낸다
		req.setAttribute("LIST", list);
		req.setAttribute("COLOR", color);
		
		// 뷰 부른다
		return view;
	}
	
	public ArrayList<String> getColorList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("w3-red"); 
		list.add("w3-pink"); 
		list.add("w3-purple"); 
		list.add("w3-deep-purple"); 
		list.add("w3-indigo"); 
		list.add("w3-blue"); 
		list.add("w3-cyan"); 
		list.add("w3-aqua"); 
		list.add("w3-teal"); 
		list.add("w3-green"); 
		list.add("w3-light-green"); 
		list.add("w3-lime"); 
		list.add("w3-khaki"); 
		list.add("w3-yellow"); 
		list.add("w3-amber"); 
		list.add("w3-orange"); 
		list.add("w3-deep-orange");
		
		return list;
	}

}

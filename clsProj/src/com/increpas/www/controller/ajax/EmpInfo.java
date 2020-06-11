package com.increpas.www.controller.ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.EmpDAO;
import com.increpas.www.vo.EmpVO;

public class EmpInfo implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", null);
		
		String str = "";
		
		String sno = req.getParameter("eno");
		int eno = 0;
		try {
			eno = Integer.parseInt(sno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		EmpDAO eDAO = new EmpDAO();
		
		/*
		// 데이터베이스에 데이터보내고 결과받고
		EmpVO eVO = eDAO.getInfo(eno);
		
		resp.setCharacterEncoding("UTF-8");
		
		StringBuffer buff = new StringBuffer();
		buff.append("{");
		buff.append("	\"eno\": " + eVO.getEno() + ","); 
		buff.append("	\"name\": \"" + eVO.getName() + "\","); 
		buff.append("	\"mgr\": " + eVO.getMgr() + ","); 
		buff.append("	\"sdate\": \"" + eVO.getSdate() + "\","); 
		buff.append("	\"sal\": " + eVO.getSal() + ","); 
		buff.append("	\"grade\": " + eVO.getGrade() + ","); 
		buff.append("	\"comm\": \"" + eVO.getComm() + "\","); 
		buff.append("	\"dno\": " + eVO.getDno() + ","); 
		buff.append("	\"dname\": \"" + eVO.getDname() + "\","); 
		buff.append("	\"loc\": \"" + eVO.getLoc() + "\"");
		buff.append("}");
//		System.out.println(buff.toString());
		return buff.toString();
		*/
		
		// 부서번호 조회 db작업
		str = eDAO.getDname(eno);
		
		return str;
	}

}

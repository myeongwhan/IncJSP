package com.increpas.www.controller.add;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.dao.AddDAO;
import com.increpas.www.vo.MemberVO;

@WebServlet("/add/getInfo.ck")
public class GetInfo extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터 받고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		
		AddDAO aDAO = new AddDAO();
		MemberVO mVO = aDAO.getInfo(mno);
		resp.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = resp.getWriter();
			pw.println("{");
			pw.println("	\"id\": \"" + mVO.getId() + "\"," );
			pw.println("	\"name\": \"" + mVO.getName() + "\"," );
			pw.println("	\"mail\": \"" + mVO.getMail() + "\"");
			pw.println("}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

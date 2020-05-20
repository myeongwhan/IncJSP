package com.increpas.www.controller.survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.SurveyDAO;
import com.increpas.www.vo.SurveyVO;

public class Survey implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/survey/survey.jsp";
		// 필요한 데이터가 없으므로 질의명령 바로 보내고 결과 받자
		SurveyDAO sDao = new SurveyDAO();
		// 데이터베이스 작업하고 결과받고
		ArrayList<SurveyVO> list = sDao.getPaper();
		
		// sino와 title이 항상 같으니 여기서 바로 뽑아버림
		int sino = list.get(0).getSino();
		int mno = list.get(0).getMno();
		String title = list.get(0).getTitle();
		
		// 데이터 넘겨주고
		req.setAttribute("SINO", sino);
		req.setAttribute("MNO", mno);
		req.setAttribute("TITLE", title);
		req.setAttribute("LIST", list);
		
		return view;
	}

}

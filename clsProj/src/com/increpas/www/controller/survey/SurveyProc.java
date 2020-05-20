package com.increpas.www.controller.survey;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.SurveyDAO;

public class SurveyProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/survey/surveyResult.cls";
		req.setAttribute("isRedirect", true);
		
		String mno = req.getParameter("mno");
		System.out.println(mno);
		
		String id = (String)req.getSession().getAttribute("SID");
		if(id == null || id.length() == 0) {
			view = "/clsProj/survey/survey.cls";	// (에러페이지로 보내질 상황)
			return view;
		}
		
		String strsino = req.getParameter("sino");
		int sino = 0;
		try {
			sino = Integer.parseInt(strsino);
		} catch(Exception e) {
			view = "/clsProj/survey/survey.cls";	// (에러페이지로 보내질 상황)
			return view;
		}
		
		// 설문문항번호를 키값, 선택보기를 value로 가지는 맵을 만든다
		// 먼저 키들만 뽑아온다
		String[] arrsno = req.getParameterValues("sno");
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		for(String tno : arrsno) {
			int no = Integer.parseInt(tno);
			map.put(no, req.getParameter(tno));
		}
		
/*		
		System.out.println(strsino);
		// 첫문항번호
		System.out.println(arrsno[0]);
		// 첫문항 선택데이터
		String strval1 = req.getParameter(arrsno[0]);
		System.out.println(strval1);
 */
		// 데이터가 준비됐으니 db작업을 한다
		SurveyDAO sDao = new SurveyDAO();
		
		// 문항테이블 업데이트작업
		int cnt = sDao.updateCount(map);
		if(cnt != arrsno.length) {
			view = "/clsProj/survey/survey.cls";	// (에러페이지로 보내질 상황)
			return view;
		}
		cnt = sDao.addRecord(sino, id);
		
		view = view + "?sino=" + strsino;
		return view;
	}

}

package com.increpas.www.controller.board;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.ClsController;
import com.increpas.www.dao.BoardDAO;
import com.increpas.www.util.PageUtil;
import com.increpas.www.vo.BoardVO;
import com.increpas.www.vo.FileVO;

public class BoardDetail implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/board/boardDetail.jsp";
		// 할일
		// 파라미터 받고
		BoardVO bVO = new BoardVO();
		
		// 데이터 심고
		bVO.setBno(Integer.parseInt(req.getParameter("bno")));
		bVO.setClick(Integer.parseInt(req.getParameter("click")));
		bVO.setTitle(req.getParameter("title"));
		bVO.setBody(req.getParameter("body"));
		bVO.setName(req.getParameter("name"));
		bVO.setSdate(req.getParameter("sdate"));
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		String[] sbino = req.getParameterValues("bino");
		
		int len = 0;
		try {
			len = sbino.length;
		} catch(Exception e) {}
		

		String[] oriname;
		String[] savename;
//		System.out.println(bVO.getName());
//		System.out.println(Arrays.toString(sbino));
		if(len != 0) {
			oriname = req.getParameterValues("oriname");
			savename = req.getParameterValues("savename");
			for(int i=0; i<len; i++) {
				FileVO fVO = new FileVO();
				fVO.setBino(Integer.parseInt(sbino[i]));
				fVO.setOriname(oriname[i]);
				fVO.setSavename(savename[i]);
				
				list.add(fVO);
			}
			
			bVO.setFile(list);
		}
		
		req.setAttribute("nowPage", req.getParameter("nowPage"));
		req.setAttribute("DATA", bVO);
		
		return view;
	}

}

package com.increpas.www.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import com.increpas.www.DB.CLSDBCP;
import com.increpas.www.sql.BoardSQL;
import com.increpas.www.util.PageUtil;
import com.increpas.www.vo.BoardVO;
import com.increpas.www.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;

public class BoardDAO {
	CLSDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	BoardSQL bSQL;
	
	public BoardDAO() {
		db = new CLSDBCP();
		bSQL = new BoardSQL();
	}
	
	// 총 게시물 수 조회 전담처리 함수
	public int getTotal() {
		int cnt = 0;
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_TOTAL_CNT);
		stmt = db.getStmt(con);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 게시판 등록글 가져오기 전담처리 함수
	public ArrayList<BoardVO> getAllList(PageUtil page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_ALL_LIST);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bVO = new BoardVO();
				bVO.setRno(rs.getInt("rno"));
				bVO.setBno(rs.getInt("bno"));
				bVO.setTitle(rs.getString("title"));
				bVO.setName(rs.getString("name"));
				bVO.setbDate(rs.getDate("bDate"));
				bVO.setbTime(rs.getTime("bDate"));
				bVO.setSdate();
				bVO.setClick(rs.getInt("click"));
				
				list.add(bVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 글 번호로 검색한 해당 글 가져오기 전담처리 함수
	public BoardVO getContent(int bno) {
		BoardVO bVO = new BoardVO();
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.EDIT_CLICK_BRD);
		pstmt = db.getPstmt(con, sql);
		int cnt = 0;
		try {
			pstmt.setInt(1, bno);
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
		}
		if(cnt != 1) {	// 업데이트 안된경우(조회수 안늘어나는 경우)
			System.out.println("## 클릭 수 증가 실패 ##");
		}
		
		sql = bSQL.getSQL(bSQL.SEL_CONT);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			ArrayList<FileVO> list = new ArrayList<FileVO>();
			
			for(int i=0; rs.next(); i++) {
				FileVO fVO = new FileVO();
				if(rs.getInt("bino") != 0) {
					fVO.setBino(rs.getInt("bino"));
					fVO.setOriname(rs.getString("oriname"));
					fVO.setSavename(rs.getString("savename"));
				}
				list.add(fVO);
				if(i != 0) continue;
				
				bVO.setBno(rs.getInt("bno"));
				bVO.setTitle(rs.getString("title"));
				bVO.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));	// 줄바꿈 jsp처리
				bVO.setName(rs.getString("name"));
				bVO.setbDate(rs.getDate("bdate"));
				bVO.setbTime(rs.getTime("bdate"));
				bVO.setSdate();
				bVO.setClick(rs.getInt("click"));
			}
			bVO.setFile(list);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return bVO;
	}
	
	// 게시판 테이블 글 등록 데이터베이스작업 전담처리 함수
	public int addBoard(HashMap<String, String> map) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = bSQL.getSQL(bSQL.ADD_BRD);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("title"));
			pstmt.setString(3, map.get("body"));
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		// 결과 내보내고
		return cnt;
		
	}
	
	// 게시판 파일 정보 등록 전담처리 함수
	public boolean addImgInfo(FileVO fVO) {
		boolean bool = false;
		// 업로드된 파일 개수
		int cnt = 0;	// 업로드된(multiPartRequest) 파일 개수
		int result = 0;	// db에 추가된 행 수
		// 커넥션
		con = db.getCon();
		// sql
		String sql = bSQL.getSQL(bSQL.ADD_BRDIMG);
		// pstmt
		// 데이터 만들고
		MultipartRequest multi = fVO.getMulti();
		
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			pstmt = db.getPstmt(con, sql);
			// 업로드된 파일의 키(name)값을 알아내고
			String key = (String) en.nextElement();
			// 해당 키값을 가지고 있는 파일 이름을 알아낸다
			String oriname = multi.getOriginalFileName(key);
			if(oriname == null || oriname.length() == 0) {
				// 이 경우는 파일을 선택하지 않은 경우이므로 다음 회차로 진행한다
				continue;
			}
			
			String savename = multi.getFilesystemName(key);
			File file = multi.getFile(key);
			long len = file.length();
			try {
				// 질의명령 완성하고
				pstmt.setString(1, fVO.getId());
				pstmt.setString(2, oriname);
				pstmt.setString(3, savename);
				pstmt.setLong(4, len);
				
				cnt++;
				// 보내고 결과받고
				result += pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(pstmt);
			}
		}
//		System.out.println(cnt);
//		System.out.println(result);
//		System.out.println(bool);
		db.close(con);
		if(cnt == result) {
			bool = true;
		}
//		System.out.println("*"+cnt);
//		System.out.println("*"+result);
//		System.out.println("*"+bool);
		// 결과 내보내고
		return bool;
		
	}
	
}

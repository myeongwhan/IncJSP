package com.increpas.www.dao;

import com.increpas.www.DB.*;
import com.increpas.www.sql.*;
import com.increpas.www.vo.*;

import java.sql.*;
import java.util.*;

public class GBoardDAO {
	CLSDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	GBoardSQL gSQL;
	GBoardVO gVO;

	public GBoardDAO() {
		db = new CLSDBCP();
		gSQL = new GBoardSQL();
	}
	
	// 방명록 조회 전담처리 함수
	public ArrayList<GBoardVO> getGList(){
		ArrayList<GBoardVO> list = new ArrayList<GBoardVO>();
		
		// 커넥션 가져오고
		con = db.getCon();
		
		// 질의명령 가져오고
		String sql = gSQL.getSQL(gSQL.SEL_LIST); 
		
		// stmt 가져오고
		stmt = db.getStmt(con);
		try {
			// 질의명령 stmt에 실어 보내고 결과받고
			rs = stmt.executeQuery(sql);
			// 하나씩 꺼내서 VO에 담고
			while(rs.next()) {
				GBoardVO vo = new GBoardVO();
				vo.setGno(rs.getInt("gno"));
				vo.setGmno(rs.getInt("gmno"));
				vo.setId(rs.getString("id"));
				vo.setBody(rs.getString("body"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setgDate(rs.getDate("gdate"));
				vo.setgTime(rs.getTime("gdate"));
				vo.setsDate();
				// list에 vo담고
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		// list 내보내고
		return list;
	}
	
	// 작성글 카운트조회 db작업 처리 함수
	public int getCnt(String id) {
		int cnt = 0;
		// 커넥션 가져오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = gSQL.getSQL(gSQL.SEL_ID_CNT);
		// pstmt 가져오고
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setString(1, id);
			// 질의명령 보내고
			rs = pstmt.executeQuery();
			// 결과 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 내보내고
		return cnt;
	}
	
	// 아바타 파일 이름 조회 전담 처리함수
	public String getAvt(String id) {
		String avt = "";
		
		// 커넥션
		con = db.getCon();
		// sql
		String sql = gSQL.getSQL(gSQL.SEL_AVT);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// sql완성
			pstmt.setString(1, id);
			// 보내고 결과받고
			rs = pstmt.executeQuery();
			// 데이터 꺼내고
			rs.next();
			avt = rs.getString("avatar");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return avt;
	}
	
	// 방명록 데이터베이스 입력 전담 처리 함수
	public int addData(String id, String body) {
		int cnt = 0;
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.ADD_DATA);
		
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, body);
			rs = pstmt.executeQuery();
			
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}

}

package com.increpas.www.dao;

/**
 * 이 클래스는 댓글게시판 관련 데이터베이스 작업을 처리할 클래스
 * @author	이명환
 * @since	2020.05.13
 * @version	v.1.0.0
 * 
 */
import java.sql.*;
import java.util.*;

import com.increpas.www.DB.*;
import com.increpas.www.sql.*;
import com.increpas.www.vo.*;

public class ReBoardDAO {
	CLSDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	ReBoardSQL rSQL;

	public ReBoardDAO() {
		// db 준비
		db = new CLSDBCP();
		// sql 준비
		rSQL = new ReBoardSQL();
	}
	
	// 댓글리스트 조회 전담처리 함수
	public ArrayList<ReBoardVO> getAllList() {
		ArrayList<ReBoardVO> list = new ArrayList<ReBoardVO>();
		// con
		con = db.getCon();
		// sql
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		// stmt
		stmt = db.getStmt(con);
		try {
			// sql보내고 결과받고
			rs = stmt.executeQuery(sql);
			// 결과에서 데이터 꺼내서 vo에 담고
			// ==> 몇 개를 꺼내야 될지 모르므로 반복해서 꺼냄
			while(rs.next()) {
				ReBoardVO rVO = new ReBoardVO();
				rVO.setReno(rs.getInt("reno"));
				rVO.setMno(rs.getInt("mno"));
				rVO.setId(rs.getString("id"));
				rVO.setAvatar(rs.getString("avatar"));
				rVO.setBody(rs.getString("body"));
				rVO.setBody();	// (오버로딩했던)줄바꿈기호처리
				rVO.setReDate(rs.getDate("redate"));
				rVO.setReTime(rs.getTime("redate"));
				rVO.setsDate();	// 원하는 형식의 date 변환
				rVO.setStep(rs.getInt("step"));
				
				// vo리스트에 담고
				list.add(rVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		// 리스트 내보내고
		return list;
	}
	
	// 댓글리스트 조회(페이지) 전담처리 함수
	public ArrayList<ReBoardVO> getAllList(int startCont, int endCont) {
		ArrayList<ReBoardVO> list = new ArrayList<ReBoardVO>();
		// con
		con = db.getCon();
		// sql
		String sql = rSQL.getSQL(rSQL.SEL_LIST);
//		System.out.println("************* sql : " + sql);
		// stmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setInt(1, startCont);
			pstmt.setInt(2, endCont);
			// sql보내고 결과받고
			rs = pstmt.executeQuery();
			// 결과에서 데이터 꺼내서 vo에 담고
			// ==> 몇 개를 꺼내야 될지 모르므로 반복해서 꺼냄
			while(rs.next()) {
				ReBoardVO rVO = new ReBoardVO();
				rVO.setReno(rs.getInt("reno"));
				rVO.setMno(rs.getInt("mno"));
				rVO.setId(rs.getString("id"));
				rVO.setAvatar(rs.getString("avatar"));
				rVO.setBody(rs.getString("body"));
				rVO.setBody();	// (오버로딩했던)줄바꿈기호처리
				rVO.setReDate(rs.getDate("redate"));
				rVO.setReTime(rs.getTime("redate"));
				rVO.setsDate();	// 원하는 형식의 date 변환
				rVO.setStep(rs.getInt("step"));
				
				// vo리스트에 담고
				list.add(rVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// 리스트 내보내고
		return list;
	}
	
	// 댓글 입력 db작업 전담처리 함수
	public int insertReply(String sid, String body, int upno) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = rSQL.getSQL(rSQL.ADD_REPL);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		
		try {
			// 질의명령 완성
			pstmt.setString(1, sid);
			pstmt.setString(2, body);
			pstmt.setInt(3, upno);
			
			// (insert와 update는 rs가 안 쓰임. select할때만 쓰임)
			// 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 데이터 내보내고
		return cnt;
	}
	
	// 댓글수정 전담처리 함수
	public ArrayList<ReBoardVO> upList(String body, int reno){
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.UP_LIST);
		ArrayList<ReBoardVO> list = new ArrayList<ReBoardVO>();
		
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, body);
			pstmt.setInt(2, reno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReBoardVO vo = new ReBoardVO();
				vo.setBody(rs.getString("body"));
				vo.setReno(rs.getInt("reno"));
				
				list.add(vo);
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
	
	// 댓글삭제 전담처리 함수
	public int delList(int reno) {
		// 커넥션
		con = db.getCon();
		// sql
		String sql = rSQL.getSQL(rSQL.DEL_LIST);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		int cnt = 0 ;	// 변경 행 수
		try {
			// reno가 -1이란건 상위글번호가 없다는것
			// 따라서 reno 컬럼에 null로 보내줘야댐
			if(reno == -1) {
				pstmt.setInt(1, (Integer) null);
			}else {
				pstmt.setInt(1, reno);
			}
			cnt = pstmt.executeUpdate();	// update명령이므로 executeUpdate
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 전체게시글 전담처리 함수
	public int getTotalCount() {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = rSQL.getSQL(rSQL.SEL_ALL_CNT);
		// stmt
		stmt = db.getStmt(con);
		try {
			// sql 보내고 결과받고
			rs = stmt.executeQuery(sql);
			// 데이터 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		// 데이터 내보내고
		return cnt;
	}

}

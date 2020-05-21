package com.increpas.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.www.DB.CLSDBCP;
import com.increpas.www.sql.AddSQL;
import com.increpas.www.vo.MemberVO;

public class AddDAO {
	CLSDBCP db;
	AddSQL aSQL;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public AddDAO() {
		db = new CLSDBCP();
		aSQL = new AddSQL();
	}
	
	// 회원리스트 가져오기
	public ArrayList<MemberVO> getList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		con = db.getCon();
		String sql = aSQL.getSQL(aSQL.SEL_NAME_MNO);
		stmt = db.getStmt(con);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				
				list.add(mVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 정보 가져오기
	public MemberVO getInfo(int mno) {
		MemberVO mVO = new MemberVO();
		con = db.getCon();
		String sql = aSQL.getSQL(aSQL.SEL_INFO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			mVO.setId(rs.getString("id"));
			mVO.setName(rs.getString("name"));
			mVO.setMail(rs.getString("mail"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return mVO;
	}

}

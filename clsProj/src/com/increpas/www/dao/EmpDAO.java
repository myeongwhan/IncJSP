package com.increpas.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.www.DB.CLSDBCP;
import com.increpas.www.sql.EmpSQL;
import com.increpas.www.vo.EmpVO;

public class EmpDAO {
	CLSDBCP db;
	EmpSQL eSQL;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public EmpDAO() {
		db = new CLSDBCP();
		eSQL = new EmpSQL();
	}
	
	// 회원 리스트 가져오기 전담처리 함수
	public ArrayList<EmpVO> getList() {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		con = db.getCon();
		String sql = eSQL.getSQL(eSQL.SEL_EMP_LIST);
		stmt = db.getStmt(con);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO eVO = new EmpVO();
				eVO.setEno(rs.getInt("eno"));
				eVO.setName(rs.getString("name"));
				
				list.add(eVO);
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
	
	// 사원번호로 사원정보를 가져오는 전담처리 함수
	public EmpVO getInfo(int eno) {
		EmpVO eVO = new EmpVO();
		con = db.getCon();
		String sql = eSQL.getSQL(eSQL.SEL_EMP_INFO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, eno);
			rs = pstmt.executeQuery();
			rs.next();
			eVO.setEno(rs.getInt("eno"));
			eVO.setName(rs.getString("name"));
			eVO.setMgr(rs.getInt("mgr"));
			eVO.setHdate(rs.getDate("hdate"));
			eVO.setHtime(rs.getTime("hdate"));
			eVO.setSdate();
			eVO.setSal(rs.getInt("sal"));
			eVO.setGrade(rs.getInt("grade"));
			eVO.setComm(rs.getString("comm"));
			eVO.setDno(rs.getInt("dno"));
			eVO.setDname(rs.getString("dname"));
			eVO.setLoc(rs.getString("loc"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return eVO;
	}
	
	// 부서이름 조회 전담처리 함수
	public String getDname(int eno) {
		String str = "";
		
		con = db.getCon();
		String sql = eSQL.getSQL(eSQL.SEL_EMP_DNAME);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, eno);
			rs = pstmt.executeQuery();
			rs.next();
			str = rs.getString("dname");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return str;
	}
	
}

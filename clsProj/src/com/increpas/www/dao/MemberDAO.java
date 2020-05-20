package com.increpas.www.dao;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import com.increpas.www.DB.*;
import com.increpas.www.sql.*;
import com.increpas.www.vo.*;
import com.oreilly.servlet.MultipartRequest;

public class MemberDAO {
	CLSDBCP db;
	MemberSQL mSQL;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public MemberDAO() {
		db = new CLSDBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 DB처리
	public int getCnt(String id, String pw) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
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
	
	// 회원 아이디 카운트조회 db작업 전담 처리 함수
	public int getIdCnt(String id) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setString(1, id);
			// 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 결과에서 데이터 꺼내고
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
	
	// join
	public int addMemb(MemberVO mVO) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// sql
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성
			pstmt.setString(1, mVO.getName());
			pstmt.setString(2, mVO.getId());
			pstmt.setString(3, mVO.getPw());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getGen());
			pstmt.setString(6, mVO.getTel());
			pstmt.setInt(7, mVO.getAno());
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 결과 반환
		return cnt;
	}
	
	// 회원 프로필 데이터베이스 입력 전담처리 함수
	public int addProf(ProfileVO pVO, MultipartRequest multi) {
		int cnt = 0;
		// 전달받은 데이터 완성하고
		setPVO(pVO, multi);
		// 커넥션
		con = db.getCon();
		// sql
		String sql = mSQL.getSQL(mSQL.ADD_PROF);
		// pstmt
		pstmt = db.getPstmt(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setString(1, pVO.getId());
			pstmt.setString(2, pVO.getOriname());
			pstmt.setString(3, pVO.getSavename());
			pstmt.setLong(4, pVO.getLen());
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 결과 반환
		return cnt;
	}
	
	// 업로드하는 파일에 대한 정보를 처리해주는 함수
	public void setPVO(ProfileVO pVO, MultipartRequest multi) {
		/*
			이미 파일은 업로드가 되었지만
			이것은 단순히 서버의 특정 폴더에 파일을 저장한 것이다
			이 파일이 누구의 것인지, 저장이름은 어떻게 되는지 등의 정보는
			전혀 알지 못한다
			따라서 업로드된 파일의 정보는 데이터베이스에 기록해 놓아야
			그 파일의 실제 소유자를 찾을 수 있게 된다
			
			참고]
				multi 가 가진 주요 함수
				==> 자신이 업로드한 파일의 정보를 알려주는 함수
				
				1. getFile("키값")	-	업로드된 파일의 정보를 알려준다
				2. getFilesystemName("키값")	-	업로드된 파일의 실제 저장된 이름을 알려준다(savename)
				3. getOriginalFileName("키값")	-	업로드된 파일의 원래 이름을 알려준다(oriname)
				4. getFileNames()	-	업로드된 파일의 모든 키값을 알려준다
		 */
		String oriname = multi.getOriginalFileName("file");
		String savename = multi.getFilesystemName("file");
		
		File file = multi.getFile("file");
		long len = file.length();
		
		pVO.setOriname(oriname);
		pVO.setSavename(savename);
		pVO.setLen(len);
		
	}

}

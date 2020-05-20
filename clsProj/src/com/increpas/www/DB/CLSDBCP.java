package com.increpas.www.DB;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class CLSDBCP {
	public DataSource ds;

	public CLSDBCP() {
		/*
		 	지금 이 클래스를 누군가 new 시키면
		 	context.xml 파일에 등록된 자원을 가지고 오도록 한다
		 	이처럼 context.xml 파일에 등록된 자원을 가지고 오는 기법을
		 	JNDI(Java Naming and Directory Interface) 기법이라고 한다
		 */
		
		try {
			// 1. context.xml 파일에 등록된 자원을 알아낸다
			InitialContext context = new InitialContext();
			// 2. 그 중에서 필요한 자원을 얻어낸다
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/memberDB");
			/*
			 	찾을 이름을 정하는 규칙
			 	
			 		java:/comp/env/(context.xml에서만든)찾을이름
			 		
			 	위 작업이 성공하면 커넥션 풀을 찾았고, 사용할 수 있게 된다
			 */
		} catch (Exception e) {
			System.out.println("!!! 커넥션 풀 가져오기 실패 !!!");
		}
	}
	
	// Connection
	public Connection getCon() {
		Connection con = null;
		try {
			// 커넥션 풀을 관리하는 DataSource에서 꺼내온다
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// Statement
	public Statement getStmt(Connection con) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	// PreparedStatement
	public PreparedStatement getPstmt(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public void close(Object o) {
		try {
			if(o instanceof PreparedStatement) {
				((PreparedStatement)o).close();
			} else if(o instanceof Statement) {
				((Statement)o).close();
			} else if(o instanceof Connection) {
				((Connection)o).close();
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

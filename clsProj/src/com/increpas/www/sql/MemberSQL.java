package com.increpas.www.sql;

public class MemberSQL {
	public final int SEL_LOGIN = 111;
	public final int SEL_ID_CNT = 112;
	
	public final int ADD_MEMB = 301;
	public final int ADD_PROF = 302;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LOGIN:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			buff.append("	and pw = ? ");
			break;
		case SEL_ID_CNT:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case ADD_MEMB:
			buff.append("INSERT INTO "); 
			buff.append("    member(mno, name, id, pw, mail, gen, tel, ano) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(mno +1), 1000) FROM member), "); 
			buff.append("    ?, ?, ?, ?, ?, ?, ? "); 
			buff.append(") ");
			break;
		case ADD_PROF:
			buff.append("INSERT INTO "); 
			buff.append("    profile(pno, p_mno, oriname, savename, len) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(pno)+1, 1000) FROM profile), "); 
			buff.append("    (SELECT mno FROM member WHERE id = ? ), "); 
			buff.append("    ?, ?, ? "); 
			buff.append(") ");
			break;
		}
		return buff.toString();
	}
}

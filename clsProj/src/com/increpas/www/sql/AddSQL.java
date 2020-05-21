package com.increpas.www.sql;

public class AddSQL {
	public final int SEL_NAME_MNO = 1001;
	public final int SEL_INFO = 1002;
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_NAME_MNO:
			buff.append("SELECT ");
			buff.append("	mno, name ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			
			break;
		case SEL_INFO:
			buff.append("SELECT ");
			buff.append("	id, name, mail ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	mno = ? ");
			
			break;
		}
		
		return buff.toString();
	}
}

package com.increpas.www.sql;

public class BoardSQL {
	public final int SEL_ALL_LIST = 1001;
	
	public final int EDIT_CLICK_BRD = 2001;
	
	public final int ADD_BRD = 3001;
	public final int ADD_BRDIMG = 3002;
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_ALL_LIST:
			buff.append("select "); 
			buff.append("    bno, title, bmno, bdate, click "); 
			buff.append("from "); 
			buff.append("    board "); 
			
			break;
		case EDIT_CLICK_BRD:
			buff.append("UPDATE "); 
			buff.append("    board "); 
			buff.append("SET "); 
			buff.append("    click = click + 1 "); 
			buff.append("WHERE "); 
			buff.append("    bno = ? ");
			
			break;
		case ADD_BRD:
			buff.append("INSERT INTO "); 
			buff.append("    board(bno, bmno, title, body) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(bno) + 1, 1000) FROM board), "); 
			buff.append("    (SELECT mno FROM member WHERE id = ?),  "); 
			buff.append("    ?, ? "); 
			buff.append(") ");
			
			break;
		case ADD_BRDIMG:
			buff.append("INSERT INTO "); 
			buff.append("    brdimage(bino, bi_bno, oriname, savename, len) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(bino) + 1, 1000) FROM brdimage), "); 
			buff.append("    (SELECT MAX(bno) FROM board WHERE bmno = ( "); 
			buff.append("        										SELECT "); 
			buff.append("            										mno "); 
			buff.append("        										FROM "); 
			buff.append("            										member "); 
			buff.append("        										WHERE "); 
			buff.append("            										id = ? "); 
			buff.append("            									) "); 
			buff.append("    ), "); 
			buff.append("    ?, ?, ? "); 
			buff.append(") ");
			
			break;
		}
		
		return buff.toString();
	}
}

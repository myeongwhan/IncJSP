package com.increpas.www.sql;

public class BoardSQL {
	public final int SEL_ALL_LIST = 1001;
	public final int SEL_TOTAL_CNT = 1002;
	public final int SEL_CONT = 1003;
	
	public final int EDIT_CLICK_BRD = 2001;
	
	public final int ADD_BRD = 3001;
	public final int ADD_BRDIMG = 3002;
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_TOTAL_CNT:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	board ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			
			break;
		case SEL_CONT:
			buff.append("SELECT ");
			buff.append("	bno, title, name, body, bdate, click, ");
			buff.append("	NVL(bino, 0) bino, NVL(oriname, 'none') oriname, NVL(savename, 'none') savename ");
			buff.append("FROM ");
			buff.append("	board, brdimage, member ");
			buff.append("WHERE ");
			buff.append("	bmno = mno ");
			buff.append("	AND bno = bi_bno(+) ");
			buff.append("	AND bno = ? ");
			
			break;
		case SEL_ALL_LIST:
			buff.append("SELECT "); 
			buff.append("    rno, bno, title, name, bdate, click "); 
			buff.append("FROM "); 
			buff.append("    ( "); 
			buff.append("    SELECT "); 
			buff.append("        ROWNUM rno, bno, title, name, bdate, click "); 
			buff.append("    FROM "); 
			buff.append("        ( "); 
			buff.append("        SELECT "); 
			buff.append("            bno, title, name, bdate, click "); 
			buff.append("        FROM "); 
			buff.append("            board, member "); 
			buff.append("         WHERE "); 
			buff.append("            board.isshow = 'Y' "); 
			buff.append("            AND bmno = mno "); 
			buff.append("        ORDER BY "); 
			buff.append("            bdate DESC "); 
			buff.append("        ) "); 
			buff.append("    ) "); 
			buff.append("WHERE "); 
			buff.append("    rno BETWEEN ? AND ? ");
			
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

package com.increpas.www.sql;

public class ReBoardSQL {
	public final int SEL_LIST = 1001;
	public final int SEL_ALL_LIST = 2001;
	public final int ADD_REPL = 1002;
	public final int UP_LIST = 1003;
	public final int DEL_LIST = 1004;
	public final int SEL_ALL_CNT = 1005;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LIST:
			buff.append("SELECT ");
			buff.append("    rno, reno, remno mno, id, savename avatar, rebody body, reupno upno, redate, step ");
			buff.append("FROM ");
			buff.append("    (SELECT ");
			buff.append("        ROWNUM RNO, R.* ");
			buff.append("    FROM ");
			buff.append("        (SELECT ");
			buff.append("            reno, remno, rebody, reupno, redate, ");
			buff.append("            (LEVEL -1) step, ");
			buff.append("            id, savename ");
			buff.append("        FROM ");
			buff.append("            reboard re, member m, avatar a ");
			buff.append("        WHERE ");
			buff.append("            re.isShow = 'Y' ");
			buff.append("            AND m.ano = a.ano ");
			buff.append("            AND remno = mno ");
			buff.append("        START WITH ");
			buff.append("            reupno is null ");
			buff.append("        CONNECT BY ");
			buff.append("            PRIOR reno = reupno ");
			buff.append("        ORDER SIBLINGS BY ");	// siblings ==> step이 같을 때 나중에쓴글이 위에옴
			buff.append("            redate DESC) R ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("	rno BETWEEN ? AND ? ");	// startCont, endCont
			break;
		case SEL_ALL_LIST:
			buff.append("SELECT ");
			buff.append("    rno, reno, remno mno, id, savename avatar, rebody body, reupno upno, redate, step ");
			buff.append("FROM ");
			buff.append("    (SELECT ");
			buff.append("        ROWNUM RNO, R.* ");
			buff.append("    FROM ");
			buff.append("        (SELECT ");
			buff.append("            reno, remno, rebody, reupno, redate, ");
			buff.append("            (LEVEL -1) step, ");
			buff.append("            id, savename ");
			buff.append("        FROM ");
			buff.append("            reboard re, member m, avatar a ");
			buff.append("        WHERE ");
			buff.append("            re.isShow = 'Y' ");
			buff.append("            AND m.ano = a.ano ");
			buff.append("            AND remno = mno ");
			buff.append("        START WITH ");
			buff.append("            reupno is null ");
			buff.append("        CONNECT BY ");
			buff.append("            PRIOR reno = reupno ");
			buff.append("        ORDER SIBLINGS BY ");	// siblings ==> step이 같을 때 나중에쓴글이 위에옴
			buff.append("            redate DESC) R ");
			buff.append("    ) ");
			break;
		case SEL_ALL_CNT:	// 삭제 안한 전체 게시글 수
			buff.append("SELECT ");
			buff.append("    count(*) cnt ");
			buff.append("FROM ");
			buff.append("	reboard ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			break;
		case ADD_REPL:
			buff.append("INSERT INTO "); 
			buff.append("    reboard(reno, remno, rebody, reupno) "); 
			buff.append("VALUES( "); 
			buff.append("    (SELECT NVL(MAX(reno)+1, 1000) FROM reboard), "); 
			buff.append("    (SELECT mno FROM member WHERE id = ?), ?, ? ");	// remno, rebody, reupno
			buff.append(") ");
			break;
		case UP_LIST:
			buff.append("UPDATE "); 
			buff.append("    reboard "); 
			buff.append("SET "); 
			buff.append("    rebody = ? "); 
			buff.append("WHERE "); 
			buff.append("    reno = ? ");
			break;
		case DEL_LIST:
			buff.append("UPDATE "); 
			buff.append("    reboard "); 
			buff.append("SET "); 
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    reno = ? ");
			break;
		}
		
		return buff.toString();
	}
}

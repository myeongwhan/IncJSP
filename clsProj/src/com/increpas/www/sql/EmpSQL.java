package com.increpas.www.sql;

public class EmpSQL {
	public final int SEL_EMP_LIST = 1001;
	public final int SEL_EMP_INFO = 1002;
	public final int SEL_EMP_DNAME = 1003;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_EMP_LIST:
			buff.append("SELECT "); 
			buff.append("    empno eno, ename name "); 
			buff.append("FROM "); 
			buff.append("    emp ");
			break;
			
		case SEL_EMP_DNAME:
			buff.append("SELECT "); 
			buff.append("    dname "); 
			buff.append("FROM "); 
			buff.append("    emp, dpt ");
			buff.append("WHERE "); 
			buff.append("    emp.deptno = dpt.deptno ");
			buff.append("    AND empno = ? ");
			break;
			
		case SEL_EMP_INFO:
			buff.append("SELECT "); 
			buff.append("    empno eno, ename name, NVL(mgr, 0) mgr, hiredate hdate, "); 
			buff.append("    sal, grade, NVL(TO_CHAR(comm), 'none') comm, "); 
			buff.append("    emp.deptno dno, dname, loc "); 
			buff.append("FROM "); 
			buff.append("    emp, dpt, salgrade "); 
			buff.append("WHERE "); 
			buff.append("    emp.deptno = dpt.deptno "); 
			buff.append("    AND sal BETWEEN losal AND hisal "); 
			buff.append("    AND empno = ? ");
			break;
		}
		return buff.toString();
	}
}

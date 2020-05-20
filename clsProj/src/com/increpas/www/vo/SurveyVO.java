package com.increpas.www.vo;


/**
 * 이 클래스는 설문조사에 사용할 데이터를 저장할 용도의 클래스
 * @author	이명환
 * @since	2020.05.15
 * @version	v.1.0.0
 *
 */

import java.sql.*;
import java.text.*;
public class SurveyVO {
	private int sino, sno, mno, srvno, sack1, sack2, sack3, sack4, saTotal;
	private String title, sq, sa1, sa2, sa3, sa4, sDate;
	private Date bDate, eDate, inDate;
	private Time bTime, eTime, inTime;
	public int getSino() {
		return sino;
	}
	public void setSino(int sino) {
		this.sino = sino;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getSrvno() {
		return srvno;
	}
	public void setSrvno(int srvno) {
		this.srvno = srvno;
	}
	public int getSack1() {
		return sack1;
	}
	public void setSack1(int sack1) {
		this.sack1 = sack1;
		setSaTotal();
	}
	public int getSack2() {
		return sack2;
	}
	public void setSack2(int sack2) {
		this.sack2 = sack2;
		setSaTotal();
	}
	public int getSack3() {
		return sack3;
	}
	public void setSack3(int sack3) {
		this.sack3 = sack3;
		setSaTotal();
	}
	public int getSack4() {
		return sack4;
	}
	public void setSack4(int sack4) {
		this.sack4 = sack4;
		setSaTotal();
	}
	public int getSaTotal() {
		return saTotal;
	}
	public void setSaTotal() {	// 설문에 참여한 인원 총 수
		this.saTotal = sack1 + sack2 + sack3 + sack4;
	}
	public void setSaTotal(int saTotal) {
		this.saTotal = saTotal;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSq() {
		return sq;
	}
	public void setSq(String sq) {
		this.sq = sq;
	}
	public String getSa1() {
		return sa1;
	}
	public void setSa1(String sa1) {
		this.sa1 = sa1;
	}
	public String getSa2() {
		return sa2;
	}
	public void setSa2(String sa2) {
		this.sa2 = sa2;
	}
	public String getSa3() {
		return sa3;
	}
	public void setSa3(String sa3) {
		this.sa3 = sa3;
	}
	public String getSa4() {
		return sa4;
	}
	public void setSa4(String sa4) {
		this.sa4 = sa4;
	}
	public String getsDate() {
		return sDate;
	}
	public String setsDate(Date d, Time t) {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		return form1.format(d) + " " + form2.format(t);
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Time getbTime() {
		return bTime;
	}
	public void setbTime(Time bTime) {
		this.bTime = bTime;
	}
	public Time geteTime() {
		return eTime;
	}
	public void seteTime(Time eTime) {
		this.eTime = eTime;
	}
	public Time getInTime() {
		return inTime;
	}
	public void setInTime(Time inTime) {
		this.inTime = inTime;
	}
	
}

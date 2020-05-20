package com.increpas.www.vo;

import java.sql.*;
import java.text.*;

public class ReBoardVO {
	private int rno, reno, mno, upno, step;
	private String id, avatar, body, sDate;
	private Date reDate;
	private Time reTime;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getReno() {
		return reno;
	}
	public void setReno(int reno) {
		this.reno = reno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBody() {
		return body;
	}
	public void setBody() {
		this.body = body.replaceAll("\r\n", "<br>");
	}	// 줄바꿈
	public void setBody(String body) {
		this.body = body;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate() {
		String str = "";
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm:ss");
		this.sDate = form1.format(reDate) + " " + form2.format(reTime);
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public Date getReDate() {
		return reDate;
	}
	public void setReDate(Date reDate) {
		this.reDate = reDate;
	}
	public Time getReTime() {
		return reTime;
	}
	public void setReTime(Time reTime) {
		this.reTime = reTime;
	}
	
}

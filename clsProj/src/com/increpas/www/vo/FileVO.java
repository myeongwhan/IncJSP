package com.increpas.www.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.MultipartRequest;

public class FileVO {
	private int bino, mno, bno;
	private long len;
	private String oriname, savename, id, dir, sdate;
	private Date wDate;
	private Time wTime;
	private MultipartRequest multi;
	
	public MultipartRequest getMulti() {
		return multi;
	}
	public void setMulti(MultipartRequest multi) {
		this.multi = multi;
	}
	public int getBino() {
		return bino;
	}
	public void setBino(int bino) {
		this.bino = bino;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		this.sdate = form1.format(wDate) + " " + form2.format(wTime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	public Time getwTime() {
		return wTime;
	}
	public void setwTime(Time wTime) {
		this.wTime = wTime;
	}
	
}

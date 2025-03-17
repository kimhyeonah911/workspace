package com.kh.sugang.model.vo;

public class SugangClass {
	private int Cno; //강의번호
	private String Cname; //이름
	private String Cdepartment; //학과
	private String Ccompletion; // 이수구분
	private int Ccredit; //학점
	
	public SugangClass() {
		super();
	}

	public SugangClass(String cname, String cdepartment, String ccompletion, int ccredit) {
		super();
		Cname = cname;
		Cdepartment = cdepartment;
		Ccompletion = ccompletion;
		Ccredit = ccredit;
	}

	public SugangClass(int cno, String cname, String cdepartment, String ccompletion, int ccredit) {
		super();
		Cno = cno;
		Cname = cname;
		Cdepartment = cdepartment;
		Ccompletion = ccompletion;
		Ccredit = ccredit;
	}

	public SugangClass(String cname, int ccredit) {
		super();
		Cname = cname;
		Ccredit = ccredit;
	}

	public int getCno() {
		return Cno;
	}

	public void setCno(int cno) {
		Cno = cno;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getCdepartment() {
		return Cdepartment;
	}

	public void setCdepartment(String cdepartment) {
		Cdepartment = cdepartment;
	}

	public String getCcompletion() {
		return Ccompletion;
	}

	public void setCcompletion(String ccompletion) {
		Ccompletion = ccompletion;
	}

	public int getCcredit() {
		return Ccredit;
	}

	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}

	@Override
	public String toString() {
		return "강의 번호: " + Cno + ", 이름: " + Cname + ", 학과: " + Cdepartment + ", 이수구분: "
				+ Ccompletion + ", 학점: " + Ccredit;
	}
	
}

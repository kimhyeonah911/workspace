package com.kh.sugang.model.vo;

public class StudentClass extends SugangClass{
	private int no; //학번

	public StudentClass() {
		super();
	}

	public StudentClass(int no, int cno, String cname, String cdepartment, String ccompletion, int ccredit) {
		super(cno, cname, cdepartment, ccompletion, ccredit);
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "학생 번호: " + no;
	}
	
}

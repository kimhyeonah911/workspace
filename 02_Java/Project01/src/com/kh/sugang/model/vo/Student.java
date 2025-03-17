package com.kh.sugang.model.vo;

public class Student {
	private int no; //학번
	private String pw; //비밀번호
	private String name; //이름
	private int age; //나이
	private int grade; //학년
	private String department; //학과
	
	public Student() {
		super();
	}

	public Student(int no, String pw, String name, int age, int grade, String department) {
		super();
		this.no = no;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.department = department;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [no=" + no + ", pw=" + pw + ", name=" + name + ", age=" + age + ", grade=" + grade
				+ ", department=" + department + "]";
	}

	
	
	
}

package com.kh.sugang.controller;
import java.util.ArrayList;
import java.util.List;

import com.kh.sugang.model.vo.Student;
import com.kh.sugang.model.vo.StudentClass;
import com.kh.sugang.model.vo.SugangClass;
import com.kh.sugang.service.SugangService;
import com.kh.sugang.view.SugangMenu;

public class SugangController {
	private SugangService ss = new SugangService();
	
	//강의 추가 메서드(Cname~Credit : 사용자가 입력한 강의 정보들을 매게변수로 받음)
	public void insertClass(String Cname, String Cdepartment, String Ccompletion, int Ccredit) {
		SugangClass c = new SugangClass(Cname, Cdepartment, Ccompletion, Ccredit);
		int result = ss.insertClass(c);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("강의 추가 성공");
		}else {
			new SugangMenu().displayFail("강의 추가 실패");
		}
	}
	
	//강의 번호를 통해 번호를 제외한 정보들을 수정하는 메서드
	public void updateClass(int Cno, String Cname, String Cdepartment, String Ccompletion, int Ccredit) {
		int result = ss.updateClass(Cno, Cname,Cdepartment, Ccompletion, Ccredit);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("강의 수정 성공");
		}else {
			new SugangMenu().displayFail("강의 수정 실패");
		}
	}
	
	//강의 번호로 삭제하는 메서드
	public void deleteClass(int Cno) {
		int result = ss.deleteClass(Cno);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("삭제 성공하였습니다.");
		}else {
			new SugangMenu().displayFail("삭제 실패하였습니다.");
		}
	}
	
	//학생 추가 메서드(no~department : 사용자가 입력한 강의 정보들을 매게변수로 받음)
	public void insertStudent(int no, String pw, String name, int age, int grade, String department) {
		Student s = new Student(no, pw, name, age, grade, department);
		int result = ss.insertStudent(s);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("학생 추가 성공");
		}else {
			new SugangMenu().displayFail("학생 추가 실패");
		}
	}
	
	public boolean loginStudent(int no, String pw) {
		List<Student> stu = new SugangService().selectStudent();
		for(Student stulist : stu) {
			if(stulist.getNo() == no && stulist.getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	//학생이 추가한 강의 정보 출력 메서드
	public void selectClass() {
		ArrayList<SugangClass> list = ss.selectClass();
		
		if(list.isEmpty()) {
			new SugangMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new SugangMenu().displayClassList(list);
		}
	}
	
	//학과 이름으로 검색받아 해당하는 강의들을 출력하는 메서드
	public void searchClass(String Cdepartment) {
		ArrayList<SugangClass> list = ss.searchClass(Cdepartment);
		
		if(list.isEmpty()) {
			new SugangMenu().displayNoData(Cdepartment + "에 해당하는 강의가 없습니다.");
		}else {
			new SugangMenu().displayClassList(list);
		}
	}
	
	/**
	 * 학생이 강의 추가하는 메서드
	 * 학생이 추가한 강의들의 학점이 18을 넘길 경우, 타과 전공과목을 추가할 경우 메뉴화면으로 리턴
	 */
	public void addClass(int stu_no, String Cname) {
		List<StudentClass> stucs = new SugangService().selectStudentClassAll(stu_no); //서버에서가져오기
		List<Student> stu = new SugangService().selectStudent();
		List<SugangClass> sgcs = new SugangService().selectClass();
		int sum = 0;
		
		for(StudentClass stulist : stucs) {
			if(stulist.getNo() == stu_no) {
			sum += stulist.getCcredit();
			}
		}
		
		
		for(SugangClass sgcslist : sgcs) {
			if(sgcslist.getCname().equals(Cname)) {
				if(sum + sgcslist.getCcredit() >18) {
					System.out.println("최대학점은 18학점입니다.");
					return;
					}
				String courseDepartment = sgcslist.getCdepartment();
				for(Student stulist : stu) {
					if(!stulist.getDepartment().equals(courseDepartment) &&
						(sgcslist.getCcompletion().equals("전공 선택") || sgcslist.getCcompletion().equals("전공 필수"))) {
						System.out.println("신청하신 과목은 타학부 전공과목입니다.");
						return;
						}
					
				}
			}
		}
		
		int result = ss.addClass(stu_no, Cname);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("강의 추가 성공");
		}else {
			new SugangMenu().displayFail("강의 추가 실패");
		}
	}
	
	//학생이 추가한 강의 정보(학번 미포함) 가져오는 메서드
	public void selectStudentClass(int stu_no) {
		ArrayList<SugangClass> list = ss.selectStudentClass(stu_no);
		
		if(list.isEmpty()) {
			new SugangMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new SugangMenu().displayClassList(list);
		}
	}
	
	//학생이 추가한 강의 삭제하는 메서드
	public void deleteStudentClass(int stu_no, String Cname) {
		int result = ss.deleteStudentClass(stu_no, Cname);
		
		if(result > 0) {
			new SugangMenu().displaySuccess("삭제 성공하였습니다.");
		}else {
			new SugangMenu().displayFail("삭제 실패하였습니다.");
		}
	}

}

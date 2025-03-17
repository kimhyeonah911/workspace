package com.kh.sugang.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.sugang.controller.SugangController;
import com.kh.sugang.model.vo.StudentClass;
import com.kh.sugang.model.vo.SugangClass;
import com.kh.sugang.service.SugangService;

public class SugangMenu {
	private Scanner sc = new Scanner(System.in);
	private SugangController sgc = new SugangController();
	
	//메인화면
	public void mainMenu() {
		while(true) {
			System.out.println("=========수강 신청 프로그램=========");
			System.out.println("1. 강의 추가");
			System.out.println("2. 강의 수정");
			System.out.println("3. 강의 삭제");
			System.out.println("4. 학생 회원 가입");
			System.out.println("5. 수강 신청");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1:
				insertClass();
				break;
			case 2:
				updateClass();
				break;
			case 3 :
				deleteClass();
				break;
			case 4:
				insertStudent();
				break;
			case 5 :
				sugangPage();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
		
	}
	
	//강의 정보 추가 메서드
	public void insertClass() {
		System.out.println("=========강의 추가=========");
		System.out.print("강의 이름 : ");
		String Cname = sc.nextLine();
		System.out.print("학과 : ");
		String Cdepartment = sc.nextLine();
		System.out.print("이수 구분 : ");
		String Ccompletion = sc.nextLine();
		System.out.print("학점 : ");
		int Ccredit = sc.nextInt();
		sc.nextLine();
		sgc.insertClass(Cname, Cdepartment, Ccompletion, Ccredit);
	}
	
	//강의 정보 수정 메서드
	public void updateClass() {
		System.out.println("=========강의 수정=========");
		selectClass();
		System.out.print("수정할 강의 번호 : ");
		int Cno = sc.nextInt();
		sc.nextLine();
		System.out.print("강의 이름 : ");
		String Cname = sc.nextLine();
		System.out.print("학과 : ");
		String Cdepartment = sc.nextLine();
		System.out.print("이수 구분 : ");
		String Ccompletion = sc.nextLine();
		System.out.print("학점 : ");
		int Ccredit = sc.nextInt();
		sc.nextLine();
		sgc.updateClass(Cno, Cname,Cdepartment, Ccompletion, Ccredit);
	}
	
	//강의 삭제 메서드
	public void deleteClass() {
		selectClass();
		System.out.print("삭제할 강의의 번호를 입력하세요 : ");
		int Cno = sc.nextInt();
		sc.nextLine();
		sgc.deleteClass(Cno);
	}
	
	//학생 정보 추가 메서드
	public void insertStudent() {
		System.out.println("=========학생 추가=========");
		System.out.print("학번 : ");
		int no = sc.nextInt();
		sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("학년 : ");
		int grade = sc.nextInt();
		sc.nextLine();
		System.out.print("학과 : ");
		String department = sc.nextLine();
		sgc.insertStudent(no, pw, name, age, grade, department);
	}
	
	//수강신청 메뉴를 보여주는 메서드
	public void sugangPage() {
		int stu_no;
		while(true) {
			System.out.print("학번을 입력하세요 : ");
			stu_no = sc.nextInt();
			sc.nextLine();
			System.out.print("비밀번호를 입력하세요 : ");
			String pw = sc.nextLine();
			if(sgc.loginStudent(stu_no, pw)) {
				break;
				}
			else {
				System.out.println("입력하신 학번을 찾을 수 없거나 잘못된 비밀번호입니다. 다시 입력해주세요.");
			}
		}
		
		while(true) {
			System.out.println("=========수강 신청=========");
			System.out.println("1. 전체 강의 보기");
			System.out.println("2. 강의 검색");
			System.out.println("3. 강의 신청");
			System.out.println("4. 신청된 강의 보기");
			System.out.println("5. 강의 삭제");
			System.out.println("9. 메인 화면으로");
			System.out.print("메뉴 번호 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1:
				selectClass();
				break;
			case 2:
				searchClass();
				break;
			case 3:
				addClass(stu_no);
				break;
			case 4 :
				selectStudentClass(stu_no);
				break;
			case 5 :
				deleteStudentClass(stu_no);
				break;
			case 9:
				System.out.println("메인 화면으로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	//전체 강의 정보 출력 메서드
	public void selectClass() {
		sgc.selectClass();
	}
	
	//강의 학과 이름으로 해당 강의 정보 검색 메서드
	public void searchClass() {
		System.out.print("검색할 강의의 학과 이름을 입력하세요 : ");
		String Cdepartment = sc.nextLine();
		sgc.searchClass(Cdepartment);
	}
	
	//강의 추가 메서드
	public void addClass(int stu_no) {
		System.out.print("신청할 강의의 이름를 입력하세요 : ");
		String Cname = sc.nextLine();
		sgc.addClass(stu_no, Cname);
	}
	
	//학생이 신청한 강의 정보 출력 메서드
	public void selectStudentClass(int stu_no) {
		List<StudentClass> stucs = new SugangService().selectStudentClassAll(stu_no);
		int sum = 0;
		
		for(StudentClass stulist : stucs) {
			if(stulist.getNo() == stu_no) {
			sum += stulist.getCcredit();
			}
		}
		System.out.println("신청한 총 학점 : " + sum);
		
		sgc.selectStudentClass(stu_no);
	}
	
	//학생이 신청한 강의 삭제 메서드
	public void deleteStudentClass(int stu_no) {
		sgc.selectStudentClass(stu_no);
		System.out.print("삭제할 강의의 이름를 입력하세요 : ");
		String Cname = sc.nextLine();
		sgc.deleteStudentClass(stu_no, Cname);
	}
	
	public void displaySuccess(String message) {
		System.out.println(message);
	}
	
	public void displayFail(String message) {
		System.out.println(message);
	}
	
	public void displayNoData(String message) {
		System.out.println(message);
	}
	
	public void displayClassList(ArrayList<SugangClass> list) {
		for(SugangClass sclist : list) {
			System.out.println(sclist);
		}
	}
	
}

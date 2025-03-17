package com.kh.jdbc.view;
import java.util.ArrayList;
import java.util.Scanner;
import com.kh.jdbc.controller.MemberController;
/*
 * View : 사용자와 상호작용을 하는 객체 -> 입력 및 출력
 */
import com.kh.jdbc.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	/*
	 * 사용자가 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("=========회원 관리 프로그램=========");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 정보 변경");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 이름으로 키워드 검색");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1:
				insertMember();
				break;
			case 2:
				mc.selectList();
				break;
			case 3:
				UpdateMember();
				break;
			case 4:
				//아이디 입력받아서 Member 삭제 deleteMember
				deleteMember();
				break;
			case 5:
				System.out.println("=========이름으로 회원 검색=========");
				System.out.print("키워드 입력 : ");
				String keyword = sc.nextLine();
				
				mc.selectByUserName(keyword);
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
		
	}
	public void insertMember() {
		System.out.println("=========회원 추가=========");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		System.out.print("성별(M, F) : ");
		String gender = sc.nextLine();
		System.out.print("나이 : ");
		String age = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		System.out.print("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		mc.inserMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
	
	//----------------응답화면---------------
	/**
	 * 서비스요청 처리 후 성공했을 경우 사용자가 보게 될 화면
	 * message : 객체별 성공 메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스요청 처리 후 실패했을 경우 사용자가 보게 될 화면
	 * message : 객체별 성공 메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	/**
	 * 회원을 모두 조회하는 메서드
	 */
	
	//----------------응답화면---------------
	/**
	 * 조회서비스 요청시 조회결과가 없을 때 사용자가 보게 될 응답화면
	 * message : 조회결과에 대한 응답메세지
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 조회서비스 요청 결과가 여러행일 경우 보게 될 응답화면
	 * list : 조회결과
	 */
	
	public void displayMemberList(ArrayList<Member> list) {
		for(Member m : list) {
			System.out.println(m);
		}
	}
	
	/**
	 * 회원 삭제하는 메서드
	 */
	public void deleteMember() {
		System.out.println("=========회원 삭제=========");
		mc.selectList();
		System.out.print("아이디를 입력해주세요 : ");
		String userId = sc.nextLine();
		mc.deleteMember(userId);
	}
	
	/**
	 * 삭제 서비스 요청 성공시 보게 될 응답화면
	 * message : 성공 메세지
	 */
	public void deleteSuccess(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 삭제 서비스 요청 실패시 보게 될 응답화면
	 * message : 실패 메세지
	 */
	public void deleteFail(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 회원 정보 업데이트 메서드
	 */
	public void UpdateMember() {
		System.out.println("=========회원 수정=========");
		System.out.print("아이디를 입력해주세요 : ");
		String userId = sc.nextLine();
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		System.out.print("변경할 전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();
		System.out.print("변경할 취미 : ");
		String hobby = sc.nextLine();
		mc.updateMember(userId, email, phone, address, hobby);
	}
	
	/**
	 * 수정 서비스 요청 성공시 보게 될 응답화면
	 * message : 성공 메세지
	 */
	public void updateSuccess(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 수정 서비스 요청 실패시 보게 될 응답화면
	 * message : 실패 메세지
	 */
	public void updateFail(String message) {
		System.out.println("\n" + message);
	}
}

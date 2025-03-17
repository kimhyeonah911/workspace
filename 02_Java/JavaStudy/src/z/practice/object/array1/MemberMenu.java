package z.practice.object.array1;

import java.util.Scanner;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc= new MemberController();
	
	public MemberMenu() {
		
	}
	
	public void mainMenu(){
		while(true) {
		System.out.println("최대 등록 가능한 회원 수는 " + mc.SIZE + "명입니다");
		System.out.println("현재 등록된 회원 수는 " + mc.existMemberNum() + "명입니다.");
		if(mc.existMemberNum() != 10) {
			System.out.println("1. 새 회원 등록");
		}else {
			System.out.println("회원 수가 모두 꽉 찼기 때문에 일부 메뉴만 오픈됩니다.");
		}
				System.out.println("2. 회원 검색");
				System.out.println("3. 회원 정보 수정");
				System.out.println("4. 회원 삭제");
				System.out.println("5. 모두 출력");
				System.out.println("9. 끝내기");
				System.out.print("메뉴 번호 : ");
				switch(sc.nextInt()) {
				case 1 :
					insertMember();
					break;
				case 2 :
					 searchMember();
					break;
				case 3 :
					updateMember();
					break;
				case 4 :
					deleteMember();
					break;
				case 5 :
					printAll();
					break;
				case 9 :
					System.out.println("프로그램을 종료합니다.");
					return;
				default :
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					}
				}
			}
	
	public void insertMember() {
		String id, name, password, email;
		char gender;
		int age;
		
		System.out.println("새 회원을 등록합니다.");
		while(true) {
			System.out.print("아이디 : ");
			id = sc.next();
			if(mc.checkId(id) == true) {
				System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
			}else {
				break;
			}
		}
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("비밀번호 : ");
		password = sc.next();
		System.out.print("이메일 : ");
		email = sc.next();
		while(true) {
			System.out.print("성별(m/f) : ");
			gender = sc.next().charAt(0);
			if(gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F') {
				break;
			}else System.out.println("성별을 다시 입력하세요.");
		}
		System.out.print("나이 : ");
		age = sc.nextInt();
		sc.nextLine();
		mc.insertMember(id, name, password, email, gender, age);
	}
	
	public void searchMember() {
		while(true) {
			System.out.println("1. 아이디로 검색하기");
			System.out.println("2. 이름으로 검색하기");
			System.out.println("3. 이메일로 검색하기");
			System.out.println("9. 메인으로 돌아가기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				searchId();
				break;
			case 2 :
				searchName();
				break;
			case 3 :
				searchEmail();
				break;
			case 9 :
				System.out.println("메인으로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void searchId() {
		System.out.print("검색할 아이디 : ");
		String id = sc.next();
		if(mc.searchId(id) == null) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			System.out.println("찾으신 회원 조회 결과입니다.");
			System.out.println(mc.searchId(id));
		}
	}
	
	public void searchName() {
		System.out.print("검색할 이름 : ");
		String name = sc.next();
		Member[] searchName = mc.searchName(name);
		if(searchName[0] == null) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			System.out.println("찾으신 회원 조회 결과입니다.");
			for(Member member : searchName) {
				if(member == null) {
					break;
				}
				System.out.println(member.inform());
			}
		}
	}
	
	public void searchEmail() {
		System.out.print("검색할 이메일 : ");
		String email = sc.next();
		Member[] searchEmail = mc.searchEmail(email);
		if(searchEmail[0] == null) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			System.out.println("찾으신 회원 조회 결과입니다.");
			for(Member member : searchEmail) {
				if(member == null) {
					break;
				}
				System.out.println(member.inform());
			}
		}
	}
	
	public void updateMember() {
		while(true) {
			System.out.println("1. 비밀번호 수정하기");
			System.out.println("2. 이름 수정하기");
			System.out.println("3. 이메일 수정하기");
			System.out.println("9. 메인으로 돌아가기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				updatePassword();
				break;
			case 2 :
				updateName();
				break;
			case 3 :
				updateEmail();
				break;
			case 9 :
				System.out.println("메인으로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void updatePassword() {
		System.out.print("수정할 회원의 아이디 : ");
		String id = sc.next();
		System.out.print("수정할 비밀번호 : ");
		String password = sc.next();
		Boolean updatePassword = mc.updatePassword(id, password);
		if(updatePassword = true) {
			System.out.println("수정이 성공적으로 되었습니다.");
		}else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
		
	}

	public void updateName() {
		System.out.print("수정할 회원의 아이디 : ");
		String id = sc.next();
		System.out.print("수정할 이름 : ");
		String name = sc.next();
		Boolean updateName = mc.updateName(id, name);
		if(updateName = true) {
			System.out.println("수정이 성공적으로 되었습니다.");
		}else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
	}

	public void updateEmail() {
		System.out.print("수정할 회원의 아이디 : ");
		String id = sc.next();
		System.out.print("수정할 이메일 : ");
		String email = sc.next();
		Boolean updateEmail = mc.updateEmail(id, email);
		if(updateEmail = true) {
			System.out.println("수정이 성공적으로 되었습니다.");
		}else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
	}
	
	public void deleteMember() {
		while(true) {
			System.out.println("1. 특정 회원 삭제하기");
			System.out.println("2. 모든 회원 삭제하기");
			System.out.println("9. 메인으로 돌아가기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				deleteOne();
				break;
			case 2 :
				deleteAll();
				break;
			case 9 :
				System.out.println("메인으로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	public void deleteOne() {
		System.out.print("삭제할 회원의 아이디 : ");
		String id = sc.next();
		System.out.print("정말 삭제하시겠습니까?(y/n)");
		if(sc.next().charAt(0)=='y' || sc.next().charAt(0)=='Y') {
			boolean deleteOne = mc.delete(id);
			if(deleteOne = true) {
				System.out.println("성공적으로 삭제하였습니다.");
			}else {
				System.out.println("존재하지 않는 아이디입니다.");
			}
		}else {
			
		}
		
	}
	
	public void deleteAll() {
		System.out.print("정말 삭제하시겠습니까?(y/n)");
		if(sc.next().charAt(0)=='y' || sc.next().charAt(0)=='Y') {
			mc.delete();
			System.out.println("성공적으로 삭제하였습니다.");
		}else {
			
		}
	}
	
	public void printAll() {
		Member[] m = mc.printAll();
		if(m[0] == null) {
			System.out.println("저장된 회원이 없습니다.");
			}else {
				for(int i=0; i<mc.SIZE; i++) {
					if(m[i]==null) {
						break;
					}else {
						System.out.println(m[i].inform());
					}
					
			}
		}
	}
}

package z.practice.override;

import java.util.Scanner;

public class LibraryMenu {
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("성별 : ");
		char gender = sc.next().toUpperCase().charAt(0);
		Member mem = new Member(name, age, gender);
		lc.insertMember(mem);
		
		while(true) {
			System.out.println("");
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체 조회");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여하기");
			System.out.println("5. 도서 추가하기");
			System.out.println("6. 도서 삭제하기");
			System.out.println("9. 프로그램 종료하기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				System.out.println(lc.myInfo());
				break;
			case 2 :
				selectAll();
				break;
			case 3 :
				searchBook();
				break;
			case 4 :
				rentBook();
				break;
			case 5 :
				insertBook();
				break;
			case 6 :
				deleteBook();
				break;
			case 9 :
				System.out.println("프로그램이 종료됩니다.");
				return;
			default :
				System.out.println("다시 입력해주세요.");
			}
		}
		
	}
	
	public void selectAll() {
		Book[] bList = lc.selectAll();
		for(int i=0; i<bList.length && bList[i] != null; i++) {
			System.out.println(i + "번 도서 : " + bList[i].toString());
		}
	}
	
	public void searchBook() {
		System.out.println("검색할 제목 키워드 : ");
		String keyword = sc.next();
		Book[] searchList = lc.searchBook(keyword);
		if(searchList == null) {
			System.out.println("도서 목록이 없습니다..");
		}else {
			for(int i=0; i<searchList.length&&searchList[i] != null; i++) {
				System.out.println(searchList[i].toString());
			}
		}
	}
	
	public void rentBook() {
		selectAll();
		System.out.print("대여할 도서 번호 선택 : ");
		int result = lc.rentBook(sc.nextInt());
		
		switch(result) {
		case 0 :
			System.out.println("성공적으로 대여되었습니다.");
			break;
		case 1 :
			System.out.println("나이 제한으로 대여 불가능입니다.");
			break;
		case 2 :
			System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요.");
		}
	}
	
	public void insertBook() {
		int num;
		while(true) {
			while(true) {
				System.out.println("어떤 도서를 추가하시겠습니까?");
				System.out.println("1. 만화책 2. 요리책");
				num = sc.nextInt();
				if(num != 1 && num != 2) {
					System.out.println("다시 입력해주세요.");
				}else break;
			}
			System.out.print("제목 : ");
			String title = sc.next();
			System.out.print("저자 : ");
			String author = sc.next();
			System.out.print("출판사 : ");
			String publisher = sc.next();
			if(num == 1) {
				System.out.print("제한 나이 : ");
				int accessAge = sc.nextInt();
				lc.insertAniBook(title, author, publisher, accessAge);
			}else if(num == 2) {
				System.out.print("요리학원 쿠폰 유뮤(true/false) : ");
				boolean coupon = sc.nextBoolean();
				lc.insertCookBook(title, author, publisher, coupon);
			}
			if(lc.countBook() == 10) {
				System.out.println("도서 개수가 가득 차서 도서 추가가 불가능합니다. 메인으로 돌아갑니다.");
				return;
			}else {
				while(true) {
					System.out.println("계속하시겠습니까?(y/n) : ");
					char ch = sc.next().toUpperCase().charAt(0);
					if(ch == 'Y') {
						break;
					}else if(ch == 'N') {
						System.out.println("메인메뉴로 돌아갑니다.");
						return;
					}else {
						System.out.println("다시 입력해주세요.");
					}
				}
			}
		}
	}
	
	public void deleteBook() {
		selectAll();
		System.out.println("삭제할 도서 번호 선택 : ");
		lc.deleteBook(sc.nextInt());
	}
	
}

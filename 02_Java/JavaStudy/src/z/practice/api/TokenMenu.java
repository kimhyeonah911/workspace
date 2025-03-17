package z.practice.api;

import java.util.Scanner;

public class TokenMenu {
	private Scanner sc = new Scanner(System.in);
	private TokenController tc = new TokenController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("1. 지정 문자열");
			System.out.println("2. 입력 문자열");
			System.out.println("3. 프로그램 끝내기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				tokenMenu();
				break;
			case 2 :
				inputMenu();
				break;
			case 3 :
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void tokenMenu() {
		String str = "J a v a P r o g r a m "; 
		System.out.println("토큰 전 처리 글자 : " + str);
		System.out.println("토큰 처리 전 개수 : " + str.length());
		String afterstr = tc.afterToken(str);
		System.out.println("토큰 처리 후 글자 : " + afterstr);
		System.out.println("토큰 처리 후 개수 : " + afterstr.length());
		System.out.println("모두 대문자로 변환 : " + afterstr.toUpperCase());
	}
	
	public void inputMenu() {
		System.out.print("문자열을 입력하세요 : ");
		String input = sc.next();
		sc.nextLine();
		System.out.println("첫 글자 대문자 : " + tc.firstCap(input));
		System.out.print("찾을 문자 하나를 입력하세요 : ");
		char one = sc.next().charAt(0);
		System.out.println(one + " 문자가 들어간 개수 : " + tc.findChar(input, one));
	}
}

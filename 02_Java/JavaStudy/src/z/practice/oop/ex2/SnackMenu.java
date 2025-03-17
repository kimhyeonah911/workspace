package z.practice.oop.ex2;

import java.util.Scanner;

public class SnackMenu {
	private Scanner sc = new Scanner(System.in);
	private SnackController scr = new SnackController();
	
	public void menu() {
		System.out.println("스낵류를 입력하세요.");
		System.out.print("종류 : ");
		String kind = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("맛 : ");
		String flavor = sc.next();
		System.out.print("개수 : ");
		int numOf = sc.nextInt();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		String Snack = scr.saveData(kind, name, flavor, numOf, price);
		System.out.println(Snack);
		
		char yn;
		while(true) {
			System.out.println("저장한 정보를 확인하시겠습니까?(y/n) : ");
			yn = sc.next().charAt(0);
			if(yn == 'y' || yn == 'Y') {
				System.out.println(scr.confirmData());
			}else if(yn == 'n' || yn == 'N') {
				
			}else {
				System.out.println("다시 입력해주세요.");
				return;
			}
			
		}
		
	}
}

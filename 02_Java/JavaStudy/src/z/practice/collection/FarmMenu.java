package z.practice.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class FarmMenu {
	private Scanner sc = new Scanner(System.in);
	private FarmController fc = new FarmController();
	
	public void mainMenu() {
		System.out.println("========== KH 마트 ==========");
		while(true) {
			System.out.println("******* 메인 메뉴 *******");
			System.out.println("1. 직원 메뉴");
			System.out.println("2. 손님 메뉴");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1 :
				adminMenu();
				break;
			case 2 :
				customerMenu();
				break;
			case 9:
				System.out.println("프로그램 종료");
				return;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void adminMenu() {
		while(true) {
			System.out.println("******* 직원 메뉴 *******");
			System.out.println("1. 새 농산물 추가");
			System.out.println("2. 종류 삭제");
			System.out.println("3. 수량 수정");
			System.out.println("4. 농산물 목록");
			System.out.println("9. 메인으로 돌아가기");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1 :
				addNewKind();
				break;
			case 2 :
				removeKind();
				break;
			case 3 :
				changeAmount();
				break;
			case 4:
				printFarm();
				break;
			case 9:
				System.out.println("메인 메뉴로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void customerMenu() {
		System.out.println("현재 KH마트 농산물 수량 ");
		printFarm();
		while(true) {
			System.out.println("******* 고객 메뉴 *******");
			System.out.println("1. 농산물 사기");
			System.out.println("2. 농산물 빼기");
			System.out.println("3. 구입한 농산물 보기");
			System.out.println("9. 메인으로 돌아가기");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1 :
				buyFarm();
				break;
			case 2 :
				removeFarm();
				break;
			case 3 :
				printBuyFarm();
				break;
			case 9:
				System.out.println("메인 메뉴로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void addNewKind() {
		int num = 0;
		while(true) {
			System.out.println("1. 과일 / 2. 채소 / 3. 견과");
			System.out.print("추가할 종류 번호 : ");
			num = sc.nextInt();
			sc.nextLine();
			if(num != 1 && num != 2 && num != 3) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			break;
		}
		
		while(true) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("수량 : ");
			int amount = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 :
				Fruit f = new Fruit("fruit", name);
				if(fc.addNewKind(f, amount)) {
					System.out.println("새 농산물이 추가되었습니다.");
					return;
				}else {
					System.out.println("새 농산물 추가에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2 :
				Vegetable v = new Vegetable("vegetable", name);
				if(fc.addNewKind(v, amount)) {
					System.out.println("새 농산물이 추가되었습니다.");
					return;
				}else {
					System.out.println("새 농산물 추가에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 3 :
				Nut n = new Nut("nut", name);
				if(fc.addNewKind(n, amount)) {
					System.out.println("새 농산물이 추가되었습니다.");
					return;
				}else {
					System.out.println("새 농산물 추가에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			}
		}
	}
	
	public void removeKind() {
		int num = 0;
		while(true) {
			System.out.println("1. 과일 / 2. 채소 / 3. 견과");
			System.out.print("삭제할 종류 번호 : ");
			num = sc.nextInt();
			sc.nextLine();
			if(num != 1 && num != 2 && num != 3) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			break;
		}
		
		while(true) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			switch(num) {
			case 1 :
				Fruit f = new Fruit("fruit", name);
				if(fc.removeKind(f)) {
					System.out.println("농산물 삭제에 성공하였습니다.");
					return;
				}else {
					System.out.println("농산물 삭제에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2 :
				Vegetable v = new Vegetable("vegetable", name);
				if(fc.removeKind(v)) {
					System.out.println("농산물 삭제에 성공하였습니다.");
					return;
				}else {
					System.out.println("농산물 삭제에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 3 :
				Nut n = new Nut("nut", name);
				if(fc.removeKind(n)) {
					System.out.println("농산물 삭제에 성공하였습니다.");
					return;
				}else {
					System.out.println("농산물 삭제에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			}
		}
	}
	
	public void changeAmount() {
		int num = 0;
		while(true) {
			System.out.println("1. 과일 / 2. 채소 / 3. 견과");
			System.out.print("수정할 종류 번호 : ");
			num = sc.nextInt();
			sc.nextLine();
			if(num != 1 && num != 2 && num != 3) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			break;
		}
		
		while(true) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("수정할 수량 : ");
			int amount = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 :
				Fruit f = new Fruit("fruit", name);
				if(fc.changeAmount(f, amount)) {
					System.out.println("농산물 수량이 수정되었습니다.");
					return;
				}else {
					System.out.println("농산물 수량 수정에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2 :
				Vegetable v = new Vegetable("vegetable", name);
				if(fc.changeAmount(v, amount)) {
					System.out.println("농산물 수량이 수정되었습니다.");
					return;
				}else {
					System.out.println("농산물 수량 수정에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			case 3 :
				Nut n = new Nut("nut", name);
				if(fc.changeAmount(n, amount)) {
					System.out.println("농산물 수량이 수정되었습니다.");
					return;
				}else {
					System.out.println("농산물 수량 수정에 실패하였습니다. 다시 입력해주세요.");
				}
				break;
			}
		}
	}
	
	public void printFarm() {
		HashMap<Farm, Integer> hMap = fc.printFarm();
		Set<Farm> farm = hMap.keySet();
		for(Farm f : farm) {
			if(f == null) {
				System.out.println("농산물이 없습니다.");
			}
			Integer amuont = hMap.get(f);
			System.out.println(f.toString() + "(" + amuont + "개)");
			
		}
	}
	
	public void buyFarm() {
		int num = 0;
		while(true) {
			System.out.println("1. 과일 / 2. 채소 / 3. 견과");
			System.out.print("구매할 종류 번호 : ");
			num = sc.nextInt();
			sc.nextLine();
			if(num != 1 && num != 2 && num != 3) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			break;
		}
		
		while(true) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			switch(num) {
			case 1 :
				Fruit f = new Fruit("fruit", name);
				if(fc.buyFarm(f)) {
					System.out.println("구매에 성공하였습니다.");
					return;
				}else {
					System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요.");
				}
				break;
			case 2 :
				Vegetable v = new Vegetable("vegetable", name);
				if(fc.buyFarm(v)) {
					System.out.println("구매에 성공하였습니다.");
					return;
				}else {
					System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요.");
				}
				break;
			case 3 :
				Nut n = new Nut("nut", name);
				if(fc.buyFarm(n)) {
					System.out.println("구매에 성공하였습니다.");
					return;
				}else {
					System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요.");
				}
				break;
			}
		}
	}
	
	public void removeFarm() {
		int num = 0;
		while(true) {
			System.out.println("1. 과일 / 2. 채소 / 3. 견과");
			System.out.print("구매 취소할 종류 번호 : ");
			num = sc.nextInt();
			sc.nextLine();
			if(num != 1 && num != 2 && num != 3) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			break;
		}
		
		while(true) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			switch(num) {
			case 1 :
				Fruit f = new Fruit("fruit", name);
				if(fc.removeFarm(f)) {
					System.out.println("구매 취소에 성공하였습니다.");
					return;
				}else {
					System.out.println("구매 목록에 존재하지 않습니다. 다시 입력해주세요.");
				}
				break;
			case 2 :
				Vegetable v = new Vegetable("vegetable", name);
				if(fc.removeFarm(v)) {
					System.out.println("구매 취소에 성공하였습니다.");
					return;
				}else {
					System.out.println("구매 목록에 존재하지 않습니다. 다시 입력해주세요.");
				}
				break;
			case 3 :
				Nut n = new Nut("nut", name);
				if(fc.removeFarm(n)) {
					System.out.println("구매 취소에 성공하였습니다.");
					return;
				}else {
					System.out.println("구매 목록에 존재하지 않습니다. 다시 입력해주세요.");
				}
				break;
			}
		}
	}
	
	public void printBuyFarm() {
		System.out.println(fc.printBuyFarm());
	}

}

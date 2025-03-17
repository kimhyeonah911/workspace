package z.practice.oop.ex4;

import java.util.Scanner;

public class EmployeeMenu {
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	
	public EmployeeMenu() {
		while(true) {
			System.out.println("1.추가 2.수정 3.삭제 4.출력 9.종료");
			System.out.println("메뉴 번호를 누르세요 : ");
			int num = sc.nextInt();
			switch(num) {
			case 1 :
				insertEmp();
				break;
			case 2 :
				updateEmp();
				break;
			case 3:
				deleteEmp();
				break;
			case 4:
				printEmp();
				break;
			case 9 :
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}
	
	public void insertEmp() {
		System.out.print("사원 번호 : ");
		int empNo = sc.nextInt();
		System.out.print("사원 이름 : ");
		String name = sc.next();
		System.out.print("사원 성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("전화 번호 : ");
		String phone = sc.next();
		System.out.println("추가 정보를 더 입력하시겠습니까?(y/n) : ");
		char yn = sc.next().charAt(0);
		if(yn == 'y' || yn == 'Y') {
			System.out.print("사원 부서 : ");
			String dept = sc.next();
			System.out.print("사원 연봉 : ");
			int salary = sc.nextInt();
			System.out.print("보너스 율 : ");
			double bonus = sc.nextDouble();
			ec.add(empNo, name, gender, phone, dept, salary, bonus);
		}else if(yn == 'n' || yn == 'N') {
			ec.add(empNo, name, gender, phone);
		}	
	}
	
	public void updateEmp() {
		while(true) {
			System.out.println("가장 최신의 사원 정보를 수정하게 됩니다.");
			System.out.println("사원의 어떤 정보를 수정하시겠습니까?");
			System.out.println("1.전화번호 2.연봉 3.보너스율 9.돌아가기");
			System.out.println("메뉴 번호를 누르세요");
			int num = sc.nextInt();
			switch(num) {
			case 1:
				System.out.println("수정할 전화번호 : ");
				String phone = sc.next();
				ec.modify(phone);
				break;
			case 2:
				System.out.println("수정할 연봉 : ");
				int salary = sc.nextInt();
				ec.modify(salary);;
				break;
			case 3:
				System.out.println("수정할 보너스율 : ");
				double bonus = sc.nextDouble();
				ec.modify(bonus);;
				break;
			case 9 :
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}
	
	public void deleteEmp() {
		System.out.println("정말 삭제하시겠습니까?(y/n)");
		char yn = sc.next().charAt(0);
		if(yn == 'y' || yn == 'Y') {
			if(ec.remove() != null) {
				System.out.println("데이터 삭제가 실패하였습니다.");
			}else {
				System.out.println("데이터 삭제가 성공하였습니다.");
			}
		}else if(yn == 'n' || yn == 'N') {

		}
	}
	
	public void printEmp() {
		if(ec.inform() != null) {
			System.out.println(ec.inform());
		}else {
			System.out.println("사원 데이터가 없습니다.");
		}
	}
}

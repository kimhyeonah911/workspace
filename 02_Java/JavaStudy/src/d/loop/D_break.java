package d.loop;

import java.util.Scanner;

public class D_break {
	/*
	 * break : 반복문, switch문 안에서 사용되는 분기문
	 * break;가 실행되는 순간 가장 가까운 반복문 또는 switch문을 강제로 탈출한다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*랜덤값(1~100) 발생시키고 그 랜덤값을 출력(단, 랜덤값이 3의 배수일 경우 반복문 종료)
		while(true) {
			int num = (int)(Math.random() * 100 + 1);
			System.out.println(num);
			if(num % 3 ==0) {
				break;
			}
		}
		*/
		
		/*로또 번호 추첨 1~45까지 중 랜덤해서 6개 추출
		for(int i=0; i<6; i++) {
			int num=(int)(Math.random()*45+1);
			System.out.print(num + " ");
			if(i<6) {
				break;
			}
		}
		*/
		
		//사용자에게 문자열을 입력받아 해당 문자열의 길이를 출력하는 프로그램을 계속 반복해라
		//단, 사용자가 입력한 문자열이 exit일 경우 반복문 탈출
		while(true) {
			String str = sc.nextLine();
			if(str.equals("exit")) {
				break;
			}
			System.out.println(str.length());
		}
		
	}
}

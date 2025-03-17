package b.operator;

import java.util.Scanner;

public class Operator5 {
	/*
	 * 논리연산자
	 * 두 개의 논리값을 연산해주는 연산자
	 * 논리연산한 결과도 논리값이 된다.
	 * 
	 * 논리값 && 논리값 : 왼쪽, 오른쪽 조건이 모두 true일 때만 결과가 true이다.
	 * 논리값 || 논리값 : 왼쪽, 오른쪽 두 값 중 하나라도 true라면 결과가 true이다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//사용자에게 숫자를 입력받아 해당 숫자가 1~100 사이값인지 확인
		int num1;
		
		System.out.print("정수 하나 입력 : ");
		num1 = sc.nextInt();
		
		boolean result = (num1 >= 1) && (num1 <= 100);
		
		System.out.println("사용자가 입력한 값은 1 이상 100 이하이다 : " + result);
		
		//사용자에게 알파벳 하나를 입력받아 대소문자를 확인
		System.out.print("문자 하나 입력 : ");
		char ch = sc.nextLine().charAt(0); 
		// 문자열에서 문자 하나를 추출하는 함수 charAt(index)
		
		boolean res1 = (ch >= 'A') && (ch <= 'Z');
		boolean res2 = (ch >= 'a') && (ch <= 'z');
		
		System.out.println("사용자가 입력한 값이 알파벳입니다 : " + (res1 || res2));
		System.out.println("사용자가 입력한 값이 대문자입니다 : " + res1);
		System.out.println("사용자가 입력한 값이 소문자입니다 : " + res2);
	}

}

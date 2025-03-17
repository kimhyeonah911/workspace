package e.array;

import java.util.Scanner;

public class C_Array {
	public static void main(String[] args) {
		/*
		//크기가 10인 정수형 배열 생성
		int[] arr = new int[10];
		
		//반복문을 통해서 0번 인덱스부터 마지막 인덱스까지 모든 값을 1로 초기화
		for(int i=0; i<10; i++) {
			arr[i] = 1;
		}
		
		//반복문을 통해서 0~마지막인덱스까지 모두 출력
		for(int i=0; i<10; i++) {
			System.out.println(arr[i]);
		}
		*/
		
		/*
		//사용자에게 배열의 길이를 입력받아 해당 크기의 문자열 배열을 생성해라(strArr)
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 길이 : ");
		int num = sc.nextInt();
		String[] strArr = new String[num];
		
		//반복문을 활용해서 매번 사용자에게 과일명을 입력받아 그 값을 strArr에 대입해라(0~마지막인덱스)
		for(int i=0; i<strArr.length; i++) {
			System.out.print("좋아하는 과일 : ");
			String str = sc.nextLine();
			strArr[i] = str;
		}
		
		//반복문을 통해 strArr에 있는 모든 값을 출력
		for(int i=0; i<strArr.length; i++) {
			System.out.println(strArr[i]);
		}
		*/
		
		/*
		//사용자에게 문자열 하나 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		
		//char배열을 생성(크기 -> 문자열의 길이)
		char[] chArr = new char[str.length()];
		
		//반복문을 활용하여 해당 문자열에서 각각의 인덱스별 문자를 char배열에 각 인덱스에 담기
		for(int i=0; i<chArr.length; i++) {
			chArr[i] = str.charAt(i);
		}
		
		//char 배열의 모든 index값을 출력
		for(int i=0; i<chArr.length; i++) {
			System.out.println(chArr[i]);
		}
		*/
		
		//사용자에게 배열의 길이를 입력받아 해당 길이의 정수열 배열 arr을 생성한다
		//arr에 모든 인덱스 값에 1~100 사이의 랜덤값을 할당해주고
		//arr의 모든 값을 출력한 후 이 중 짝수인 값의 총 합을 수해서 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		int[] arr = new int[num];
		int sum = 0;
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = (int)(Math.random() * 100) + 1;
		}
		
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		for(int i=0; i<arr.length; i++) {
			if(arr[i] % 2 == 0) {
				sum += arr[i];
			}
		}
		System.out.println("\n짝수의 총 합 : " + sum);
		
	}
}

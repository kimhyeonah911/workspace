package z.practice.io;

import java.io.File;
import java.util.Scanner;

public class FileMenu {
	private Scanner sc = new Scanner(System.in);
	private FileController fc = new FileController();
	private StringBuilder stringBuilder;
	
	public void mainMenu() {
		while(true) {
			System.out.println("***** My Note *****");
			System.out.println("1. 노트 새로 만들기");
			System.out.println("2. 노트 열기");
			System.out.println("3. 노트 열어서 수정하기");
			System.out.println("9. 끝내기");
			System.out.print("메뉴 번호 : ");
			switch(sc.nextInt()) {
			case 1 :
				fileSave();
				break;
			case 2 :
				fileOpen();
				break;
			case 3 :
				fileEdit();
				break;
			case 9 :
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void fileSave() {
		/*
		 * StringBuilder
		 * 가변문자열을 처리하는 클래스이다. 문자열을 수정할 때 새로운 객체를 생성하지 않고 
		 * 내부 버퍼를 이용해서 성능을 최적화한다.(String과 비슷한 역할을 함)
		 * */
		String text = null;
		String fileName = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			System.out.println("파일에 저장할 내용을 입력하세요.");
			System.out.println("ex끝it 이라고 입력하면 종료됩니다.");
			System.out.print("내용 : ");
			text = sc.next();
			sb.append(text);
			if(text.equals("ex끝it")) {
				//파일명이 존재하면서 덮어쓰기를 하지 않겠다고 했을 때 반복한다.
				while(true) {
					System.out.print("저장할 파일 명을 입력해주세요(ex. myFile.txt) : ");
					fileName = sc.next();
					if(fc.checkName(fileName)) {//파일명이 존재할 때
						System.out.println("이미 존재하는 파일입니다. 덮어쓰시겠습니까?(y/n)");
						if(sc.next().toUpperCase().charAt(0) == 'Y') {
							fc.fileSave(fileName, sb);
							return;
						}
					}else {
						fc.fileSave(fileName, sb);
						return;
					}
				}
			}
			sb.append(text);
		}
		
	}
	
	public void fileOpen() {
		System.out.print("열 파일 명 : ");
		String file = sc.next();
		sc.nextLine();
		if(fc.checkName(file)) {
			System.out.println(fc.fileOpen(file).toString());
		}else {
			System.out.println("없는 파일입니다.");
		}
	}
	
	public void fileEdit() {
		System.out.print("수정할 파일 명 : ");
		String file = sc.next();
		sc.nextLine();
		if(fc.checkName(file)) {
			StringBuilder sb = new StringBuilder();
			while(true) {
				System.out.println("파일에 저장할 내용을 입력하세요.");
				System.out.println("ex끝it 이라고 입력하면 종료됩니다.");
				System.out.println("내용 : ");
				String str = sc.next();
				if(str.equals("ex끝it")) {
					fc.fileEdit(file, sb);
					return;
			}sb.append(str);
			
			}
		}else {
			System.out.println("없는 파일입니다.");
		}
	
	}
}

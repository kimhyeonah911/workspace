package z.practice.exception1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterMenu {
	public void menu() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("문자열 : ");
		String str = br.readLine();
		try {
			int count = new CharacterController().countAlpha(str);
			System.out.println(str + "에 포함된 영문자 개수 : " + count);
		} catch (CharCheckException e) {
			e.getMessage();
			e.printStackTrace();
		} 
	}
}

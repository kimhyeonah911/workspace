package z.practice.exception1;

public class CharCheckException extends Exception{

	public CharCheckException() {
		super();
	}

	public CharCheckException(String msg) {
		super("체크할 문자열 안에 공백이 포함되어 있습니다.");
	}
	
	
}

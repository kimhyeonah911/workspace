package z.practice.exception2;

public class NumRangeException extends Exception{

	public NumRangeException() {
		super();
	}

	public NumRangeException(String message) {
		super("1부터 100 사이의 값이 아닙니다.");
	}
	
}

package z.practice.exception2;

public class NumberController {

	public NumberController() {
		super();
	}
	
	public boolean checkDouble(int num1, int num2) throws NumRangeException{
		if(num1 >= 1 && num1 <= 100 && num2 >= 1 && num2 <= 100) {
			for(int i=1; i<=100; i++) {
				if(num1 == num2 * i) {
					return true;
				}else {
					return false;
				}
			}
		}
		throw new NumRangeException();
	}
}

package b.operator;

public class Operator3 {
	/*
	 * 산술연산자
	 * + - * / %
	 * *-/ % > + -
	 * 
	 * 복삽대입연산자
	 * 산술연산자와 대입연산자를 결합해서 사용할 수 있다.
	 * += -= *= /= %=
	 * a = a + 3; -> a += 3;
	 */
	public static void main(String[] args) {
		int a = 5; // 5
		int b = 10; // 10
		int c = (++a) + b; // a = 6 c = 16
		int d = 16 / 6; // 2
		int e = c % a; // 4
		int f = e++; // e = 5 f = 4
		int g = (--b) + (d--); // b = 9 d = 1 g = 11
		int h = 2; // 2
		int i = (a++) + b / (--c / f) * (g-- - d) % (++e + h); 
		// a=7 b=9 c=15 d=1 e=6 f=4 g=10 h=2 i=6+9/(15/4)*(11-1)%(6+2)
		// 6+9/3*10%8 = 6+30%8 = 12
		}

}

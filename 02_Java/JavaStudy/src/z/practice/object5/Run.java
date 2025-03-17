package z.practice.object5;

public class Run {
	public static void main(String[] args) {
		Student st1 = new Student(1, 1, "홍길동", 1.72, '남');
		Student st2 = new Student(2, 2, "홍길이", 1.58, '여');
		Student st3 = new Student(3, 3, "홍길삼", 1.81, '남');
		
		st1.information();
		st2.information();
		st3.information();
		
	}
}

package f.object.ex3;

public class Run {
	public static void main(String[] args) {
		Student st1 = new Student();
		Student st2 = new Student("최지원", 20, 10, 95, 50);
		
		st1.myInfo();
		st2.myInfo();
	}

}

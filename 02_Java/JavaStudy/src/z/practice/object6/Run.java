package z.practice.object6;

public class Run {

	public static void main(String[] args) {
		Book b1 = new Book("홍길동전", "민음사", "허균", 12000, 0.1);
		Book b2 = new Book("소년이 온다", "창비", "한강", 15000, 0.1);
		Book b3 = new Book("넥서스", "김영사", "유발 하라리", 27800, 0.2);
		
		b1.inform();
		b2.inform();
		b3.inform();

	}

}

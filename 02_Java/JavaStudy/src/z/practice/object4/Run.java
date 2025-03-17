package z.practice.object4;

public class Run {
	public static void main(String[] args) {
		Product p1 = new Product("침대", 300000, "시몬스");
		Product p2 = new Product("커피", 12000, "카누");
		Product p3 = new Product("볼펜", 2000, "모나미");
		
		p1.information();
		p2.information();
		p3.information();
		
	}
}

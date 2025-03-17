package z.practice.oop.ex2;

public class SnackController {
	private Snack s = new Snack();

	public SnackController() {
		super();
	}
	
	public String saveData(String kind, String name, String flavor, int numOf, int price) {
		s.setKind(kind);
		s.setName(name);
		s.setFlavor(flavor);
		s.setNumOf(numOf);
		s.setPrice(price);
		Snack s = new Snack(kind, name, flavor, numOf, price);
		return "저장 완료되었습니다";
		
	}
	
	public String confirmData() {
		return s.information();
	}
	
	
}

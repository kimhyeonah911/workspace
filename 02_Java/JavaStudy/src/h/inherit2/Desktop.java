package h.inherit2;

public class Desktop extends Product{
	private boolean allinOne; //일체형 여부

	public Desktop() {
		//상속 관계에서 부모 생성자를 명시하지 않으면 부모의 기본 생성자 호출
		System.out.println("Desktop()");
	}

	public Desktop(String brand, String pCode, String pName, int price, boolean allinOne) {
		super(brand, pCode, pName, price);
		this.allinOne = allinOne;
	}
	
	//오버라이딩 -> 부모 클래스에 있는 메소드에서 내용만 재정의 하는 것
	public String information() {
		return super.information() + " 올인원 : " + allinOne;
	}

	public boolean isAllinOne() {
		return allinOne;
	}

	public void setAllinOne(boolean allinOne) {
		this.allinOne = allinOne;
	}
	
}

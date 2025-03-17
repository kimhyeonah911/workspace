package z.practice.oop.ex3;

public class Human {
	private String name;
	private int price;
	private TV tv = new TV();
	
	public Human() {
		super();
	}
	
	public Human(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public void buy(TV tv) {
		if(this.price >= tv.getPrice()) {
			System.out.println(name + "님 구매내역");
			System.out.println(tv.show());
			this.price = this.price - tv.getPrice();
			System.out.println("남은 잔액 : " + this.price);
		}else {
			System.out.println(this.name + "님 잔액이 부족하여 구매하실 수 없습니다.");
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}

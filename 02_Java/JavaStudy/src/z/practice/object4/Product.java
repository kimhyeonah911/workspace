package z.practice.object4;

public class Product {
	private String pName;
	private int price;
	private String brand;
	
	public Product() {
		super();
	}

	public Product(String pName, int price, String brand) {
		super();
		this.pName = pName;
		this.price = price;
		this.brand = brand;
	}
	
	public void information() {
		System.out.println("제품 이름 : " + pName);
		System.out.println("제품 가격 : " + price);
		System.out.println("제품 브랜드 : " + brand);
	}
	
}

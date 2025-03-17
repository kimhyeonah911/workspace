package z.practice.oop.ex3;

public class TV {
	private String name;
	private int year;
	private int inch;
	private int price;
	
	public TV() {
		super();
	}

	public TV(String name, int year, int inch, int price) {
		super();
		this.name = name;
		this.year = year;
		this.inch = inch;
		this.price = price;
	}
	
	public String show() {
		return this.name + "에서 만든 " + this.year + "년형 " + this.inch + "인치 TV 가격 : " + this.price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}

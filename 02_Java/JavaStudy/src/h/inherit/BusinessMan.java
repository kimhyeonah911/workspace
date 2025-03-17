package h.inherit;

public class BusinessMan extends Man{
	private String company;
	private String position;
	
	public BusinessMan(String name, String company, String position) {
		super(name);
		this.company = company;
		this.position = position;
		System.out.println("Company : " + company);
		System.out.println("Position : " + position);
	}

	public void tellYourInfo() {
		System.out.println("my company is " + this.company);
		System.out.println("my position is " + this.position);
	}
}

package h.inherit2;

public class TV extends Product{
	private int inch;

	public TV(int inch) {
		super();
	}

	public TV(String brand, String pCode, String pName, int price, int inch) {
		super(brand, pCode, pName, price);
		this.inch = inch;
	}
	
	public String information() {
		return super.information() + " 인치 : " + inch;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}
	
	
}

package z.practice.oop.ex1;

public class SquareController {
	private Shape s = new Shape();
	
	public double calcPerimeter(double height, double width) {
		s = new Shape(2, height, width);
		return s.getHeight() * 2 + s.getWidth() * 2;
	}
	
	public double calcArea(double height, double width) {
		s = new Shape(2, height, width);
		return s.getHeight() * s.getWidth();
	}
	
	public void paintColor(String color) {
		this.s.setColor(color);
	}
	
	public String print() {
		return "사각형 " + s.information();
	}
	
}

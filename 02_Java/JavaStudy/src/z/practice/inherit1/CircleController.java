package z.practice.inherit1;

public class CircleController {
	private Circle c = new Circle();
	static final double PI = 3.14;
	
	
	public String calcArea(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		return c.toString() + " / " + PI*radius*radius;
	}
	
	public String calcCircum(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		return c.toString() + " / " + PI*radius*2;
	}
}

package z.practice.object3;

public class Student {
	String name;
	int age; 
	double mathScore;
	double enScore;
	double koScore;
	
	public Student() {
		super();
	}
	
	public Student(String name, int age, double mathScore, double enScore, double koScore) {
		super();
		this.name = name;
		this.age = age;
		this.mathScore = mathScore;
		this.enScore = enScore;
		this.koScore = koScore;
	}
	
	//메서드
	public void myInfo() { //내 정보를 출력하는 기능
		System.out.println("안녕하세요. 저는 " + this.age + "살의 " + this.name + "입니다.");
	}
	
	public double getAvg() {
		return((this.mathScore+this.enScore+this.koScore)/3);
	}

	
}
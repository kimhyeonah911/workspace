package z.practice.object3;
import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		Student choi = new Student("최지원", 15, 80, 60, 90);
		Student kim = new Student("김민수", 17, 60, 75, 70);
		Student park = new Student("박지민", 19, 90, 45, 85);
		
		System.out.println(choi.name + "의 평균 : " + choi.getAvg());
		System.out.println(kim.name + "의 평균 : " + kim.getAvg());
		System.out.println(park.name + "의 평균 : " + park.getAvg());
		
		//lee라는 객체를 사용자로부터 이름, 나이, 점수를 입력받아 평균 구해서 출력하는 프로그램 작성
		Scanner sc = new Scanner(System.in);
		String name;
		int age;
		double mathScore, enScore, koScore;
		System.out.print("이름을 입력하세요 : ");
		name = sc.next();
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		System.out.print("수학점수를 입력하세요 : ");
		mathScore = sc.nextDouble();
		System.out.print("영어점수를 입력하세요 : ");
		enScore = sc.nextDouble();
		System.out.print("국어점수를 입력하세요 : ");
		koScore = sc.nextDouble();
		
		Student lee = new Student(name, age, mathScore, enScore, koScore);
		System.out.println(lee.name + "의 평균 : " + lee.getAvg());
		
		University kh = new University("kh대학교", 75, 60, 60);
	
		kh.apply(choi);
		kh.apply(kim);
		kh.apply2(park.name, park.getAvg(), park.enScore, park.koScore);
		kh.apply(lee);
	}
}

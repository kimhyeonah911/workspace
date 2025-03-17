package z.practice.object.array;

public class StudentMenu {
	private StudentController ssm = new StudentController();
	
	public StudentMenu() {
		System.out.println("========== 학생 정보 출력 ==========");
		Student[] sArr = ssm.printStudent();
		for(int i=0; i<sArr.length; i++) {
			System.out.println("이름 : " + sArr[i].getName() + " / 과목 : " + sArr[i].getSubject() + " / 점수 : " + sArr[i].getScore());
		}
		
		System.out.println("========== 학생 성적 출력 ==========");
		double[] dArr = ssm.avgScore();
		System.out.println("학생 점수 합계 : " + dArr[0]);
		System.out.println("학생 점수 평균 : " + dArr[1]);
		
		System.out.println("========== 성적 결과 출력 ==========");
		for(Student st : sArr) {
			if(ssm.CUT_LINE < st.getScore()) {
				System.out.println(st.getName() + "학생은 통과입니다.");
			}else {
				System.out.println(st.getName() + "학생은 재시험 대상입니다.");
			}
		}
	}
}

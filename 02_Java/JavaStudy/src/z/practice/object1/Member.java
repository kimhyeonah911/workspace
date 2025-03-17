package z.practice.object1;

//id, pwd, name, phone, email, age, gender(남, 여) 를 필드로 가지고있는 Member클래스 정의하기
public class Member {
	String id;
	String pwd;
	String name;
	String phone;
	String email;
	int age;
	char gender;
	
	//기본생성자와 아이디, 비밀번호, 이름을 넘겨받아 초기화하는 생성자 정의
	public Member() {
		super();
	}
	
	public Member(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	//나머지정보를 초기화해주는 메서드 setInfo정의, 모든 정보를 서술해서 출력해주는 toString이라는 메서드를 정의
	public void setInfo(String phone, String email, int age, char gender){
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}
	
	public void toInfo(){
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("성별 : " + gender);
		System.out.println("전화번호 : " + phone);
		System.out.println("이메일 : " + email);
		System.out.println("id : " + id + " pwd : " + pwd);
	}
}

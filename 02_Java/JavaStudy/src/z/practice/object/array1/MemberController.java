package z.practice.object.array1;

public class MemberController {
	private Member[] m = new Member[SIZE];
	public static final int SIZE = 10;
	
	public int existMemberNum() {
		int count = 0;
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null) {
				break;
			}
			count++;
		}
		return count;
	}
	
	public Boolean checkId(String Id) { // 중복값이 있다면 true 없다면 false 반환
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null) {
				return false;
			}
			
			if(m[i].getId().equals(Id)) {
				return true;
			}
		}
		return false;
	}
	
	public void insertMember(String id, String name, String password, String email, char gender, int age) {
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null) {
				m[i] = new Member(id, name, password, email, gender, age);
				return;
			}

		}
	}
	
	public String searchId(String id) {
		int num = 0;
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null ) {
				return null;
			}else if(m[i].getId().equals(id)){
				return m[i].inform();
			}
		}
		return null;
	}
	
	public Member[] searchName(String name) {
		Member[] result = new Member[SIZE];
		int index = 0;
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null) {
				break;
			}
			if(m[i].getName().equals(name)) {
				result[index++] = m[i];
			}
		}
		return result;
	}
	
	public Member[] searchEmail(String email) {
		Member[] result = new Member[SIZE];
		int index = 0;
		for(int i=0; i<SIZE; i++) {
			if(m[i] == null) {
				break;
			}
			if(m[i].getEmail().equals(email)) {
				result[index++] = m[i];
			}
		}
		return result;
	}
	
	public Boolean updatePassword(String id, String password) {
		for(int i=0; i<SIZE; i++) {
			if(m[i]==null) {
				return false;
			}
			if(m[i].getId().equals(id)) {
				m[i].setPassword(password);
				return true;
			}
		}
		return false;
	}
	
	public Boolean updateName(String id, String name) {
		for(int i=0; i<SIZE; i++) {
			if(m[i]==null) {
				return false;
			}
			if(m[i].getId().equals(id)) {
				m[i].setName(name);
				return true;
			}
		}
		return false;
	}
	
	public Boolean updateEmail(String id, String email) {
		for(int i=0; i<SIZE; i++) {
			if(m[i]==null) {
				return false;
			}
			if(m[i].getId().equals(id)) {
				m[i].setEmail(email);
				return true;
			}
		}
		return false;
	}
	
	public Boolean delete(String id) {
		for(int i=0; i<SIZE; i++) {
			if(m[i]==null) {
				return false;
			}
			if(m[i].getId().equals(id)) {
				//i요소의 null이 대입되었기 때문에 뒤에 있는 요소에는 값이 있으면 안 됨
				//그래서 i요소에 null을 넣는 것이 아니라 뒤에 요소를 하나씩 앞으로 당겨준다.
				//발견한 요소부터 맨 마지막 요소(null이 아닌 요소)까지
				for(int j=i; j<SIZE; j++) {
					if(j == SIZE-1) {
						m[j]= null;
						break;
					}else if(m[j]==null) {
						break;
					}
					m[j] = m[j+1];
				}
				return true;
			}
		}
		return false;
	}
	
	public void delete() {
		this.m = new Member[SIZE];
	}
	
	public Member[] printAll() {
		return m;
	}
}

package z.practice.object1;

public class Run {
	public static void main(String[] args) {
		Member mem1 = new Member("mem1", "mem1", "홍길동");
		mem1.setInfo("010-1111-1111", "aaa@naver.com", 24, '남');
		Member mem2 = new Member("mem2", "mem2", "홍길은");
		mem2.setInfo("010-2222-2222", "bbb@naver.com", 27, '여');
		Member mem3 = new Member("mem3", "mem3", "홍길금");
		mem3.setInfo("010-3333-3333", "ccc@naver.com", 26, '남');

		
		mem1.toInfo();
		mem2.toInfo();
		mem3.toInfo();
	}

}

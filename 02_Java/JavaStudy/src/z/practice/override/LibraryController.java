package z.practice.override;

public class LibraryController {
	private Member mem = null;
	private Book[] bList = new Book[10];
	
	public LibraryController() {
		super();
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}

	public void insertMember(Member mem) {
		this.mem = mem;
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		/*
		 * 문자열에 특정 문자가 포함되어 있는지 검사
		 * 문자열.indexOf("찾고자하는 문자열") -> index값 반환 / 못찾으면 -1 반환
		 * int num = "안녕하세요 최지원입니다.".indexOf("세요"); -> 3
		 * ->문자열에서  찾고자하는 문자열이 몇번째 index에 포함되어 있는지 찾고 싶을 때 사용
		 * 
		 * 문자열.contains("찾고자하는 문자열") -> 찾고자하는 문자열이 포함되어있는지
		 * 찾고자하는 문자열이 포함되어 있다면 true 반환 없다면 false 반환
		 * */
		int index = 0;
		Book[] scBook = new Book[bList.length];
		
		for(int i=0; i<bList.length; i++) {
			if(bList[i]==null) {
				break;
			}
			if(bList[i].getTitle().contains(keyword)){
				scBook[index++] = bList[i];
			}
		}
		
		return scBook;
	}
	
	public int rentBook(int index) {
		int result = 0;
		if(bList[index] instanceof AniBook) {
			if(((AniBook)bList[index]).getAccessAge() > mem.getAge()) {
				result = 1;
			}
		}else if(bList[index] instanceof CookBook) {
			if(((CookBook)bList[index]).isCoupon() == true) {
				mem.setCouponCount(mem.getCouponCount()+1) ;
				result = 2;
			}
		}
		
		return result;
	}
	
	public int countBook() {
		int num=0;
		for(int i=0; i<bList.length; i++) {
			if(bList[i] == null) {
				break;
			}else {
				num++;
			}
		}
		return num;
	}
	
	public void insertAniBook(String title, String author, String publisher, int accessAge) {
		for(int i=0; i<bList.length; i++) {
			if(bList[i]==null) {
				bList[i] = new AniBook(title, author, publisher, accessAge);
				return;
			}
		}
	}
	
	public void insertCookBook(String title, String author, String publisher, boolean coupon) {
		for(int i=0; i<bList.length; i++) {
			if(bList[i]==null) {
				bList[i] = new CookBook(title, author, publisher, coupon);
				return;
			}
		}
	}
	
	public void deleteBook(int num) {
		for(int i=num; i<bList.length; i++) {
			if(i == bList.length -1) {
				bList[i] = null;
			}else {
				bList[i]=bList[i+1];
			}
		}
	}
}

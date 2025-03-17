package z.practice.interface1;

public class GalaxyNote9 extends SmartPhone implements NotePen{

	public GalaxyNote9() {
		super();
		setMaker("삼성");
	}

	@Override
	public String charge() {
		return "고속 충전, 고속 무선 충전";
	}

	@Override
	public String makeCall() {
		return "번호를 누르고 통화버튼을 누름";
	}

	@Override
	public String takeCall() {
		return "수신 버튼을 누름";
	}

	@Override
	public String picture() {
		return "1200만 듀얼 카메라";
	}

	@Override
	public String touch() {
		return "정전식, 와콤펜 지원";
	}

	@Override
	public boolean bluetoothPen() {
		return true;
	}

	@Override
	public String printInformation() {
		return "갤럭시 노트9은 "+ getMaker() + "에서 만들어졌고 제원은 다음과 같다." + System.lineSeparator() + makeCall() + System.lineSeparator() + 
				takeCall() + System.lineSeparator() + picture() + System.lineSeparator() + charge() + 
				System.lineSeparator() + touch() + System.lineSeparator() + "블루투스펜 탑재 여부 : " + bluetoothPen();
	}	
	
	
}

package z.practice.interface1;

public class PhoneController {
	private String[] result = new String[2];
	
	public String[] method() {
		Phone[] phone = new Phone[2];
		phone[0] = new GalaxyNote9();
		phone[1] = new V40();
		
		for(int i=0; i<phone.length; i++) {
			if(phone[i] instanceof SmartPhone) {
				result[i] = ((SmartPhone)phone[i]).printInformation();
			}
		}
		return result;
	}
}

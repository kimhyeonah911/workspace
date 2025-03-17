package z.practice.exception1;

public class CharacterController {

	public CharacterController() {
		super();
	}
	
	public int countAlpha(String s) throws CharCheckException {
		int count = 0;
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
				count++;
			}
			else if(c == ' ') {
				throw new CharCheckException();
			}
		}
		return count;
	}
}

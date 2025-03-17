package z.practice.api;

import java.util.StringTokenizer;

public class TokenController {

	public TokenController() {
		super();
	}
	
	public String afterToken(String str) {
		StringBuilder afterstr = new StringBuilder();
		StringTokenizer stn = new StringTokenizer(str, " ");
		int size = stn.countTokens();
		for(int i=0; i<size; i++) {
			afterstr.append(stn.nextToken());
		}
		return afterstr.toString();
	}
	
	public String firstCap(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}
	
	public int findChar(String input, char one) {
		int count = 0;
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == one) {
				count++;
			}
		}
		return count;
	}
}

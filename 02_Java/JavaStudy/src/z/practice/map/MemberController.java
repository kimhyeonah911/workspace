package z.practice.map;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class MemberController {
	private HashMap map = new HashMap();
	
	public boolean joinMembership(String id, Member m) {
		if(map.get(id)==null) {
			map.put(id, m);
			return true;
		}
		return false;
	}
	
	public String logIn(String id, String password) {
		if(map.get(id) != null) {
			if(((Member) map.get(id)).getPassword().equals(password)) {
				return ((Member) map.get(id)).getName();
			}
		}
		return null;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) {
		if(map.get(id) != null && ((Member) map.get(id)).getPassword().equals(oldPw)) {
				((Member) map.get(id)).setPassword(newPw);
				return true;
		}
		return false;
	}
	
	public void changeName(String id, String newName) {
		if(map.get(id) != null) {
			((Member) map.get(id)).setName(newName);
		}
	}
	
	public TreeMap sameName(String name) {
		TreeMap sameTree = new TreeMap((o1, o2) -> ((String)o1).compareTo((String)o2));
		Set keys = map.keySet();
		for (Object id : keys) {
			if(((Member) map.get(id)).getName().equals(name)) {
				sameTree.put(id, ((Member) map.get(id)).getName());
			}
		}
		return sameTree;
	}
}

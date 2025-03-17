package z.practice.set;

import java.util.Comparator;

public class SortedLottery implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		if(((Lottery)o1).getName().compareTo(((Lottery)o2).getName())==0) {
			return ((Lottery)o1).getPhone().compareTo(((Lottery)o2).getPhone());
		}
		
		//0보다 작은 값 : o1<o2, 0 : o1=o2, 0보다 큰 수 : o1>o2  =>  오름차순
		return ((Lottery)o1).getName().compareTo(((Lottery)o2).getName());
	}
	
}
